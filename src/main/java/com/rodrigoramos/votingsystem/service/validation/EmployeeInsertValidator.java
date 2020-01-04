package com.rodrigoramos.votingsystem.service.validation;

import com.rodrigoramos.votingsystem.controller.exception.FieldMessage;
import com.rodrigoramos.votingsystem.dto.EmployeeNewDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInsertValidator implements ConstraintValidator<EmployeeInsert, EmployeeNewDTO> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(EmployeeInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(EmployeeNewDTO employeeNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessageList = new ArrayList<>();

        Employee employee = employeeRepository.findByEmail(employeeNewDTO.getEmail());
        if(employee != null) {
            fieldMessageList.add(new FieldMessage("email", "O e-mail informado já foi cadastrado!"));
        }

        for (FieldMessage e : fieldMessageList) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return fieldMessageList.isEmpty();
    }
}
