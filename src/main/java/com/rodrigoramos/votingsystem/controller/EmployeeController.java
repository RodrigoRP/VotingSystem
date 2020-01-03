package com.rodrigoramos.votingsystem.controller;

import com.rodrigoramos.votingsystem.dto.EmployeeDTO;
import com.rodrigoramos.votingsystem.dto.EmployeeNewDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.service.impl.EmployeeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @ApiOperation(value="Busca por id")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<Employee> find(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping(value="/email")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
      public ResponseEntity<Employee> findByEmail(@RequestParam(value="value") String email) {
        Employee employee = employeeService.findByEmail(email);
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping(value = "/")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<Void> insert(@Valid @RequestBody EmployeeNewDTO employeeNewDTO) {
        Employee employee = employeeService.convertToModel(employeeNewDTO);
        employee = employeeService.insert(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<Void> update(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id) {
        Employee employee = employeeService.convertToModel(employeeDTO);
        employee.setId(id);
        employee = employeeService.update(employee);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        find(id);
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    required = true,
                    dataType = "string",
                    paramType = "header",
                    value = "Token de autenticação."
            )})
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        List<Employee> employeeList = employeeService.findAll();
        List<EmployeeDTO> employeeDTOList = employeeList.stream().map(obj -> new EmployeeDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(employeeDTOList);
    }

}
