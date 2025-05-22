package com.cesar.school.application.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.service.TaskService;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

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
        // 1. Atribui o membro responsável, se fornecido
        if (assignedMemberId != null) {
            task.assignTo(new MemberId(assignedMemberId));
        }

        // 2. Salva a tarefa no banco, delegando ao método padrão
        this.createTask(task); // ⬅️ exatamente como é feito com o createMember

        // 3. Busca o projeto e adiciona a tarefa nele
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        project.addTask(task);

        // 4. Persiste a atualização no projeto
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
