package org.dizzyfox734.todo_list.web;

import org.dizzyfox734.todo_list.service.TodosService;
import org.dizzyfox734.todo_list.web.dto.TodosSaveRequestDto;
import org.dizzyfox734.todo_list.web.dto.TodosUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/api/todo/{id}")
    public Long update(@PathVariable Long id, @RequestBody TodosUpdateRequestDto requestDto) {
        return todosService.update(id, requestDto);
    }

    @DeleteMapping("/api/todo/{id}")
    public Long delete(@PathVariable Long id) {
        todosService.delete(id);

        return id;
    }

    @PostMapping("/api/todo/complete/{id}")
    public Long complete(@PathVariable Long id) {
        return todosService.complete(id);
    }
}
