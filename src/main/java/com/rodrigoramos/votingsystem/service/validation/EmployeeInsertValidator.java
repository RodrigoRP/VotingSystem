package com.rodrigoramos.votingsystem.service.validation;

import com.rodrigoramos.votingsystem.controller.exception.FieldMessage;
import com.rodrigoramos.votingsystem.dto.NewEmployeeDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeInsertValidator implements ConstraintValidator<EmployeeInsert, NewEmployeeDTO> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(EmployeeInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(NewEmployeeDTO newEmployeeDTO, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessageList = new ArrayList<>();

        Employee employee = employeeRepository.findByEmail(newEmployeeDTO.getEmail());
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
