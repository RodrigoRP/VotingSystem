package com.rodrigoramos.votingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.service.validation.EmployeeUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EmployeeUpdate
public class EmployeeDTO implements Serializable {

    @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!")
    private String name;

    @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!")
    private String lastName;

    @Email(message = "E-mail inválido!")
    private String email;


    public EmployeeDTO(@Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!") String name, @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!") String lastName, @Email(message = "E-mail inválido!") String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        name = employee.getName();
        lastName = employee.getLastName();
        email = employee.getEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
