package com.cesar.school.presentation.dto.projectmanagement.task;

public class TaskSummary {
    public int id;
    public String title;
    public String description;
    public String kanbanColumn;

    public TaskSummary(int id, String title, String description, String kanbanColumn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.kanbanColumn = kanbanColumn;
    }
}