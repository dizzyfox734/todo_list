package org.dizzyfox734.todo_list.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TodosControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void load_todoPage() {
        // when
        String body = this.restTemplate.getForObject("/todo", String.class);

        // then
        assertThat(body).contains("Todo list");
    }
}
