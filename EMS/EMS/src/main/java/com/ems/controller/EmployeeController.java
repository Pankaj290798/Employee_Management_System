package com.ems.controller;

import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN','VIEW_ONLY')")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
       return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee newDetails) {
    return ResponseEntity.ok(employeeService.updateEmployee(newDetails));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("Employee Deleted Successfully ");
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN','VIEW_ONLY')")
    @GetMapping("/department/{departmentId}")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN','VIEW_ONLY')")
    @GetMapping("/city/{cityId}")
    public List<Employee> getEmployeesByCity(@PathVariable Long cityId) {
        return employeeService.getEmployeesByCity(cityId);
    }
    }

