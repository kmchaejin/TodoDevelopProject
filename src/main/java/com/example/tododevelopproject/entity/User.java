package com.example.tododevelopproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

//    @Column(nullable = false)
//    private String password;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {

    }
}
