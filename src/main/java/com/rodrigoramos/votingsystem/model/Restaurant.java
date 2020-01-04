package com.rodrigoramos.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "not blank restaurant name")
    @Column(unique = true)
    private String name;
    private String description;
    private Boolean alreadyVoted;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String description, Boolean alreadyVoted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alreadyVoted = alreadyVoted;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }


}
