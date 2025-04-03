package com.example.tododevelopproject.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{
    @Id
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Schedule schedule;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private String contents;

    public Comment() {}

    public Comment(Schedule schedule, User user, String contents) {
        this.schedule = schedule;
        this.user = user;
        this.contents = contents;
    }
}
