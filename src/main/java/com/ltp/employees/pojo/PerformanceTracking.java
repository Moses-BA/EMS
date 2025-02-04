package com.ltp.employees.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "performance_tracking")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor

public class PerformanceTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull(message = "Total Tasks Assigned cannot be null")
    @PositiveOrZero(message = "Total Tasks Assigned must be greater than or equal to zero")
    @Column(name = "total_tasks_assigned")
    private Integer totalTasksAssigned;

    @NonNull
    @NotNull(message = "Tasks Completed cannot be null")
    @PositiveOrZero(message = "Tasks Completed must be greater than or equal to zero")
    @Column(name = "tasks_completed")
    private Integer tasksCompleted;

    @Column(name = "completion_percentage")
    private Integer completionPercentage; 

    @OneToOne(optional = true)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    // Calculate completion percentage on update
    @PrePersist
    @PreUpdate
    public void calculateCompletionPercentage() {
        if (totalTasksAssigned > 0) {
            if (tasksCompleted == 0) {
                completionPercentage = 0; // Handle case where tasksCompleted is zero
            } else {
                completionPercentage = (int) Math.round((double) tasksCompleted / totalTasksAssigned * 100); 
            }
        } else {
            completionPercentage = 0;
        }
    }

    public void setCompletionPercentage(Integer completionPercentage) {
        this.completionPercentage = completionPercentage;
    }
}