package com.app.todo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    private String username;

    @NotNull
    @Size(min=8, message = "Enter at least 08 characters")
    private String description;

    @NotNull
    private LocalDate targetDate;

    private boolean done;
}
