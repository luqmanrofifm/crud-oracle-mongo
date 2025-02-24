package com.example.crud_oracle_mongo.controller;

import com.example.crud_oracle_mongo.dto.request.EmployeeDto;
import com.example.crud_oracle_mongo.entity.Employee;
import com.example.crud_oracle_mongo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/crud/employee"})
@RequiredArgsConstructor
public class EmployeeController extends BaseController {
    private final EmployeeService employeeService;

    @PostMapping({"/list"})
    public ResponseEntity<Object> list() {
        try {
            List<Employee> result = this.employeeService.getAll();
            return this.success(result);
        } catch (Exception var2) {
            return this.error(HttpStatus.EXPECTATION_FAILED, "error");
        }
    }

    @PostMapping({"/detail/{id}"})
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        try {
            Optional<Employee> optionalE = this.employeeService.getById(id);
            return optionalE.isPresent() ? this.success(optionalE.get()) : this.error(HttpStatus.NOT_FOUND, "Object not found");
        } catch (Exception e) {
            return this.error(HttpStatus.EXPECTATION_FAILED, e.getMessage());
        }
    }

    @PostMapping({"/create"})
    public ResponseEntity<Object> create(@RequestBody EmployeeDto dto) {
        try {
            return this.success(this.employeeService.create(dto));
        } catch (Exception var3) {
            return this.error(HttpStatus.EXPECTATION_FAILED, "error");
        }
    }

    @PostMapping({"/update/{id}"})
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody EmployeeDto dto) {
        try {
            Optional<Employee> optionalE = this.employeeService.getById(id);
            if (optionalE.isPresent()) {
                Employee updatedEntity = this.employeeService.update(optionalE.get(), dto);
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
            Optional<Employee> optionalE = this.employeeService.getById(id);
            if (optionalE.isPresent()) {
                this.employeeService.delete(optionalE.get());
                return this.success("Object was deleted");
            } else {
                return this.error(HttpStatus.NOT_FOUND, "Object not found");
            }
        } catch (Exception e) {
            return this.error(HttpStatus.EXPECTATION_FAILED, e.getMessage());
        }
    }
}
