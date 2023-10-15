package com.adan.departmentservice.service;

import com.adan.departmentservice.dto.DepartmentRequest;
import com.adan.departmentservice.dto.DepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<DepartmentResponse> getAllDepartment();
    DepartmentResponse getDepartmentById(Long id);
    void addDepartment(DepartmentRequest departmentRequest);
    boolean updateDepartment(Long id, DepartmentRequest departmentRequest);
    boolean deleteDepartmentById(Long id);
}
