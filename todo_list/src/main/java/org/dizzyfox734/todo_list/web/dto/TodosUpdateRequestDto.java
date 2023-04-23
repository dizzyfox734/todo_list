package org.dizzyfox734.todo_list.web.dto;

public class TodosUpdateRequestDto {
    private Long id;
    private String content;
    private Boolean completed_fl;

    public TodosUpdateRequestDto() {
        super();
    }

    public static class Builder {
        private String content;
        private Boolean completed_fl;

        public TodosUpdateRequestDto.Builder content(String content) {
            this.content = content;
            return this;
        }
        public TodosUpdateRequestDto.Builder completed(Boolean completed_fl) {
            this.completed_fl = completed_fl;
            return this;
        }

        public TodosUpdateRequestDto build() {
            return new TodosUpdateRequestDto(this);
        }
    }
    private TodosUpdateRequestDto(Builder builder) {
        this.content = builder.content;
        this.completed_fl = builder.completed_fl;
    }

    public String getContent() {
        return content;
    }
    public Boolean getCompleted() {
        return completed_fl;
    }
}
