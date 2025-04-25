package com.cesar.school.core.teamsmembers.entity;


import com.cesar.school.core.teamsmembers.vo.FeedbackId;
import com.cesar.school.core.teamsmembers.vo.MemberId;
import com.cesar.school.core.teamsmembers.vo.TaskId;

import java.time.LocalDate;
import java.util.Optional;

public class Feedback {
    private FeedbackId id;
    private String message;
    private LocalDate date;
    private MemberId givenBy;
    private MemberId receivedBy;
    private Optional<TaskId> relatedTask;
}
