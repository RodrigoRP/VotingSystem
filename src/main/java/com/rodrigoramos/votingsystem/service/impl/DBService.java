package com.rodrigoramos.votingsystem.service.impl;

import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.model.Restaurant;
import com.rodrigoramos.votingsystem.model.enums.Profile;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import com.rodrigoramos.votingsystem.repository.RestaurantRepository;
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
    @Autowired
    private RestaurantRepository restaurantRepository;

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


        Restaurant r1 = new Restaurant(null, "Carlo e Camilla in Segheria", "Barbecue",Boolean.FALSE);
        Restaurant r2 = new Restaurant(null,"A Despensa","Italiana, Internacional",Boolean.FALSE);
        Restaurant r3 = new Restaurant(null,"Euskalduna Studio","Europeia, Portuguesa",Boolean.FALSE);
        Restaurant r4 = new Restaurant(null,"éLeBê Entreparedes","Bar, Mediterrânea",Boolean.FALSE);
        Restaurant r5 = new Restaurant(null,"O Paparico","Pub com cerveja artesanal, Portuguesa", Boolean.FALSE);
        Restaurant r6 = new Restaurant(null,"éLeBê Baixa","Steakhouse, Latina",Boolean.FALSE);
        Restaurant r7 = new Restaurant(null,"A Escola by The Artist","Frutos do mar, Mediterrânea",Boolean.FALSE);
        Restaurant r8 = new Restaurant(null,"Essência Restaurante Vegetariano","Steakhouse, Europeia",Boolean.FALSE);
        Restaurant r9 = new Restaurant(null," Intrigo","Mediterrânea, Europeia",Boolean.FALSE);

        restaurantRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8, r9));

    }
}
