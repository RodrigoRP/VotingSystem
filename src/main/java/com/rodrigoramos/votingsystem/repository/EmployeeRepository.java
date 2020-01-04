package com.rodrigoramos.votingsystem.repository;

import com.rodrigoramos.votingsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Transactional(readOnly = true)
    Employee findByEmail(String email);


}
