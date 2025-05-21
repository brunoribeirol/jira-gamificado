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
    private int id;

    private String message;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "given_by")
    private int givenBy;

    @Column(name = "received_by")
    private int receivedBy;

    @Column(name = "related_task")
    private Integer relatedTask; // pode ser nulo

    public FeedbackEntity(int id, String message, Date date, int givenBy, int receivedBy, Integer relatedTask) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.givenBy = givenBy;
        this.receivedBy = receivedBy;
        this.relatedTask = relatedTask;
    }
}
