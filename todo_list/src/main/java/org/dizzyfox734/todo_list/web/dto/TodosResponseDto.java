package org.dizzyfox734.todo_list.web.dto;

import org.dizzyfox734.todo_list.domain.todo.Todos;

public class TodosResponseDto {
    private Long id;
    private String content;
    private Boolean completed_fl;

    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public boolean getCompleted() {
        return completed_fl;
    }

    public TodosResponseDto (Todos entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.completed_fl = entity.getCompleted_fl();
    }
}
