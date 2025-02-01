package com.ems.service;

import com.ems.entity.Employee;
import com.ems.exception.ApplicationException;
import com.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Employee not found","","", HttpStatus.NOT_FOUND));
    }

    public void deleteById(Long id){
        Employee employee =employeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Employee not found","","", HttpStatus.NOT_FOUND));
        employeeRepository.deleteById(employee.getId());
    }

    public Employee updateEmployee(Employee newDetails){
        Employee employee = employeeRepository.findById(newDetails.getId())
                .orElseThrow(() -> new ApplicationException("Employee not found","","", HttpStatus.NOT_FOUND));
        employee.setFirstName(newDetails.getFirstName());
        employee.setLastName(newDetails.getLastName());
        employee.setJobTitle(newDetails.getJobTitle());
        employee.setSalary(newDetails.getSalary());
        employee.setDepartment(newDetails.getDepartment());
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByCity(Long cityId) {
        return employeeRepository.findByCityId(cityId);
    }

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
