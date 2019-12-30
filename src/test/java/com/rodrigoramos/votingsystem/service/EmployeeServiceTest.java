package com.rodrigoramos.votingsystem.service;

import com.rodrigoramos.votingsystem.dto.NewEmployeeDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import com.rodrigoramos.votingsystem.service.impl.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@ContextConfiguration
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private NewEmployeeDTO newEmployeeDTO1;
    private NewEmployeeDTO newEmployeeDTO2;

   /* @MockBean
    private EmployeeRepository employeeRepository;*/

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setup() {
        newEmployeeDTO1 = new NewEmployeeDTO();
        newEmployeeDTO1.setName("Adolfo");
        newEmployeeDTO1.setEmail("adolfo@bol.com.br");
        newEmployeeDTO1.setLastName("Silva");
        newEmployeeDTO1.setCpf("16962024002");

        newEmployeeDTO2 = new NewEmployeeDTO();
        newEmployeeDTO2.setName("Maria");
        newEmployeeDTO2.setEmail("maria@bol.com.br");
        newEmployeeDTO2.setLastName("Silva");
        newEmployeeDTO2.setCpf("16962024002");

        final List<Employee> employeeList = new ArrayList<>();

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        employee1 = employeeService.findByEmail(newEmployeeDTO1.getEmail());
        employee2 = employeeService.findByEmail(newEmployeeDTO2.getEmail());

        employeeList.add(employee1);
        employeeList.add(employee2);
    }



    @Test
    public void getAllEmployeesTest()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee(null, "John", "John", "howtodoinjava@gmail.com","04496322021","123456");
        Employee empTwo = new Employee(null, "Alex", "kolenchiski", "alexk@yahoo.com","25798516075","123456");
        Employee empThree = new Employee(null, "Steve", "Waugh", "swaugh@gmail.com","90281067074","123456");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeRepository.findAll()).thenReturn(list);

        List<Employee> empList = employeeService.findAll();

        assertEquals(3, empList.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void getEmployeeByIdTest() {
        when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(new Employee(null, "Lokesh", "Gupta", "user@email.com", "90281067074","123456")));

        Employee emp = employeeService.findById(1);

        assertEquals("Lokesh", emp.getName());
        assertEquals("Gupta", emp.getLastName());
        assertEquals("user@email.com", emp.getEmail());
    }

    @Test
    public void createEmployeeTest() {
        Employee emp = new Employee(null,"Lokesh","Gupta","user@email.com","90281067074","123456");
        employeeService.insert(emp);
        verify(employeeRepository, times(1)).save(emp);
    }

/*
    @Test
    public void deleteEmployeeByIdTest() {
        Employee emp = new Employee(1,"Lokesh","Gupta","user@email.com","90281067074");
        employeeService.deleteEmployeeById(emp.getId());
        verify(employeeRepository, times(1)).deleteById(emp.getId());
    }
*/

/*    @Test
    public void testDeleteStudentById() {
        Employee employee;
        employee = employeeService.findByEmail(newEmployeeDTO1.getEmail());
        employeeService.deleteEmployeeById(employee.getId());
        Mockito.verify(employeeRepository, Mockito.times(1))
                .deleteById(employee.getId());
    }*/







}



