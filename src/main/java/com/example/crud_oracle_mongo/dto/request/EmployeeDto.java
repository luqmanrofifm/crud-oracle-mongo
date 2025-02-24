package com.example.crud_oracle_mongo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeDto {
    private String name;
    private String position;
    private String department;
    private Double salary;
}
