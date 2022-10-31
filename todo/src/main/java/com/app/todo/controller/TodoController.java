package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.repository.TodoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("username")
public class TodoController {
    private TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    private String getLoggedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "todos", method = RequestMethod.GET)
    public String getAllTodos(ModelMap model) {
//        List<Todo> todos = service.findByUsername(getLoggedUsername());
        List<Todo> todos = repository.findByUsername(getLoggedUsername());

        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodos(ModelMap model) {
        Todo todo = Todo.builder()
                .id(0L)
                .username(getLoggedUsername())
                .description("")
                .done(false)
                .build();

        model.put("todo", todo);

        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodos(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUsername(getLoggedUsername());
        repository.save(todo);

        return "redirect:todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam Long id) {
        repository.deleteById(id);

        return "redirect:todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam Long id, ModelMap model) {
        Todo todo = repository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUsername(getLoggedUsername());
        repository.save(todo);

        return "redirect:todos";
    }
}
