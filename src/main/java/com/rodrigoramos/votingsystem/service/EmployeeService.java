package com.rodrigoramos.votingsystem.service;

import com.rodrigoramos.votingsystem.dto.EmployeeDTO;
import com.rodrigoramos.votingsystem.dto.NewEmployeeDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import com.rodrigoramos.votingsystem.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee find(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Employee.class.getName()));
    }

    public Employee insert(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        Employee newEmployee = find(employee.getId());
        updateData(newEmployee, employee);
        return employeeRepository.save(newEmployee);
    }

    public void deleteEmployeeById(Integer id) {
        find(id);
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


/*
    public Employee findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + employee.getId() + ", Tipo: " + Employee.class.getName());
        }
        return employee;
    }
*/

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public Employee convertToModel(NewEmployeeDTO newEmployeeDTO) {
        return new Employee(null, newEmployeeDTO.getName(), newEmployeeDTO.getLastName(), newEmployeeDTO.getEmail(),
                newEmployeeDTO.getCpf());
    }

    public Employee convertToModel(EmployeeDTO employeeDTO) {
        return new Employee(null, employeeDTO.getName(), employeeDTO.getLastName(), employeeDTO.getEmail(), null);
    }



    private void updateData(Employee newEmployee, Employee employee) {
        if(employee.getName() != null) newEmployee.setName(employee.getName());
        if(employee.getLastName() != null) newEmployee.setLastName(employee.getLastName());
        if(employee.getEmail() != null) newEmployee.setEmail(employee.getEmail());
    }

}
