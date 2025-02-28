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
@Table(name = "t_companies")
public class CompanyEntity extends BaseEntity {

    @Column(name = "org_name", nullable = false, length = 50)
    private String name;

    @Column(name = "industry", nullable = false, length = 50)
    private String industry;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "company")
    private List<ProjectEntity> projects;
}
