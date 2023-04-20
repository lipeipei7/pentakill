package com.learning.pentaQ.controller;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaParserExample {

    private static String getResolvedPackage(String fullyQualifiedClassName) {
        return fullyQualifiedClassName.substring(0, fullyQualifiedClassName.lastIndexOf("."));
    }


    public static void main(String[] args) throws FileNotFoundException {
        File javaFile = new File("src/main/java/com/learning/pentaQ/request/MyEntityRequest.java");
        // Parse the Java file into an AST using JavaParser
        JavaParser javaParser = new JavaParser();
        // Create a JavaSymbolSolver object
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver());
        combinedTypeSolver.add(new JavaParserTypeSolver(new File("src/main/java")));
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedTypeSolver);

        // Configure symbol resolution
        javaParser.getParserConfiguration().setSymbolResolver(symbolSolver);

        CompilationUnit cu = javaParser.parse(javaFile).getResult().get();

        //package name
        String packageName = cu.getPackageDeclaration().get().getNameAsString();

        // Generate a TypeSpec using JavaPoet
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(cu.getTypes().get(0).getNameAsString());

        // Add the imports
        Map<String, String> importsMap = cu.getImports().stream()
                .filter(importDeclaration -> importDeclaration.getName().getQualifier().isPresent())
                .collect(Collectors.toMap(
                        importDeclaration -> importDeclaration.getName().getIdentifier(),
                        importDeclaration -> importDeclaration.getName().getQualifier().get().toString()
                ));

        // Add the annotations
        for (AnnotationExpr annotationExpr : cu.getTypes().get(0).getAnnotations()) {
            if (!annotationExpr.isNormalAnnotationExpr()) {
                classBuilder.addAnnotation(
                        ClassName.get(
                                importsMap.get(annotationExpr.getNameAsString()),
                                annotationExpr.getNameAsString())
                );
                continue;
            }
            annotationExpr.asNormalAnnotationExpr().getPairs().forEach(pair -> {
                classBuilder.addAnnotation(AnnotationSpec.builder(
                                ClassName.get(
                                        importsMap.get(annotationExpr.getNameAsString()),
                                        annotationExpr.getNameAsString()))
                        .addMember(pair.getNameAsString(), "$L", pair.getValue())
                        .build());
            });
        }


        // Add the fields
        for (FieldDeclaration fieldDeclaration : cu.getTypes().get(0).getFields()) {
            FieldSpec.Builder fieldBuilder = FieldSpec.builder(
                    ClassName.get(
                            getResolvedPackage(fieldDeclaration.getCommonType().resolve().asReferenceType().getQualifiedName()),
                            fieldDeclaration.getCommonType().toString()
                    ),
                    fieldDeclaration.getVariable(0).getNameAsString()
            );
            fieldBuilder.addModifiers(Modifier.PRIVATE);
            classBuilder.addField(fieldBuilder.build());
        }

        // Add the methods
        for (MethodDeclaration methodDeclaration : cu.getTypes().get(0).getMethods()) {
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(methodDeclaration.getNameAsString())
                    .addModifiers(Modifier.PUBLIC)
                    .returns(ClassName.get(
                            methodDeclaration.getType().resolve().asReferenceType().getQualifiedName(),
                            methodDeclaration.getType().toString()));
            methodDeclaration.getBody().get().getStatements().forEach(statement -> {
                methodBuilder.addCode(statement.toString() + System.lineSeparator());
            });

            for (Parameter parameter : methodDeclaration.getParameters()) {
                methodBuilder.addParameter(
                        ClassName.get(
                                parameter.getType().resolve().asReferenceType().getQualifiedName(),
                                parameter.getType().toString()
                        ), parameter.getNameAsString());
            }
            classBuilder.addMethod(methodBuilder.build());
        }

        // Write the file
        JavaFile.Builder output = JavaFile.builder("com.learning.pentaQ.request", classBuilder.build());

        try {
            output.build().writeTo(System.out);
//            output.writeTo(new File("src/main/java").toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
