package com.cesar.school.presentation.dto.projectmanagement.challenge;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.vo.ChallengeId;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.shared.MemberId;
import jakarta.validation.constraints.*;

import java.util.Date;

public class CreateChallengeRequest {

    @Positive(message = "O ID do desafio deve ser positivo.")
    public int id;

    @NotBlank(message = "O título é obrigatório.")
    public String title;

    public String description;

    @NotBlank(message = "Critérios são obrigatórios.")
    public String criteria;

    @Min(value = 0, message = "Pontos extras devem ser zero ou positivos.")
    public int extraPoints;

    @Positive(message = "O ID do criador deve ser positivo.")
    public int createdBy;

    @Positive(message = "O ID do projeto é obrigatório.")
    public int projectId;

    @NotNull(message = "A data limite é obrigatória.")
    public Date deadline;

    public Challenge toDomain() {
        return new Challenge(
                new ChallengeId(id),
                title,
                description,
                criteria,
                extraPoints,
                new MemberId(createdBy),
                new ProjectId(projectId),
                deadline
        );
    }
}
