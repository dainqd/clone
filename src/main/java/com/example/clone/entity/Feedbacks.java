package com.example.clone.entity;

import com.example.clone.util.Enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedbacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private Date createdAt;
    private String username;
    @Max(value=5, message = "Please re-enter the number of review stars ")
    private int star;
    @Column(columnDefinition="text")
    private String content;
    private Enums.FeedbackStatus status = Enums.FeedbackStatus.hide;
}
