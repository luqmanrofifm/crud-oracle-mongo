package com.example.crud_oracle_mongo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private Integer year;
}
