package com.app.todo.service;

import com.app.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static Long todosCount = 0L;

    static {
        todos.add(
            Todo.builder()
                .id(++todosCount)
                .username("filipefrs")
                .description("Learn Spring Boot")
                .targetDate(LocalDate.now().plusYears(1))
                .done(false)
                .build()
        );
        todos.add(
                Todo.builder()
                    .id(++todosCount)
                    .username("filipefrs")
                    .description("Learn JSP")
                    .targetDate(LocalDate.now().plusYears(2))
                    .done(false)
                    .build()
        );
        todos.add(
                Todo.builder()
                    .id(++todosCount)
                    .username("filipefrs")
                    .description("Learn AWS")
                    .targetDate(LocalDate.now().plusYears(3))
                    .done(false)
                    .build()
        );
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);

        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate) {
        Todo newTodo = Todo.builder()
                .id(++todosCount)
                .username(username)
                .description(description)
                .targetDate(targetDate)
                .done(false)
                .build();

        todos.add(newTodo);
    }

    public void deleteById(Long id) {
        Predicate<? super Todo> predicate = todo -> Objects.equals(todo.getId(), id);
        todos.removeIf(predicate);
    }

    public Todo findById(Long id) {
        Predicate<? super Todo> predicate = todo -> Objects.equals(todo.getId(), id);

        Todo todo = todos.stream().filter(predicate).findFirst().get();

        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
