package com.example.crud_oracle_mongo.entity;

import com.example.crud_oracle_mongo.utils.UUIDConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(
        name = "book"
)
public class Book {
    @Id
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(
            name = "id",
            updatable = false,
            nullable = false,
            columnDefinition = "RAW(16)"
    )
    @Convert(
            converter = UUIDConverter.class
    )
    private UUID id;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private String title;
    private String author;
    private Integer year;
}
