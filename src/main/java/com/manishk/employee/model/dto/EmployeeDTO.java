package com.manishk.employee.model.dto;

import com.manishk.employee.model.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeDTO {
    private long id;
    private String name;
    private String address;
    private double salary;

    public EmployeeDTO(Employee employee){
        setId(employee.getId());
        setName(employee.getName());
        setAddress(employee.getAddress());
        setSalary(employee.getSalary());
    }
}

