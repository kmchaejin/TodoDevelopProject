package com.example.tododevelopproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="name")
    private User user;

    private String title;

    private String contents;
}
