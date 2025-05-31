package com.cesar.school.application.projectmanagement.template;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.template.TaskCompletionTemplate;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;

public class StandardTaskCompletion extends TaskCompletionTemplate {

    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    public StandardTaskCompletion(TaskRepository taskRepository, MemberRepository memberRepository) {
        this.taskRepository = taskRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    protected void notifyMember(Task task, Member member) {
        System.out.println("Task '" + task.getTitle() + "' completed by member " + member.getId().getValue());
    }

    @Override
    protected void persist(Task task, Member member) {
        taskRepository.save(task);
        memberRepository.save(member);
    }
}

