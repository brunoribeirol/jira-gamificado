package com.cesar.school.presentation.dto.teamsmembers;

import com.cesar.school.core.teamsmembers.entity.Feedback;

import java.util.Date;

public class FeedbackResponse {

    public int id;
    public String message;
    public Date date;
    public int givenBy;
    public Integer relatedTaskId;

    public static FeedbackResponse fromDomain(Feedback feedback) {
        FeedbackResponse response = new FeedbackResponse();
        response.id = feedback.getId().getValue();
        response.message = feedback.getMessage();
        response.date = feedback.getDate();
        response.givenBy = feedback.getGivenBy().getValue();
        response.relatedTaskId = feedback.getRelatedTask() != null ? feedback.getRelatedTask().getValue() : null;
        return response;
    }
}
