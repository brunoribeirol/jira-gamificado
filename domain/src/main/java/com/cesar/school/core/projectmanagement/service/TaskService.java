package com.cesar.school.core.projectmanagement.service;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import java.util.HashMap;
import java.util.Map;

public class TaskService {

    private final Map<TaskId, Task> tasks = new HashMap<>();

    // Método para pegar a tarefa pela ID
    public Task getTask(TaskId taskId) {
        return tasks.get(taskId);
    }

    // Método para atualizar o título da tarefa
    public void updateTaskTitle(TaskId taskId, String newTitle) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
        if (newTitle == null || newTitle.isBlank()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório");
        }
        task.setTitle(newTitle); // Método de setter na Task
    }

    // Método para atribuir um membro à tarefa
    public void assignTaskToMember(TaskId taskId, MemberId memberId) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
        task.assignTo(memberId); // Método na Task para atribuir o membro
    }

    // Método para deletar uma tarefa
    public void deleteTask(TaskId taskId) {
        tasks.remove(taskId); // Remove a tarefa pelo ID
    }

    // Método de simulação para adicionar tarefas (apenas para fins de teste)
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }
}
