package com.cesar.school.infrastructure.persistence.mapper.teamsmembers;

import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.vo.FeedbackId;
import com.cesar.school.infrastructure.persistence.entity.teamsmembers.FeedbackEntity;

public class FeedbackMapper {

//    public static FeedbackEntity toEntity(Feedback domain) {
//        return new FeedbackEntity(
//                domain.getId().getValue(),
//                domain.getMessage(),
//                domain.getDate(),
//                domain.getGivenBy().getValue(),
//                domain.getReceivedBy().getValue(),
//                domain.getRelatedTask() != null ? domain.getRelatedTask().getValue() : null
//        );
//    }

    public static FeedbackEntity toEntity(Feedback feedback) {
        FeedbackEntity entity = new FeedbackEntity();

//        if (feedback.getId() != null) {
//            entity.setId(feedback.getId().getValue());
//        }

        entity.setMessage(feedback.getMessage());
        entity.setDate(feedback.getDate());
        entity.setGivenBy(feedback.getGivenBy().getValue());
        entity.setReceivedBy(feedback.getReceivedBy().getValue());
        entity.setRelatedTask(
                feedback.getRelatedTask() != null ? feedback.getRelatedTask().getValue() : null
        );

        return entity;
    }

    public static Feedback toDomain(FeedbackEntity entity) {
        return new Feedback(
                new FeedbackId(entity.getId()),
                entity.getMessage(),
                entity.getDate(),
                new MemberId(entity.getGivenBy()),
                new MemberId(entity.getReceivedBy()),
                entity.getRelatedTask() != null ? new TaskId(entity.getRelatedTask()) : null
        );
    }
}
