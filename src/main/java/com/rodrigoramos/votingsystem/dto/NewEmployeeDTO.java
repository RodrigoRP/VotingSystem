package com.rodrigoramos.votingsystem.dto;

import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.service.validation.EmployeeInsert;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EmployeeInsert
public class NewEmployeeDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!")
    private String lastName;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "E-mail inválido!")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @CPF
    private String cpf;

    public NewEmployeeDTO() {
    }

    public NewEmployeeDTO(@NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!") String name, @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 2, max = 40, message = "O tamanho deve ser entre 2 e 40 caracteres!") String lastName, @NotEmpty(message = "Preenchimento obrigatório!") @Email(message = "E-mail inválido!") String email, @NotEmpty(message = "Preenchimento obrigatório!") @CPF String cpf) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
