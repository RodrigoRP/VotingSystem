package com.rodrigoramos.votingsystem.service.validation;

import com.rodrigoramos.votingsystem.controller.exception.FieldMessage;
import com.rodrigoramos.votingsystem.dto.EmployeeDTO;
import com.rodrigoramos.votingsystem.model.Employee;
import com.rodrigoramos.votingsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeUpdateValidator implements ConstraintValidator<EmployeeUpdate, EmployeeDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(EmployeeUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(EmployeeDTO employeeDTO, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> fieldMessageList = new ArrayList<>();

        Employee employee = employeeRepository.findByEmail(employeeDTO.getEmail());
        if (employee != null && !employee.getId().equals(uriId)) {
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

