package org.dizzyfox734.todo_list.web;

import org.dizzyfox734.todo_list.service.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {

    private TodosService todosService;

    @Autowired
    public TodoController(TodosService todosService) {
        this.todosService = todosService;
    }

    @GetMapping("/todo")
    public String list(Model model) {
        model.addAttribute("todos", todosService.findAllDesc());

        return "todolist";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/todo";
    }
}
