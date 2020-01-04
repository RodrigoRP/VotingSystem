package com.rodrigoramos.votingsystem.service;

import com.rodrigoramos.votingsystem.dto.EmployeeDTO;
import com.rodrigoramos.votingsystem.dto.EmployeeNewDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.model.enums.Profile;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import com.rodrigoramos.votingsystem.security.UserSS;
import com.rodrigoramos.votingsystem.service.exception.AuthorizationException;
import com.rodrigoramos.votingsystem.service.exception.ObjectNotFoundException;
import com.rodrigoramos.votingsystem.service.impl.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@ContextConfiguration
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;


    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private Authentication auth;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private EmployeeService employeeService;


    private UserSS adminUser() {
        Set<Profile> userProfiles = new HashSet<>();
        userProfiles.add(Profile.ADMIN);
        return new UserSS(1, "he@ya.com", "123456", userProfiles);
    }

    private UserSS customerUser() {
        Set<Profile> userProfiles = new HashSet<>();
        userProfiles.add(Profile.USER);
        return new UserSS(2, "he@ya.com", "123", userProfiles);
    }

    private void mockSpringSecurityUser(UserSS user) {
        when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
    }

    @Test(expected = AuthorizationException.class)
    public void throwsExceptionWhenTriesToFindByIdButNotAuthenticated() {
        mockSpringSecurityUser(null);
        employeeService.findById(1);
    }

    @Test(expected = AuthorizationException.class)
    public void throwsExceptionWhenCustomerTriesToFindSomeoneElseById() {
        mockSpringSecurityUser(customerUser());
        employeeService.findById(1);
    }


    @Test
    public void updatesEmployeeTest() {
        mockSpringSecurityUser(adminUser());
        final EmployeeDTO input = new EmployeeDTO("howtodoinjava@gmail.com","John", "John");
        when(employeeRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Employee emp = employeeService.convertToModel(input);

        //employeeService.insert(emp);
       // employeeService.update(emp);
      //  verify(employeeRepository).save(any(Employee.class));
    }


    @Test
    public void getAllEmployeesTest() {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee(null, "John", "John", "howtodoinjava@gmail.com", "04496322021", bCryptPasswordEncoder.encode("123456"));
        Employee empTwo = new Employee(null, "Alex", "kolenchiski", "alexk@yahoo.com", "25798516075", bCryptPasswordEncoder.encode("123456"));
        Employee empThree = new Employee(null, "Steve", "Waugh", "swaugh@gmail.com", "90281067074", bCryptPasswordEncoder.encode("123456"));

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeRepository.findAll()).thenReturn(list);

        List<Employee> empList = employeeService.findAll();

        assertEquals(3, empList.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void createEmployeeTest() {
        Employee emp = new Employee(null, "Lokesh", "Gupta", "user@email.com", "90281067074", "123456");
        employeeService.insert(emp);
        verify(employeeRepository, times(1)).save(emp);
    }


    @Test
    public void findsValidUserByEmail() {
        mockSpringSecurityUser(adminUser());
        final Employee expected = new Employee(1, "Lokesh", "Gupta", "user@email.com", "90281067074", "123456");
        when(employeeRepository.findByEmail("user@email.com")).thenReturn((expected));
        final Employee actual = employeeService.findByEmail("user@email.com");
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @Test
    public void findsAnyUserByIdWhenAdmin() throws ObjectNotFoundException {
        mockSpringSecurityUser(adminUser());
        final Employee expected = new Employee(1, "Lokesh", "Gupta", "user@email.com", "90281067074", "123456");
        when(employeeRepository.findById(1)).thenReturn(Optional.of((expected)));

        final Employee actual = employeeService.findById(1);
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }


    @Test
    public void shouldConverteDeDTOParaModel() {
        Employee expected = new Employee(null, "Lokesh", "Gupta", "user@email.com", null, null);

        EmployeeDTO dto = new EmployeeDTO(expected.getName(), expected.getLastName(),expected.getEmail());
        assertThat(expected).isEqualToComparingFieldByField(employeeService.convertToModel(dto));
    }

   @Test
   public void shouldConverterDeDTOParaModel2() {
        Employee expected = new Employee(null, "Lokesh", "Gupta", "user@email.com", "90281067074", null);

        EmployeeNewDTO dto = new EmployeeNewDTO(expected.getName(), expected.getLastName(), expected.getEmail(), expected.getCpf());
        assertThat(expected).isEqualToComparingFieldByField(employeeService.convertToModel(dto));
    }

    @Test
    public void deleteEmployeeByIdTest() {
        mockSpringSecurityUser(adminUser());
        Employee expected = new Employee(1, "Lokesh", "Gupta", "user@email.com", "90281067074", "123456");
        when(employeeRepository.findById(1)).thenReturn(Optional.of((expected)));
        employeeService.deleteEmployeeById(expected.getId());

        assertThat(employeeRepository.count()).isEqualTo(0);
    }







}



