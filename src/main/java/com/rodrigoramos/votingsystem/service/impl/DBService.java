package com.rodrigoramos.votingsystem.service.impl;

import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.model.enums.Profile;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void instantiateTestDatabase() throws ParseException {
        Employee jose = new Employee(null, "Jose", "Silva", "jose@terra.com.br","42597178048",encoder.encode("123456"));
        Employee michael = new Employee(null, "Michael", "Jackson", "mic@terra.com.br","08108376092",encoder.encode("123456"));
        Employee nicolas = new Employee(null, "Nicolas", "Cage", "nic@terra.com.br","87710225039",encoder.encode("123456"));
        Employee robert = new Employee(null, "Robert", "de Niro", "rob@terra.com.br","02780251026",encoder.encode("123456"));
        Employee tom = new Employee(null, "Tom", "Jones", "jon@terra.com.br","93972440006",encoder.encode("123456"));
        Employee angelina = new Employee(null, "Angelina", "Jolie", "ang@terra.com.br","18007416005",encoder.encode("123456"));
        Employee fernanda = new Employee(null, "Fernanda", "Montenegro", "fer@terra.com.br","99740482066",encoder.encode("123456"));

        jose.addProfile(Profile.ADMIN);
        employeeRepository.saveAll(Arrays.asList(jose, michael, nicolas, robert, tom, angelina, fernanda));

    }


}
