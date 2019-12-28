package com.rodrigoramos.votingsystem.service;

import com.rodrigoramos.votingsystem.dto.NewEmployeeDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@ContextConfiguration
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAllEmployeesTest()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee(null, "John", "John", "howtodoinjava@gmail.com","04496322021");
        Employee empTwo = new Employee(null, "Alex", "kolenchiski", "alexk@yahoo.com","25798516075");
        Employee empThree = new Employee(null, "Steve", "Waugh", "swaugh@gmail.com","90281067074");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeRepository.findAll()).thenReturn(list);

        List<Employee> empList = employeeService.findAll();

        assertEquals(3, empList.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void getEmployeeByIdTest()
    {
        when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(new Employee(null, "Lokesh", "Gupta", "user@email.com", "90281067074")));

        Employee emp = employeeService.find(1);

        assertEquals("Lokesh", emp.getName());
        assertEquals("Gupta", emp.getLastName());
        assertEquals("user@email.com", emp.getEmail());
    }

    @Test
    public void createEmployeeTest()
    {
        Employee emp = new Employee(null,"Lokesh","Gupta","user@email.com","90281067074");

        employeeService.insert(emp);

        verify(employeeRepository, times(1)).save(emp);
    }



}



