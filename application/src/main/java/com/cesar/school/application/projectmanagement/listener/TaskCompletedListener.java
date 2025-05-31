package com.cesar.school.application.projectmanagement.listener;

import com.cesar.school.core.projectmanagement.event.TaskCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TaskCompletedListener {

    @EventListener
    public void onTaskCompleted(TaskCompletedEvent event) {
        String title = event.getTask().getTitle();
        Integer memberId = event.getMember().getId().getValue();

        System.out.println("🎉 Tarefa concluída: " + event.getTask().getTitle() +
                " por membro " + event.getMember().getId().getValue());

        // Aqui você pode:
        // - Enviar e-mail
        // - Atualizar outro sistema
        // - Dar recompensa
        // - Etc.
    }
}
