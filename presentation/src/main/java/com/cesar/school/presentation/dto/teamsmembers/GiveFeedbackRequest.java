package com.cesar.school.presentation.dto.teamsmembers;

import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.vo.FeedbackId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class GiveFeedbackRequest {

    @NotNull(message = "O ID do feedback é obrigatório.")
    public Integer id;

    @NotBlank(message = "A mensagem do feedback é obrigatória.")
    public String message;

    @NotNull(message = "O ID de quem está enviando o feedback é obrigatório.")
    public Integer givenBy;

    @NotNull(message = "O ID de quem está recebendo o feedback é obrigatório.")
    public Integer receivedBy;

    public Integer relatedTaskId;

    public Feedback toDomain() {
        return new Feedback(
                new FeedbackId(id),
                message,
                new Date(),
                new MemberId(givenBy),
                new MemberId(receivedBy),
                relatedTaskId != null ? new TaskId(relatedTaskId) : null
        );
    }
}
