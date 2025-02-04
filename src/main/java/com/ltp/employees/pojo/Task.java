package com.ltp.employees.pojo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor

public class Task {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @NotBlank(message = "Task title cannot be blank")
    @NonNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    public enum TaskStatus {
        PENDING,
        COMPLETED;
    }

    @NotNull(message = "Task status cannot be null")
    @Enumerated(EnumType.STRING) 
    @Column(name = "status", nullable = false)
    @NonNull
    private TaskStatus status = TaskStatus.PENDING;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @NotNull(message = "Due date cannot be null")
    @Column(name = "due_date", nullable = false)
    @Future(message = "The due date must be in the future")
    @NonNull
    private LocalDate dueDate;
}
