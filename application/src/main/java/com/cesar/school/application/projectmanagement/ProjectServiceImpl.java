package com.cesar.school.application.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.service.ProjectService;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;
import com.cesar.school.core.shared.MemberId;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository    = taskRepository;
    }

//    @Override
//    public Project create(ProjectId projectId, String name, String description, TeamId teamId) {
//        if (name == null || name.trim().isEmpty()) {
//            throw new IllegalArgumentException("Nome do projeto é obrigatório");
//        }
//
//        Project project = new Project(projectId, name, description, teamId);
//        projectRepository.save(project);
//        return project;
//    }

    @Override
    public void createProject(Project project) {
        if (project.getName() == null || project.getName().isBlank()) {
            throw new IllegalArgumentException("Nome do projeto é obrigatório");
        }

        projectRepository.save(project);
    }

    public void addTaskToProject(ProjectId projectId, Task task, int assignedMemberId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        task.assignTo(new MemberId(assignedMemberId)); // associa o membro à tarefa

        project.addTask(task); // se quiser manter a lista de tarefas no projeto
        taskRepository.save(task); // salva a tarefa no banco
        projectRepository.save(project); // salva o projeto atualizado
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getById(ProjectId id) {
        return projectRepository.findById(id);
    }

    @Override
    public void update(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void delete(ProjectId id) {
        projectRepository.deleteById(id);
    }
}
