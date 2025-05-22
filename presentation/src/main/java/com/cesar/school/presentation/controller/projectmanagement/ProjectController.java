package com.cesar.school.presentation.controller.projectmanagement;

import com.cesar.school.application.projectmanagement.ProjectServiceImpl;
import com.cesar.school.presentation.dto.projectmanagement.task.CreateTaskRequest;
import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.presentation.dto.projectmanagement.project.CreateProjectRequest;
import com.cesar.school.presentation.dto.projectmanagement.project.ProjectResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateProjectRequest request) {
        Project project = request.toDomain();
        projectService.create(project.getId(), project.getName(), project.getDescription(), project.getTeamId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable int id) {
        return projectService.getById(new ProjectId(id))
                .map(ProjectResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Void> addTaskToProject(
            @PathVariable int projectId,
            @RequestBody @Valid CreateTaskRequest request
    ) {
        Task task = request.toDomain();
        projectService.addTaskToProject(new ProjectId(projectId), task, request.getAssignedMemberId()); // ✅ correto
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        projectService.delete(new ProjectId(id));
        return ResponseEntity.noContent().build();
    }
}
