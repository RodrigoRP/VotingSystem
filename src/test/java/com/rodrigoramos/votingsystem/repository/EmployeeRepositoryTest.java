package com.rodrigoramos.votingsystem.repository;

import com.rodrigoramos.votingsystem.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Employee jose = new Employee(1, "Jose", "Silva", "jose@terra.com.br","42597178048","123456");

        //entityManager.persist(jose);
        //entityManager.flush();

        // when
        Employee found = employeeRepository.findByEmail(jose.getEmail());

        // then
        assertThat(found.getName())
                .isEqualTo(jose.getName());
    }



}
