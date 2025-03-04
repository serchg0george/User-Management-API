package com.teamsphere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_timesheets")
public class TimesheetEntity extends BaseEntity {

    @Column(name = "time_spent_minutes", nullable = false, length = 10)
    private Integer timeSpentMinutes;

    @Column(name = "task_description", nullable = false, length = 150)
    private String taskDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private RoleEntity role;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "timeSheetEmployee")
    private EmployeeEntity employee;
}
