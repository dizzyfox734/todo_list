package org.dizzyfox734.todo_list.domain.todos;

import org.dizzyfox734.todo_list.domain.todo.Todos;
import org.dizzyfox734.todo_list.domain.todo.TodosRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TodosRepositoryTest {
    @Autowired
    TodosRepository todosRepository;

    @After
    public void cleanup() {
        todosRepository.deleteAll();
    }

    @Test
    public void call_save_todo() {
        // given
        String content = "잠 자기";
        todosRepository.save(new Todos.Builder(content, Boolean.FALSE).build());

        // when
        List<Todos> todosList = todosRepository.findAll();

        // then
        Todos todos = todosList.get(0);

        System.out.println(">>>>>>>>>>>>> " + todos.getContent() + ", " + (todos.getCompleted_fl() ? "완료":"미완") + "<<<<<<<<<<<<");

        assertThat(todos.getContent()).isEqualTo(content);
        assertThat(todos.getCompleted_fl()).isEqualTo(Boolean.FALSE);
    }
}
