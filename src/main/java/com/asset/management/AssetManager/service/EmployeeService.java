package com.asset.management.AssetManager.service;

import com.asset.management.AssetManager.entity.Employee;
import com.asset.management.AssetManager.mapper.EmployeeMapper;
import com.asset.management.AssetManager.models.EmployeeDto;
import com.asset.management.AssetManager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeMapper::toDto).collect(Collectors.toList());
    }

    public Optional<EmployeeDto> getEmployeeById(Long id) {
        Employee employeeEntity =  employeeRepository.findById(id).orElse(null);
        return nonNull(employeeEntity) ? Optional.of(EmployeeMapper.toDto(employeeEntity)) : Optional.empty();
    }

    public EmployeeDto createEmployee(EmployeeDto employee) {
        Employee employeeEntity =  employeeRepository.save(EmployeeMapper.toEntity(employee));
        return EmployeeMapper.toDto(employeeEntity);
    }
}
