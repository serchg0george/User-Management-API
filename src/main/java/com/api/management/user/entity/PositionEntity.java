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

    @Column(name = "position_name", nullable = false, length = 50)
    private String positionName;

    @Column(name = "years_of_experience", nullable = false)
    private Integer yearsOfExperience;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "position")
    private EmployeeEntity employee;
}
