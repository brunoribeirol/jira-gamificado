package com.cesar.school.presentation.controller.projectmanagement;

import com.cesar.school.application.projectmanagement.TaskServiceImpl;
import com.cesar.school.core.projectmanagement.entity.Task;
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
    public ResponseEntity<Void> create(
            @PathVariable int projectId,
            @RequestBody @Valid CreateTaskRequest request
    ) {
        Task task = request.toDomain(new ProjectId(projectId));
        Integer memberId = request.getAssignedMemberId();

        taskService.addTaskToProject(new ProjectId(projectId), task, memberId);
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

    @PatchMapping("/{taskId}/complete") //http://localhost:8080/api/tasks/{taskId}/complete?memberId={memberId}
    public ResponseEntity<Void> complete(@PathVariable int taskId, @RequestParam int memberId) {
        taskService.completeTask(new TaskId(taskId), new MemberId(memberId));
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

    @GetMapping("/assignee/{memberId}")
    public ResponseEntity<List<TaskSummary>> getTasksByAssignee(@PathVariable int memberId) {
        List<Task> tasks = taskService.findByAssignee(new MemberId(memberId));
        List<TaskSummary> summaries = tasks.stream()
                .map(task -> new TaskSummary(
                        task.getId().getValue(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getKanbanColumn()
                ))
                .toList();
        return ResponseEntity.ok(summaries);
    }

}