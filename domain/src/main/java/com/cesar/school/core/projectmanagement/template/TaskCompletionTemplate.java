package com.cesar.school.core.projectmanagement.template;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.strategy.TaskScoreStrategy;
import com.cesar.school.core.teamsmembers.entity.Member;

public abstract class TaskCompletionTemplate {

    public final void complete(Task task, Member member) {
        validateTask(task);
        calculateScore(task);
        notifyMember(task, member);
        persist(task, member);
    }

    protected void validateTask(Task task) {
        if (task.isCompleted()) {
            throw new IllegalStateException("Task is already completed.");
        }
    }

    protected abstract void calculateScore(Task task);

    protected abstract void notifyMember(Task task, Member member);

    protected abstract void persist(Task task, Member member);
}
