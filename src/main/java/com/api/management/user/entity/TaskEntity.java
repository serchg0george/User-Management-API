package com.api.management.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_tasks")
public class TaskEntity extends BaseEntity {

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 400, nullable = false)
    private String description;

    @Column(name = "estimated_time_minutes", length = 10, nullable = false)
    private Integer estimatedTimeMinutes;

    @Column(name = "status", length = 50, nullable = false)
    private String status;

    @OneToOne
    private TimeSheetEntity timeSpentMinutes;

    @ManyToOne
    private EmployeeEntity assignedToEmployee;
}
