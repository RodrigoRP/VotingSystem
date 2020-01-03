package com.rodrigoramos.votingsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date lunchDate;

    private Integer restaurant;

    public Winner() {
    }

}
