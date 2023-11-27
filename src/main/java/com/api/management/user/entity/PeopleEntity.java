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
@Table(name = "t_people")
public class PeopleEntity extends BaseEntity {

    @Column(name = "full_name", nullable = false, length = 90)
    private String fullName;

    @Column(name = "pin", length = 10)
    private String pin;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "people", fetch = FetchType.LAZY)
    private List<MailEntity> mails;

}
