package com.example.crud_oracle_mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Document(
        collection = "employees"
)
public class Employee {
    @Id
    private UUID id;
    private String name;
    private String position;
    private String department;
    private Double salary;
}
