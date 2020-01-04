package com.rodrigoramos.votingsystem.model;

import com.rodrigoramos.votingsystem.service.impl.VoteService;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Employee employee;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @Column(name = "restaurant_voted")
    private Integer restaurant_id;

    public Vote() {
    }

    public Vote(Integer id, Employee employee, LocalDate dateUpdated, Integer restaurant_id) {
        this.id = id;
        this.employee = employee;
        this.dateUpdated = dateUpdated;
        this.restaurant_id = restaurant_id;
    }

    public Vote(Employee employee) {
        this.setEmployee(employee);
    }

    @PrePersist
    private void onCreate() {
        this.dateUpdated = LocalDate.now(VoteService.ZONE_ID);
    }

}
