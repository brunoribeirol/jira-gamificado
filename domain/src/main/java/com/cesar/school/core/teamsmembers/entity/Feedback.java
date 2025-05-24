package com.cesar.school.core.teamsmembers.entity;

import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.vo.FeedbackId;

import java.util.Date;
import java.util.Objects;

public class Feedback {
    private final FeedbackId id;
    private final String message;
    private final Date date;
    private final MemberId givenBy;
    private final MemberId receivedBy;
    private final TaskId relatedTask; // opcional

    // Usado quando o Feedback já existe (carregado do banco)
    public Feedback(FeedbackId id, String message, Date date, MemberId givenBy, MemberId receivedBy, TaskId relatedTask) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.message = Objects.requireNonNull(message, "message must not be null");
        this.date = Objects.requireNonNull(date, "date must not be null");
        this.givenBy = Objects.requireNonNull(givenBy, "givenBy must not be null");
        this.receivedBy = Objects.requireNonNull(receivedBy, "receivedBy must not be null");
        this.relatedTask = relatedTask;
    }

    // Usado para criar novo Feedback (sem id ainda)
    public Feedback(String message, Date date, MemberId givenBy, MemberId receivedBy, TaskId relatedTask) {
        this.id = null;
        this.message = Objects.requireNonNull(message, "message must not be null");
        this.date = Objects.requireNonNull(date, "date must not be null");
        this.givenBy = Objects.requireNonNull(givenBy, "givenBy must not be null");
        this.receivedBy = Objects.requireNonNull(receivedBy, "receivedBy must not be null");
        this.relatedTask = relatedTask;
    }

    public FeedbackId getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return new Date(date.getTime()); // proteção contra mutabilidade externa
    }

    public MemberId getGivenBy() {
        return givenBy;
    }

    public MemberId getReceivedBy() {
        return receivedBy;
    }

    public TaskId getRelatedTask() {
        return relatedTask;
    }
}
