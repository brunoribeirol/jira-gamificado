package com.cesar.school.presentation.dto.projectmanagement.challenge;

import com.cesar.school.core.projectmanagement.entity.Challenge;

import java.util.Date;

public class ChallengeResponse {

    public int id;
    public String title;
    public String description;
    public String criteria;
    public int extraPoints;
    public int createdBy;
    public int projectId;
    public Date deadline;

    public static ChallengeResponse fromDomain(Challenge challenge) {
        ChallengeResponse response = new ChallengeResponse();
        response.id = challenge.getId().getValue();
        response.title = challenge.getTitle();
        response.description = challenge.getDescription();
        response.criteria = challenge.getCriteria();
        response.extraPoints = challenge.getExtraPoints();
        response.createdBy = challenge.getCreatedBy().getValue();
        response.projectId = challenge.getProjectId().getValue();
        response.deadline = challenge.getDeadline();
        return response;
    }
}
