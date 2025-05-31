package com.cesar.school.application.projectmanagement.template;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.template.TaskCompletionTemplate;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import com.cesar.school.core.projectmanagement.event.TaskCompletedEvent;

public class StandardTaskCompletion extends TaskCompletionTemplate {

    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher eventPublisher;

    public StandardTaskCompletion(TaskRepository taskRepository, MemberRepository memberRepository, ApplicationEventPublisher eventPublisher) {
        this.taskRepository = taskRepository;
        this.memberRepository = memberRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    protected void notifyMember(Task task, Member member) {
        eventPublisher.publishEvent(new TaskCompletedEvent(task, member));
    }

    @Override
    protected void persist(Task task, Member member) {
        taskRepository.save(task);
        memberRepository.save(member);
    }
}

