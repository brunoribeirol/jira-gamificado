package com.cesar.school.application.projectmanagement;

import com.cesar.school.application.projectmanagement.template.StandardTaskCompletion;
import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.service.TaskService;
import com.cesar.school.core.projectmanagement.strategy.TaskScoreStrategy;
import com.cesar.school.core.projectmanagement.template.TaskCompletionTemplate;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.core.teamsmembers.vo.TeamId;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final TaskScoreStrategy taskScoreStrategy;
    private final TaskCompletionTemplate taskCompletion;
    private final MemberTeamServiceImpl memberTeamService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           ProjectRepository projectRepository,
                           MemberRepository memberRepository,
                           TaskScoreStrategy taskScoreStrategy,
                           ApplicationEventPublisher eventPublisher,
                           MemberTeamServiceImpl memberTeamService) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
        this.taskScoreStrategy = taskScoreStrategy;
        this.taskCompletion = new StandardTaskCompletion(taskRepository, memberRepository, eventPublisher, taskScoreStrategy);
        this.memberTeamService = memberTeamService;
    }


    @Override
    @Transactional
    public void unlockTask(TaskId taskId, MemberId memberId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));

        int cost = task.getPoints();                 // custo = pontos da própria tarefa
//        member.spendPoints(cost);                    // debita (lança exceção se saldo < custo)
//
//        task.assignTo(memberId);                     // ou task.markUnlocked() se houver flag

        task.unlockBy(member);

        memberRepository.save(member);               // persiste novo saldo
        taskRepository.save(task);                   // persiste tarefa atribuída
    }


    @Override
    public void assignTaskToMember(TaskId taskId, MemberId memberId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        Project project = projectRepository.findById(task.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));

        if (!member.belongsToTeam(project.getTeamId())) {
            throw new IllegalArgumentException("Membro não pertence ao time do projeto.");
        }

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
    public void completeTask(TaskId taskId, MemberId memberId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + taskId.getValue()));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId.getValue()));

        // valida se o membro pertence ao mesmo time do projeto
        Project project = projectRepository.findById(task.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found: " + task.getProjectId().getValue()));

        if (!member.belongsToTeam(project.getTeamId())) {
            throw new IllegalArgumentException("Membro " + memberId.getValue()
                    + " não pertence ao time desse projeto " + project.getTeamId().getValue());
        }

        taskCompletion.complete(task, taskScoreStrategy, member);
    }

    @Override
    public void moveTaskToColumn(TaskId taskId, String column, MemberId memberId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        Project project = projectRepository.findById(task.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        // Converte o TeamId do projeto para o TeamId do time
        TeamId teamId = new TeamId(project.getTeamId().getValue());

        // Verifica se o membro é um dos responsáveis ou se é o líder do time
        boolean isAssignee = task.getAssignees().stream()
                .anyMatch(assignee -> assignee.equals(memberId));
        boolean isTeamLeader = memberTeamService.isTeamLeader(memberId, teamId);

        if (!isAssignee && !isTeamLeader) {
            throw new IllegalArgumentException("Você não pode mover tarefas que não são suas e não é líder do time");
        }

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

        // ⛔ Primeiro valida se o projeto existe!
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        taskRepository.save(task, projectId);

        project.addTask(task);
        projectRepository.save(project);
    }


    @Override
    public Optional<Task> getById(TaskId taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public List<Task> listByProject(ProjectId projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    @Override
    public List<Task> findByAssignee(MemberId memberId) {
        return taskRepository.findByAssignee(memberId);
    }

}