package com.asset.management.AssetManager.mapper;

import com.asset.management.AssetManager.entity.Asset;
import com.asset.management.AssetManager.entity.Employee;
import com.asset.management.AssetManager.models.EmployeeDto;
import org.hibernate.internal.util.collections.CollectionHelper;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employeeEntity){
        if(employeeEntity ==null){
            return null;
        }

        return new EmployeeDto(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getEmail()
                , employeeEntity.getDepartment(), mapAssignedAssetIds(employeeEntity));
    }

    private static List<Long> mapAssignedAssetIds(Employee employee){
        return CollectionHelper.isNotEmpty(employee.getAssets())
                ? employee.getAssets().stream().map(Asset::getId).collect(Collectors.toList()) : null;
    }

    public static Employee toEntity(EmployeeDto employeeDto){
        if(employeeDto == null){
            return null;
        }

        Employee employeeEntity = new Employee();
        employeeEntity.setId(employeeDto.getId());
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setEmail(employeeDto.getEmail());
        employeeEntity.setDepartment(employeeDto.getDepartment());
        return employeeEntity;
    }
}
