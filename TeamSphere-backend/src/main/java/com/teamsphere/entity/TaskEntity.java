package com.teamsphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_tasks")
public class TaskEntity extends BaseEntity {

    @Column(name = "time_spent_minutes", nullable = false, length = 10)
    private Integer timeSpentMinutes;

    @Column(name = "task_description", nullable = false, length = 150)
    private String taskDescription;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

    @ManyToOne
    private EmployeeEntity employee;
}
