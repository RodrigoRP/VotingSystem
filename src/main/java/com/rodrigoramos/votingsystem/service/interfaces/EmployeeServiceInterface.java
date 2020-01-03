package com.rodrigoramos.votingsystem.service.interfaces;

import com.rodrigoramos.votingsystem.model.Employee;

public interface EmployeeServiceInterface extends ServiceInterface<Employee> {

    Employee findByEmail(String email);

}
