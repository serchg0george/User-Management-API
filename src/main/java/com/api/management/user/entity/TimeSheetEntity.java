package com.api.management.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_time_tracks")
public class TimeSheetEntity extends BaseEntity {

    @Column(name = "time_spent_minutes", nullable = false)
    private Integer timeSpentMinutes;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "timeSpentMinutes")
    private TaskEntity task;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "")
    private EmployeeEntity employee;
}
