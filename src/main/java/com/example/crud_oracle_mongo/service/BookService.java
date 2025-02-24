package com.example.crud_oracle_mongo.service;

import com.example.crud_oracle_mongo.dto.request.BookDto;
import com.example.crud_oracle_mongo.entity.Book;
import com.example.crud_oracle_mongo.exceptions.BadRequestException;
import com.example.crud_oracle_mongo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book createFromDto(BookDto dto) {
        Book entity = new Book();
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setYear(dto.getYear());
        entity.setCreatedDate(new Date());
        this.bookRepository.save(entity);
        return entity;
    }

    public Page<Book> findAll(String search, Pageable pageable) {
        Specification<Book> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!ObjectUtils.isEmpty(search)) {
                predicates.add(cb.like(root.get("title"), "%" + search + "%"));
            }

            return !predicates.isEmpty() ? cb.and(predicates.toArray(new Predicate[0])) : null;
        };


        return this.bookRepository.findAll(spec, pageable);
    }

    public Optional<Book> get(UUID id) {
        return this.bookRepository.findById(id);
    }

    public Book update(Book book, BookDto dto) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setYear(dto.getYear());
        book.setUpdatedDate(new Date());
        this.bookRepository.save(book);
        return book;
    }

    public void delete(Book book) {
        this.bookRepository.delete(book);
    }


    public Page<Book> getListBookByAuthor(String author, Pageable pageable) {
        if (!ObjectUtils.isEmpty(author)) {
            return bookRepository.findBooksByAuthorLike(author, pageable);
        } else {
            throw new BadRequestException("Author is required");
        }
    }
}
