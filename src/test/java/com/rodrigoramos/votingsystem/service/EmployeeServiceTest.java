package com.rodrigoramos.votingsystem.service;

import com.rodrigoramos.votingsystem.dto.EmployeeNewDTO;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@ContextConfiguration
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeNewDTO employeeNewDTO1;
    private EmployeeNewDTO employeeNewDTO2;

   /* @MockBean
    private EmployeeRepository employeeRepository;*/

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setup() {
        employeeNewDTO1 = new EmployeeNewDTO();
        employeeNewDTO1.setName("Adolfo");
        employeeNewDTO1.setEmail("adolfo@bol.com.br");
        employeeNewDTO1.setLastName("Silva");
        employeeNewDTO1.setCpf("16962024002");

        employeeNewDTO2 = new EmployeeNewDTO();
        employeeNewDTO2.setName("Maria");
        employeeNewDTO2.setEmail("maria@bol.com.br");
        employeeNewDTO2.setLastName("Silva");
        employeeNewDTO2.setCpf("16962024002");

        final List<Employee> employeeList = new ArrayList<>();

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        employee1 = employeeService.findByEmail(employeeNewDTO1.getEmail());
        employee2 = employeeService.findByEmail(employeeNewDTO2.getEmail());

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



