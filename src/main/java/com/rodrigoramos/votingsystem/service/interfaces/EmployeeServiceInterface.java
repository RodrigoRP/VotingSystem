package com.rodrigoramos.votingsystem.service.interfaces;

import com.rodrigoramos.votingsystem.model.Employee;

import java.util.List;

public interface EmployeeServiceInterface extends ServiceInterface<Employee> {

    Employee findByEmail(String email);

    Employee findById(Integer id);

    Employee insert(Employee employee);

    Employee update(Employee employee);

    void deleteEmployeeById(Integer id);

    List<Employee> findAll();


}
