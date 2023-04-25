package org.dizzyfox734.todo_list.web;

import org.dizzyfox734.todo_list.service.TodosService;
import org.dizzyfox734.todo_list.web.dto.TodosSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoApiController {
    private TodosService todosService;

    @Autowired
    public TodoApiController(TodosService todosService) {
        this.todosService = todosService;
    }

    @PostMapping("/api/todo")
    public Long save(@RequestBody TodosSaveRequestDto requestDto) {
        return todosService.save(requestDto);
    }
}
