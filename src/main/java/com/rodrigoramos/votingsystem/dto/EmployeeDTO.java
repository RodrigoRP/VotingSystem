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

    private Integer id;

    @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!")
    private String name;

    @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!")
    private String lastName;

    @Email(message = "E-mail inválido!")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @JsonIgnore
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        lastName = employee.getLastName();
        email = employee.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
