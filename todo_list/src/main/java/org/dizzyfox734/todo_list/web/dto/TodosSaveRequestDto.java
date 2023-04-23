package org.dizzyfox734.todo_list.web.dto;

import org.dizzyfox734.todo_list.domain.todo.Todos;

public class TodosSaveRequestDto {
    private Long id;
    private String content;
    private Boolean completed_fl;

    public TodosSaveRequestDto() {
        super();
    }

    public static class Builder {
        private String content;
        private Boolean completed_fl;

        public Builder content(String content) {
            this.content = content;
            return this;
        }
        public Builder completed(Boolean completed_fl) {
            this.completed_fl = completed_fl;
            return this;
        }

        public TodosSaveRequestDto build() {
            return new TodosSaveRequestDto(this);
        }
    }
    private TodosSaveRequestDto(Builder builder) {
        this.content = builder.content;
        this.completed_fl = builder.completed_fl;
    }

    public String getContent() {
        return content;
    }
    public Boolean getCompleted() {
        return completed_fl;
    }

    public Todos toEntity() {
        return new Todos.Builder(content, completed_fl)
                .build();
    }
}
