package com.ems.service;

import com.ems.entity.Department;
import com.ems.exception.ApplicationException;
import com.ems.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Department not found with id: ","","", HttpStatus.NOT_FOUND));
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setDepartmentName(updatedDepartment.getDepartmentName());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        Department department =departmentRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Department not found with id: ","","", HttpStatus.NOT_FOUND));
        departmentRepository.delete(department);
    }
}

