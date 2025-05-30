package com.cesar.school.application.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.service.TaskService;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;          // ← novo

    public TaskServiceImpl(TaskRepository taskRepository,
                           ProjectRepository projectRepository,
                           MemberRepository memberRepository) {   // ← novo param
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
    }

    /* ─────────────────────────────
       NOVO: desbloquear tarefa
       ───────────────────────────── */
    @Override
    @Transactional
    public void unlockTask(TaskId taskId, MemberId memberId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));

        int cost = task.getPoints();                 // custo = pontos da própria tarefa
        member.spendPoints(cost);                    // debita (lança exceção se saldo < custo)

        task.assignTo(memberId);                     // ou task.markUnlocked() se houver flag

        memberRepository.save(member);               // persiste novo saldo
        taskRepository.save(task);                   // persiste tarefa atribuída
    }

    /* ───────── métodos já existentes ───────── */

    @Override
    public void assignTaskToMember(TaskId taskId, MemberId memberId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        task.assignTo(memberId);
        taskRepository.save(task);
    }

    @Override
    public void updateTaskTitle(TaskId taskId, String newTitle) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        task.setTitle(newTitle);
        taskRepository.save(task);
    }

    @Override
    public void completeTask(TaskId taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        task.markAsCompleted();
        taskRepository.save(task);
    }

    @Override
    public void moveTaskToColumn(TaskId taskId, String column) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        task.moveToColumn(column);
        taskRepository.save(task);
    }

    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void delete(TaskId taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void addTaskToProject(ProjectId projectId, Task task, Integer assignedMemberId) {
        if (assignedMemberId != null) {
            task.assignTo(new MemberId(assignedMemberId));
        }
        this.createTask(task);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        project.addTask(task);
        projectRepository.save(project);
    }

    @Override
    public Optional<Task> getById(TaskId taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public List<Task> listByProject(ProjectId projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        return project.getTasks();
    }
}
