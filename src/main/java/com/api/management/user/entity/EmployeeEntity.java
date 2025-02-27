package com.api.management.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_employees")
public class EmployeeEntity extends BaseEntity {

    @Column(name = "full_name", nullable = false, length = 90)
    private String fullName;

    @Column(name = "pin", length = 10)
    private String pin;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<MailEntity> mails;

    @OneToOne
    private GroupEntity group;

    @OneToOne
    private PositionEntity position;

    @OneToOne
    private TimeSheetEntity timeSpentMinutes;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "assignedToEmployee", fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "projects_employee",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<ProjectEntity> projects;

}
