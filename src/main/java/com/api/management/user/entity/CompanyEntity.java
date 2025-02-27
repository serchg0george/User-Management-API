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

    @OneToOne
    private MailEntity mail;

    @OneToOne
    private AddressEntity address;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "company")
    private List<ProjectEntity> projects;
}
