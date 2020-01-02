package com.rodrigoramos.votingsystem.model;

import com.rodrigoramos.votingsystem.security.UserSS;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vote extends AbstractBaseEntity {

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    public Vote() {
    }

    public Vote(Integer id, LocalDate date, Employee employee) {
        super(id);
        this.date = date;
        this.employee = employee;
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.getDate(), vote.getEmployee());
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setUser(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "date=" + date +
                ", employee=" + employee +
                ", id=" + id +
                '}';
    }
}