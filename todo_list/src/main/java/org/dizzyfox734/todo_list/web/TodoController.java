package org.dizzyfox734.todo_list.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {

    @GetMapping("/todo")
    public String list() {
        return "todolist";
    }
}
