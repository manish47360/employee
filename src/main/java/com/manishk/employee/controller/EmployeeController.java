package com.manishk.employee.controller;

import com.manishk.employee.model.dto.EmployeeDTO;
import com.manishk.employee.model.dto.ErrorMessage;
import com.manishk.employee.model.entity.Employee;
import com.manishk.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody EmployeeDTO employee, UriComponentsBuilder uriComponentsBuilder){
        Employee save = employeeService.save(employee);
        return ResponseEntity.created(uriComponentsBuilder.path("/employees/{id}").build(save.getId())).build();
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> get(@PathVariable long id){
        EmployeeDTO employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable long id, @RequestBody EmployeeDTO dto){
        EmployeeDTO updateEmployee = employeeService.update(id,dto);
        return ResponseEntity.ok(updateEmployee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmployeeService.EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(EmployeeService.EmployeeNotFoundException exception) {
        //"Employee not found with id"
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Employee not found with id");
        return ResponseEntity.of(Optional.of(errorMessage));
    }

}
