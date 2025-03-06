package com.teamsphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_roles")
public class RoleEntity extends BaseEntity {

    @Column(name = "role_name", nullable = false, unique = true, length = 20)
    private String roleName;

    @Column(name = "description", length = 150)
    private String description;

    @OneToOne(mappedBy = "role")
    private TimesheetEntity timeSheets;

}
