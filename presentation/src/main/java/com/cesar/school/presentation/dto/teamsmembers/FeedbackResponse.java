package com.cesar.school.presentation.dto.teamsmembers;

import com.cesar.school.core.teamsmembers.entity.Feedback;

import java.util.Date;

public class FeedbackResponse {

    public int id;
    public String message;
    public Date date;
    public int givenBy;
    public int receivedBy;
    public Integer relatedTask; // pode ser null

    public static FeedbackResponse fromDomain(Feedback feedback) {
        FeedbackResponse dto = new FeedbackResponse();
        dto.id = feedback.getId().getValue();
        dto.message = feedback.getMessage();
        dto.date = feedback.getDate();
        dto.givenBy = feedback.getGivenBy().getValue();
        dto.receivedBy = feedback.getReceivedBy().getValue();
        dto.relatedTask = feedback.getRelatedTask() != null ? feedback.getRelatedTask().getValue() : null;
        return dto;
    }
}
