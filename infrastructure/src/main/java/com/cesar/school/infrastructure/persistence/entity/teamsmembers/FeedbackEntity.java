package com.cesar.school.infrastructure.persistence.entity.teamsmembers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String message;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(name = "given_by", nullable = false)
    private int givenBy;

    @Column(name = "received_by", nullable = false)
    private int receivedBy;

    @Column(name = "related_task")
    private Integer relatedTask; // pode ser nulo

    public FeedbackEntity(Integer id, String message, Date date, int givenBy, int receivedBy, Integer relatedTask) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.givenBy = givenBy;
        this.receivedBy = receivedBy;
        this.relatedTask = relatedTask;
    }

    // Construtor alternativo usado para salvar (sem id)
    public FeedbackEntity(String message, Date date, int givenBy, int receivedBy, Integer relatedTask) {
        this.message = message;
        this.date = date;
        this.givenBy = givenBy;
        this.receivedBy = receivedBy;
        this.relatedTask = relatedTask;
    }
}