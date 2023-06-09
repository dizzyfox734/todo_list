package org.dizzyfox734.todo_list.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dizzyfox734.todo_list.domain.todo.Todos;
import org.dizzyfox734.todo_list.domain.todo.TodosRepository;
import org.dizzyfox734.todo_list.service.TodosService;
import org.dizzyfox734.todo_list.web.dto.TodosSaveRequestDto;
import org.dizzyfox734.todo_list.web.dto.TodosUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodosApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TodosRepository todosRepository;

    @Autowired
    TodosService todosService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        todosRepository.deleteAll();
    }

    @Test
    public void save_todos() throws Exception {
        // given
        String content = "content";
        Boolean completed_fl = false;
        TodosSaveRequestDto requestDto = new TodosSaveRequestDto.Builder()
                .content(content)
                .completed_fl(completed_fl)
                .build();
        String url = "http://localhost:" + port + "/api/todo";

        // when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<Todos> all = todosRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getCompleted_fl()).isEqualTo(completed_fl);
    }

    @Test
    public void delete_todos() throws Exception {
        // given
        String content = "content";
        Boolean completed_fl = false;
        Long todosId = todosRepository.save(new Todos.Builder(content, completed_fl).build()).getId();
        String url = "http://localhost:" + port + "/api/todo/" + todosId;

        // when
        mvc.perform(delete(url))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertThatThrownBy(() -> todosService.findById(todosId)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void update_todos() throws Exception {
        // given
        String content = "content";
        Boolean completed_fl = false;
        Long todosId = todosRepository.save(new Todos.Builder(content, completed_fl).build()).getId();

        String url = "http://localhost:" + port + "/api/todo/" + todosId;
        String updatedContent = "content2";
        Boolean updatedCompleted_fl = true;
        TodosUpdateRequestDto updateRequestDto = new TodosUpdateRequestDto.Builder()
                .content(updatedContent)
                .completed_fl(updatedCompleted_fl)
                .build();

        // when
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(updateRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        List<Todos> all = todosRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(updatedContent);
        assertThat(all.get(0).getCompleted_fl()).isEqualTo(updatedCompleted_fl);
    }

    @Test
    public void complete_todos() throws Exception {
        // given
        String content = "content";
        Boolean completed_fl = false;
        Long todosId = todosRepository.save(new Todos.Builder(content, completed_fl).build()).getId();

        String url = "http://localhost:" + port + "/api/todo/complete/" + todosId;

        // when
        mvc.perform(post(url))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        List<Todos> all = todosRepository.findAll();
        assertThat(all.get(0).getCompleted_fl()).isEqualTo(true);

    }
}
