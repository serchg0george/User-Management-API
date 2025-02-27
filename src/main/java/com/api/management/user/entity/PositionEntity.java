package com.api.management.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_positions")
public class PositionEntity extends BaseEntity {

    @Column(name = "role_names")
    private String roleName;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "position")
    private EmployeeEntity employee;
}
