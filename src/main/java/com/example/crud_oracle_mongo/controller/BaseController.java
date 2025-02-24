package com.example.crud_oracle_mongo.controller;

import com.example.crud_oracle_mongo.common.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

public class BaseController {

    public ResponseEntity<Object> success(Object data) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "success", data), HttpStatus.OK);
    }

    public ResponseEntity<Object> error(HttpStatus httpStatus, Object message) {
        return new ResponseEntity<>(new Response(httpStatus.value(), "error", message), httpStatus);
    }

    public Pageable pageFromRequest(int page, int size, String sort, Boolean asc) {
        if (ObjectUtils.isEmpty(sort)) {
            sort = "id";
        }

        return PageRequest.of(page, size, Sort.by(this.getSortBy(sort, asc, true)));
    }

    public Order getSortBy(String sort, Boolean asc, Boolean ignoreCase) {
        if (Boolean.FALSE.equals(ignoreCase)) {
            return Boolean.TRUE.equals(asc) ? Order.asc(sort) : Order.desc(sort);
        } else {
            return Boolean.TRUE.equals(asc) ? Order.asc(sort).ignoreCase() : Order.desc(sort).ignoreCase();
        }
    }
}
