package com.rodrigoramos.votingsystem.model;

import com.rodrigoramos.votingsystem.service.impl.VoteService;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Employee employee;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @Column(name = "restaurant_voted")
    private Integer restaurant_id;

    public Vote() {
    }

    public Vote(Employee employee) {
        this.setEmployee(employee);
    }

    @PrePersist
    private void onCreate() {
        this.dateUpdated = LocalDate.now(VoteService.ZONE_ID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        Vote vote = (Vote) o;
        return Objects.equals(getId(), vote.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
