package com.cinekodigo.cinekodigo.entity;

import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number")
    private long mobile_number;
    public Users(String name, String email, long mobile_number) {
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
    }
    @Override
    public String toString() {
        return "Usuarios [id=" + id + ", name=" + name + ", desc=" + email + ", mobile number=" + mobile_number + "]";
    }
}