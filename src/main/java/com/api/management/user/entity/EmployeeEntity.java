package com.api.management.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_employees")
public class EmployeeEntity extends BaseEntity {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\p{IsCyrillic} -]+$")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\p{IsCyrillic} -]+$")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Pattern(regexp = "^(?!\\s*$)[-0-9\\s]{10}$")
    @Column(name = "pin", length = 10)
    private String pin;

    @NotBlank
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @OneToOne
    private DepartmentEntity department;

    @OneToOne
    private PositionEntity position;

    @OneToOne
    private TimesheetEntity timeSheetEmployee;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_projects_employees",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<ProjectEntity> projects;

}
