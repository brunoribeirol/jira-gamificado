package com.cesar.school.application.projectmanagement.template;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.strategy.TaskScoreStrategy;
import com.cesar.school.core.projectmanagement.event.TaskCompletedEvent;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

class StandardTaskCompletionTest {

    @Test
    void shouldCompleteTaskAndPersistChanges() {
        // Mocks dos repositórios, publisher e estratégia
        TaskRepository taskRepository = mock(TaskRepository.class);
        MemberRepository memberRepository = mock(MemberRepository.class);
        ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);
        TaskScoreStrategy strategy = mock(TaskScoreStrategy.class);

        // Mocks do domínio
        Task task = mock(Task.class);
        Member member = mock(Member.class);
        MemberId memberId = mock(MemberId.class);

        // Configura comportamento dos mocks
        when(member.getId()).thenReturn(memberId);
        when(memberId.getValue()).thenReturn(42);  // valor fictício

        // Executa o método do template
        StandardTaskCompletion template = new StandardTaskCompletion(taskRepository, memberRepository, publisher, strategy);
        template.complete(task, strategy, member);

        // Verificações
        verify(task).complete(strategy);
        verify(taskRepository).save(task);
        verify(memberRepository).save(member);
        verify(publisher).publishEvent(any(TaskCompletedEvent.class));
    }
}
