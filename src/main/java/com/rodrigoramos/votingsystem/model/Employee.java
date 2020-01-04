package com.rodrigoramos.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodrigoramos.votingsystem.model.enums.Profile;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Data
@Table(name = "users")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastName;
    private String email;
    private String cpf;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "profiles")
    private Set<Integer> profiles = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    public Employee() {
        addProfile(Profile.USER);
    }

    public Employee(Integer id) {
        this();
        this.setId(id);
        addProfile(Profile.USER);
    }

    public Employee(Integer id, String name, String lastName, String email, String cpf, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        addProfile(Profile.USER);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getCod());
    }

    @PrePersist
    public void onCreate() {
        Vote vote = new Vote();
        vote.setEmployee(this);
        this.setVote(vote);
    }
}
