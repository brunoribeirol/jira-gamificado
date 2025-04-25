package com.cesar.school.core.projectmanagement.service;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.projectmanagement.vo.TeamId;

public interface ProjectManagementService {

    /**
     * Cria um novo projeto com colunas padrão e o associa a um time.
     */
    Project createProject(ProjectId id, String name, String description, TeamId teamId);

    /**
     * Adiciona uma nova tarefa a um projeto existente.
     */
    void addTask(ProjectId projectId, Task task);

    /**
     * Move uma tarefa para outra coluna no quadro Kanban.
     */
    void moveTask(TaskId taskId, String targetColumn);

    /**
     * Marca uma tarefa como concluída e define a data de finalização.
     */
    void completeTask(TaskId taskId);

    /**
     * Adiciona um desafio personalizado ao projeto.
     */
    void addChallenge(ProjectId projectId, Challenge challenge);
}