package com.cesar.school.presentation.dto.teamsmembers;

import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.shared.vo.FeedbackId;
import com.cesar.school.core.shared.vo.TaskId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class FeedbackRequest {

    public Integer id;

    @NotBlank
    public String message;

    @NotNull
    public Date date;

    @NotNull
    public Integer givenBy;

    @NotNull
    public Integer receivedBy;

    public Integer relatedTask;

    public Feedback toDomain() {
        // Se `id` é null, usamos o construtor de criação (sem id)
        if (id == null) {
            return new Feedback(
                    message,
                    date,
                    new MemberId(givenBy),
                    new MemberId(receivedBy),
                    relatedTask != null ? new TaskId(relatedTask) : null
            );
        }

        // Se `id` não for null, usamos o construtor com id (pode ser usado em updates, por exemplo)
        return new Feedback(
                new FeedbackId(id),
                message,
                date,
                new MemberId(givenBy),
                new MemberId(receivedBy),
                relatedTask != null ? new TaskId(relatedTask) : null
        );
    }
}
