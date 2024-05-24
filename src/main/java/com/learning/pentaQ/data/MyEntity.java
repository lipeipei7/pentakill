package com.learning.pentaQ.data;


import com.github.crud.generator.annotation.CrudGenerator;
import com.github.crud.generator.constant.RepositoryType;
import lombok.*;

import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CrudGenerator(basePackage = "com.learning.pentaQ", repositoryType = RepositoryType.JPA)
public class MyEntity {
    @Id
    private String id;
    private String name;
}
