package org.dizzyfox734.todo_list.web.dto;

import org.dizzyfox734.todo_list.domain.todo.Todos;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TodosResponseDtoTest {

    @Test
    public void dto_test() {
        // given
        String content = "test";
        Boolean completed_fl = true;

        // when
        Todos todo = new Todos.Builder(content, completed_fl).build();
        TodosResponseDto dto = new TodosResponseDto(todo);

        // then
        assertThat(dto.getContent()).isEqualTo(content);
        assertThat(dto.getCompleted()).isEqualTo(completed_fl);
    }
}
