package com.cesar.school.application.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.repository.ProjectRepository;
import com.cesar.school.core.projectmanagement.repository.TaskRepository;
import com.cesar.school.core.projectmanagement.service.ProjectService;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.shared.TeamId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              TaskRepository taskRepository,
                              MemberRepository memberRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Project createProject(Project project) {
        if (project.getName() == null || project.getName().isBlank()) {
            throw new IllegalArgumentException("Nome do projeto é obrigatório");
        }

        return projectRepository.save(project);
    }

    public void addTaskToProject(ProjectId projectId, Task task, Integer assignedMemberId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        if (assignedMemberId != null) {
            MemberId memberId = new MemberId(assignedMemberId);

            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado"));

            if (!member.getTeamId().equals(project.getTeamId())) {
                throw new IllegalArgumentException("Membro não pertence ao time do projeto.");
            }

            task.assignTo(memberId);
        }

        project.addTask(task);
        taskRepository.save(task);
        projectRepository.save(project);
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
