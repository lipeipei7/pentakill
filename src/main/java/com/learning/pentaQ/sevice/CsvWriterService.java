package com.learning.pentaQ.sevice;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.mapping.Collection;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWriterService {
    CsvMapper csvMapper = new CsvMapper();

    public String writeCsvRowByRow(Person data, OutputStream outputStream, CsvSchema.Builder schemaBuilder) {
        if (data == null) {
            // Handle empty data
            return "";
        }

        // Write to the provided output stream row by row
        try {
            return csvMapper.writer(schemaBuilder.build())
                    .writeValueAsString(data);
//                    .writeValue(outputStream, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {
        CsvWriterService csvWriterService = new CsvWriterService();

        List<Person> data = Arrays.asList(
                new Person(new Child("son", "1", "USA")),
                new Person(new Child("JaneSmith", "25", "Canada"))
        );

        CsvSchema.Builder schemaBuilder = CsvSchema.builder();

        // Create a CsvSchema object
        schemaBuilder
                .addColumn("child.country")
                .addColumn("child.age")
                .addColumn("child.name")
//                .setColumnSeparator('@')
//                .setLineSeparator('-')
                .setUseHeader(false);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String collect = data.stream()
                .map(person -> csvWriterService.writeCsvRowByRow(person, outputStream, schemaBuilder))
                .collect(Collectors.joining());
        String header = csvWriterService.csvMapper.writer(schemaBuilder.setUseHeader(true).build())
                .writeValueAsString(Collections.emptyList());

        System.out.println(header);
        System.out.println(collect);
//        System.out.println(outputStream.toString());
    }

    @AllArgsConstructor
    @Data
    public static class Person {
        Child child;
    }

    @AllArgsConstructor
    @Data
    public static class Child {
        String name;
        String age;
        String country;
    }

//    @JsonSerialize(using = ObjectToStringSerializer.class)
//    @JsonDeserialize(using = StringToObjectDeserializer.class)
//    public static class ObjectToStringSerializer extends StdSerializer<Object> {
//        protected ObjectToStringSerializer() {
//            super(Object.class);
//        }
//
//        @Override
//        public void serialize(Object value, com.fasterxml.jackson.core.JsonGenerator gen,
//                              com.fasterxml.jackson.databind.SerializerProvider provider) throws IOException {
//            gen.writeString(value.toString());
//        }
//    }
//
//    public static class StringToObjectDeserializer extends StdDeserializer<Object> {
//        protected StringToObjectDeserializer() {
//            super(Object.class);
//        }
//
//        @Override
//        public Object deserialize(com.fasterxml.jackson.core.JsonParser p,
//                                  com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
//            return p.getValueAsString();
//        }
//    }
}
