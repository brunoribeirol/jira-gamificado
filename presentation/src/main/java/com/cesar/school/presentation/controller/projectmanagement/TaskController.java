package com.cesar.school.presentation.controller.projectmanagement;

import com.cesar.school.application.projectmanagement.TaskServiceImpl;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.presentation.dto.projectmanagement.task.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/project/{projectId}")
    public ResponseEntity<Void> create(@PathVariable int projectId, @RequestBody @Valid CreateTaskRequest request) {
        taskService.addTaskToProject(new ProjectId(projectId), request.toDomain());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable int id) {
        return taskService.getById(new TaskId(id))
                .map(TaskResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<Void> assign(@PathVariable int id, @RequestBody @Valid AssignMemberRequest request) {
        taskService.assignTaskToMember(new TaskId(id), new MemberId(request.memberId));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/title")
    public ResponseEntity<Void> updateTitle(@PathVariable int id, @RequestBody @Valid UpdateTaskTitleRequest request) {
        taskService.updateTaskTitle(new TaskId(id), request.newTitle);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/column")
    public ResponseEntity<Void> moveToColumn(@PathVariable int id, @RequestBody @Valid MoveTaskRequest request) {
        taskService.moveTaskToColumn(new TaskId(id), request.column);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable int id) {
        taskService.completeTask(new TaskId(id));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        taskService.delete(new TaskId(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponse>> listByProject(@PathVariable int projectId) {
        List<TaskResponse> responses = taskService.listByProject(new ProjectId(projectId))
                .stream()
                .map(TaskResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}