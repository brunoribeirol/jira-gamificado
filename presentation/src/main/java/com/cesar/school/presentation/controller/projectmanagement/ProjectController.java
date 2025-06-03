package com.cesar.school.presentation.controller.projectmanagement;

import com.cesar.school.application.projectmanagement.ProjectServiceImpl;
import com.cesar.school.presentation.dto.projectmanagement.task.CreateTaskRequest;
import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.presentation.dto.projectmanagement.project.CreateProjectRequest;
import com.cesar.school.presentation.dto.projectmanagement.project.ProjectResponse;
import com.cesar.school.presentation.dto.projectmanagement.task.TaskSummary;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAll() {
        List<Project> projects = projectService.getAll();
        List<ProjectResponse> responses = projects.stream()
                .map(ProjectResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable int id) {
        return projectService.getById(new ProjectId(id))
                .map(ProjectResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/new-project")
    public ResponseEntity<ProjectResponse> create(@RequestBody @Valid CreateProjectRequest request) {
        Project created = projectService.createProject(request.toDomain());
        return ResponseEntity.status(201).body(ProjectResponse.fromDomain(created));
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Void> addTaskToProject(
            @PathVariable int projectId,
            @RequestBody @Valid CreateTaskRequest request
    ) {
        Task task = request.toDomain(new ProjectId(projectId));
        projectService.addTaskToProject(new ProjectId(projectId), task, request.getAssignedMemberId()); // ✅ correto
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskSummary>> listTaskSummaries(@PathVariable int projectId) {
        return projectService.getById(new ProjectId(projectId))
                .map(project -> {
                    List<TaskSummary> summaries = new ArrayList<>();
                    Iterator<Task> iterator = project.iterator();
                    while (iterator.hasNext()) {
                        Task task = iterator.next();
                        summaries.add(new TaskSummary(
                                task.getId().getValue(),
                                task.getTitle(),
                                task.getDescription(),
                                task.getKanbanColumn()
                        ));
                    }
                    return ResponseEntity.ok(summaries);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        projectService.delete(new ProjectId(id));
        return ResponseEntity.noContent().build();
    }
}
