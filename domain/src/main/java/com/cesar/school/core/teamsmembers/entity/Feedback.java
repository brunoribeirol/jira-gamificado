package com.cesar.school.core.teamsmembers.entity;

import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.vo.FeedbackId;

import java.util.Date;

public class Feedback {
    private final FeedbackId id;
    private final String message;
    private final Date date;
    private final MemberId givenBy;
    private final MemberId receivedBy;
    private final TaskId relatedTask; // opcional

    public Feedback(FeedbackId id, String message, Date date, MemberId givenBy, MemberId receivedBy, TaskId relatedTask) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.givenBy = givenBy;
        this.receivedBy = receivedBy;
        this.relatedTask = relatedTask;
    }

    public FeedbackId getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return new Date(date.getTime()); // Imutabilidade: evitando alterações externas
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
