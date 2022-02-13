package com.cinekodigo.cinekodigo.entity;


import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Movies(String title, String length) {
        this.title = title;
        this.length = length;
    }

    @Column(name = "title")
    private String title;
    @Column(name = "length")
    private String length;
}