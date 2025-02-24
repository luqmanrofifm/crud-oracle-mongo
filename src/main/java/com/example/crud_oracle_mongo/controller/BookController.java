package com.example.crud_oracle_mongo.controller;

import com.example.crud_oracle_mongo.dto.request.BookDto;
import com.example.crud_oracle_mongo.entity.Book;
import com.example.crud_oracle_mongo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/crud/book"})
@RequiredArgsConstructor
public class BookController extends BaseController {
    private final BookService bookService;

    @PostMapping({"/list"})
    public ResponseEntity<Object> list(
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "100") int limit,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false,defaultValue = "true") boolean asc,
            @RequestParam(required = false) String search) {
        Pageable pageable = this.pageFromRequest(page, limit, sort, asc);

        try {
            Page<Book> result = this.bookService.findAll(search, pageable);
            return this.success(result);
        } catch (Exception var8) {
            return this.error(HttpStatus.EXPECTATION_FAILED, "error");
        }
    }

    @PostMapping({"/detail/{id}"})
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        try {
            Optional<Book> optionalE = this.bookService.get(id);
            return optionalE.isPresent() ? this.success(optionalE.get()) : this.error(HttpStatus.NOT_FOUND, "Object not found");
        } catch (Exception e) {
            return this.error(HttpStatus.EXPECTATION_FAILED, e.getMessage());
        }
    }

    @PostMapping({"/create"})
    public ResponseEntity<Object> create(@RequestBody BookDto dto) {
        try {
            return this.success(this.bookService.createFromDto(dto));
        } catch (Exception var3) {
            return this.error(HttpStatus.EXPECTATION_FAILED, "error");
        }
    }

    @PostMapping({"/update/{id}"})
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody BookDto dto) {
        try {
            Optional<Book> optionalE = this.bookService.get(id);
            if (optionalE.isPresent()) {
                Book updatedEntity = this.bookService.update(optionalE.get(), dto);
                return this.success(updatedEntity);
            } else {
                return this.error(HttpStatus.NOT_FOUND, "Object not found");
            }
        } catch (Exception var5) {
            return this.error(HttpStatus.EXPECTATION_FAILED, "error");
        }
    }

    @PostMapping({"/delete/{id}"})
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            Optional<Book> optionalE = this.bookService.get(id);
            if (optionalE.isPresent()) {
                this.bookService.delete(optionalE.get());
                return this.success("Object was deleted");
            } else {
                return this.error(HttpStatus.NOT_FOUND, "Object not found");
            }
        } catch (Exception e) {
            return this.error(HttpStatus.EXPECTATION_FAILED, e.getMessage());
        }
    }

    @PostMapping({"/list-by-author"})
    public ResponseEntity<Object> listByAuthor(@RequestParam(required = false) String author,
                                               @RequestParam(required = false,defaultValue = "0") int page,
                                               @RequestParam(required = false,defaultValue = "100") int limit,
                                               @RequestParam(required = false) String sort,
                                               @RequestParam(required = false,defaultValue = "true") boolean asc) {
        try {
            Pageable pageable = this.pageFromRequest(page, limit, sort, asc);
            return this.success(this.bookService.getListBookByAuthor(author, pageable));
        } catch (Exception var2) {
            return this.error(HttpStatus.EXPECTATION_FAILED, "error");
        }
    }
}
