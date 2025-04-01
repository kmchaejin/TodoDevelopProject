package com.example.tododevelopproject.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동 생성
    private Long id;

//    @ManyToOne
//    @JoinColumn(name="name")
//    private User user;

    private String title;

    private String contents;

    public Schedule() {}

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
