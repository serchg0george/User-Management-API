package com.teamsphere.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "company_name", nullable = false, length = 50)
    private String name;

    @NotBlank
    @Column(name = "industry", nullable = false, length = 50)
    private String industry;

    @NotBlank
    @Column(name = "address", length = 50)
    private String address;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", orphanRemoval = true)
    private List<ProjectEntity> projects;
}
