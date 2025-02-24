package com.example.crud_oracle_mongo.repository;

import com.example.crud_oracle_mongo.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {
    Page<Book> findBooksByAuthorLike(String author, Pageable pageable);
}
