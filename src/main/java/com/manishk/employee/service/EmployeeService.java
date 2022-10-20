package com.manishk.employee.service;

import com.manishk.employee.EmployeeRepository;
import com.manishk.employee.model.dto.EmployeeDTO;
import com.manishk.employee.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository repository;

    public Employee save(EmployeeDTO dto){
     Employee employee = new Employee(dto);
     Employee saved = repository.save(employee);
     return saved;
    }

    public List<EmployeeDTO> getAll(){
        return repository.findAll().stream().map(EmployeeDTO::new).toList();
    }

    public EmployeeDTO findById(long id) {
      EmployeeDTO employee = repository.findById(id).map(EmployeeDTO::new).orElseThrow(EmployeeNotFoundException::new);
      log.info("find by id: {}", id);
      return employee;

    }

    public EmployeeDTO update(long id, EmployeeDTO dto) {
        Employee employee = new Employee(dto);
        employee.setId(id);
        repository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        log.info("employee updated on id : {}",id);
        return new EmployeeDTO(repository.save(employee));
    }

    public void delete(long id) {
        repository.findById(id).orElseThrow(EmployeeNotFoundException::new);
         repository.deleteById(id);
         log.info("employee deleted by id : {}" ,id);
    }

    public static class EmployeeNotFoundException extends RuntimeException{

    }
}
