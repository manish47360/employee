package com.manishk.employee.model.entity;

import com.manishk.employee.model.dto.EmployeeDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private double salary;



    public Employee(EmployeeDTO dto) {
         setName(dto.getName());
         setAddress(dto.getAddress());
         setSalary(dto.getSalary());
    }

}
