package com.example.crud_oracle_mongo.service;

import com.example.crud_oracle_mongo.dto.request.EmployeeDto;
import com.example.crud_oracle_mongo.entity.Employee;
import com.example.crud_oracle_mongo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    public Optional<Employee> getById(UUID id) {
        return this.employeeRepository.findById(id);
    }

    public Employee create(EmployeeDto employee) {
        Employee entity = new Employee(UUID.randomUUID(), employee.getName(), employee.getPosition(), employee.getDepartment(), employee.getSalary());
        return this.employeeRepository.save(entity);
    }

    public Employee update(Employee employee, EmployeeDto dto) {
        employee.setName(dto.getName());
        employee.setPosition(dto.getPosition());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        return this.employeeRepository.save(employee);
    }

    public void delete(Employee employee) {
        this.employeeRepository.delete(employee);
    }
}
