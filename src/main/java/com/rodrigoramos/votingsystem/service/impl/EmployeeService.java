package com.rodrigoramos.votingsystem.service.impl;

import com.rodrigoramos.votingsystem.dto.EmployeeDTO;
import com.rodrigoramos.votingsystem.dto.NewEmployeeDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import com.rodrigoramos.votingsystem.service.exception.ObjectNotFoundException;
import com.rodrigoramos.votingsystem.service.interfaces.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Employee.class.getName()));
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



    public Employee findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + employee.getId() + ", Tipo: " + Employee.class.getName());
        }
        return employee;
    }


  /*  public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }*/

    public Employee convertToModel(NewEmployeeDTO newEmployeeDTO) {
        return new Employee(null, newEmployeeDTO.getName(), newEmployeeDTO.getLastName(), newEmployeeDTO.getEmail(),
                newEmployeeDTO.getCpf(), encoder.encode(newEmployeeDTO.getPassword()));
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
