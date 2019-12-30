package com.rodrigoramos.votingsystem.controller;

import com.rodrigoramos.votingsystem.dto.EmployeeDTO;
import com.rodrigoramos.votingsystem.dto.NewEmployeeDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> find(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> insert(@Valid @RequestBody NewEmployeeDTO newEmployeeDTO) {
        Employee employee = employeeService.convertToModel(newEmployeeDTO);
        employee = employeeService.insert(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id) {
        Employee employee = employeeService.convertToModel(employeeDTO);
        employee.setId(id);
        employee = employeeService.update(employee);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        find(id);
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        List<Employee> employeeList = employeeService.findAll();
        List<EmployeeDTO> employeeDTOList = employeeList.stream().map(obj -> new EmployeeDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(employeeDTOList);
    }


}
