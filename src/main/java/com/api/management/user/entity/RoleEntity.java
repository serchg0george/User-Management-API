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
@Table(name = "t_roles")
public class RoleEntity extends BaseEntity {

    @Column(name = "role_name", nullable = false, unique = true, length = 20)
    private String roleName;

    @Column(name = "description", length = 150)
    private String description;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "role")
    private List<TimesheetEntity> timeSheets;

}
