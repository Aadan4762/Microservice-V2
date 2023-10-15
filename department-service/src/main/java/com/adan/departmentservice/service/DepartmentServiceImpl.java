package com.adan.departmentservice.service;

import com.adan.departmentservice.dto.DepartmentRequest;
import com.adan.departmentservice.dto.DepartmentResponse;
import com.adan.departmentservice.entity.Department;
import com.adan.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    @Override
    public List<DepartmentResponse> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        return departmentOptional.map(this::mapToDepartmentResponse).orElse(null);
    }

    @Override
    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .name(departmentRequest.getName())
                .build();
        departmentRepository.save(department);

    }

    @Override
    public boolean updateDepartment(Long id, DepartmentRequest departmentRequest) {
        Optional<Department> existingDepartmentOptional = departmentRepository.findById(id);
        existingDepartmentOptional.ifPresent(existingDepartment ->{
            existingDepartment.setName(departmentRequest.getName());
            departmentRepository.save(existingDepartment);
        });
        return false;
    }

    @Override
    public boolean deleteDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        departmentOptional.ifPresent(department -> {
            departmentRepository.delete(department);
            log.info("Department {} is deleted", department.getId());
        });
        return false;
    }
}
