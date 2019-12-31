package com.rodrigoramos.votingsystem.controller;

import com.rodrigoramos.votingsystem.dto.EmployeeNewDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.service.impl.EmployeeService;


import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private static Employee p1;
    private static Employee p2;
    private static EmployeeNewDTO p3;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        p1 = new Employee(1, "Jose", "Silva", "jose@terra.com.br","42597178048","123456");
        p2 = new Employee(2, "Alice", "Moraes","alice@bol.com.br","42597178048","123456");

        p3 = new EmployeeNewDTO("Maria", "Smith", "maria@ig.com.br","42597178048");




    }

    @Test
    void findAll_whenNoRecord() {

        Mockito.when(employeeService.findAll()).thenReturn(Arrays.asList());
        assertThat(employeeController.findAll().getBody().size(), is(0));
        Mockito.verify(employeeService, Mockito.times(1)).findAll();
    }

    @Test
    void findAll_whenRecord() {

        Mockito.when(employeeService.findAll()).thenReturn(Arrays.asList(p1, p2));
        assertThat(employeeController.findAll().getBody().size(), is(2));
        Mockito.verify(employeeService, Mockito.times(1)).findAll();
    }

/*    @Test
    void create() {
        ResponseEntity<Employee> p = employeeController.insert(employeeService.convertToModel(p1));
        Mockito.verify(employeeService, Mockito.times(1)).insert("dadsa", "dasddsa",);
    }*/

/*    @Test
    void create() {
        final NewEmployeeDTO actualDto = p2;

        final NewEmployeeDTO expected = new NewEmployeeDTO(actualDto.getName(), actualDto.getLastName(), actualDto.getEmail(), actualDto.getCpf());
        when(employeeService.findByEmail(actualDto.getEmail())).thenReturn(p2);

        final Employee entity = new Employee(null,actualDto.getName(), actualDto.getLastName(), actualDto.getEmail(), actualDto.getCpf());;
        when(employeeController.insert(any())).thenAnswer(invocation -> invocation.getArgument(0));

        NewEmployeeDTO actual = employeeService.insert(actualDto);

        Assertions.assertThat(actual).isEqualToComparingFieldByField(expected);

        verify(employeeController).insert(entity);
    }*/

    @Test
    void findById_WhenMatch() {

        Mockito.when(employeeService.findById(1)).thenReturn(p1);
        ResponseEntity<Employee> p = employeeController.find(1);
        assertThat(p.getBody(), is(p1) );
    }

    @Test
    void findById_WhenNoMatch() {

       // Mockito.when(employeeService.find(1)).thenReturn(Optional.empty());
        ResponseEntity<Employee> p = employeeController.find(1);
        assertThat(p.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    void deleteById_WhenNotFound() {

        employeeController.delete(1);
        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployeeById(1);
    }

    @Test

        void deleteById_WhenFound() {
        employeeController.delete(1);
        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployeeById(1);

    }



}
