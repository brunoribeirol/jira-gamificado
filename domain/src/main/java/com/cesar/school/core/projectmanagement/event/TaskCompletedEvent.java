package com.cesar.school.core.projectmanagement.event;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.teamsmembers.entity.Member;

public class TaskCompletedEvent {
    private final Task task;
    private final Member member;

    public TaskCompletedEvent(Task task, Member member) {
        this.task = task;
        this.member = member;
    }

    public Task getTask() { return task; }
    public Member getMember() { return member; }
}