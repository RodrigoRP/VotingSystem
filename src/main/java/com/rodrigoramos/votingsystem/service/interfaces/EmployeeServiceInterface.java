package com.rodrigoramos.votingsystem.service.interfaces;

import com.rodrigoramos.votingsystem.model.Employee;

import java.util.Optional;

public interface EmployeeServiceInterface extends ServiceInterface<Employee> {

    Employee findById(Integer id);


}
