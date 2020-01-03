package com.rodrigoramos.votingsystem.service.impl;

import com.rodrigoramos.votingsystem.dto.EmployeeDTO;
import com.rodrigoramos.votingsystem.dto.EmployeeNewDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.model.enums.Profile;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import com.rodrigoramos.votingsystem.security.UserSS;
import com.rodrigoramos.votingsystem.service.exception.AuthorizationException;
import com.rodrigoramos.votingsystem.service.exception.ObjectNotFoundException;
import com.rodrigoramos.votingsystem.service.interfaces.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private final BCryptPasswordEncoder encoder;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
    }


    public Employee findById(Integer id) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Employee.class.getName()));
    }

    public Employee findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new ObjectNotFoundException(
                    "Funcionário não encontrado! Id: " + employee.getId() + ", Tipo: " + Employee.class.getName());
        }
        return employee;
    }

    public Employee insert(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        Employee newEmployee = findById(employee.getId());
        updateData(newEmployee, employee);
        return employeeRepository.save(newEmployee);
    }

    public void deleteEmployeeById(Integer id) {
        findById(id);
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }



    public Employee convertToModel(EmployeeNewDTO employeeNewDTO) {
        return new Employee(null, employeeNewDTO.getName(), employeeNewDTO.getLastName(), employeeNewDTO.getEmail(),
                employeeNewDTO.getCpf(), encoder.encode(employeeNewDTO.getPassword()));
    }

    public Employee convertToModel(EmployeeDTO employeeDTO) {
        return new Employee(null, employeeDTO.getName(), employeeDTO.getLastName(), employeeDTO.getEmail(), null, encoder.encode(employeeDTO.getPassword()));
    }



    private void updateData(Employee newEmployee, Employee employee) {
        if(employee.getName() != null) newEmployee.setName(employee.getName());
        if(employee.getLastName() != null) newEmployee.setLastName(employee.getLastName());
        if(employee.getEmail() != null) newEmployee.setEmail(employee.getEmail());
    }

}
