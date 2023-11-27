package com.api.management.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_mails")
public class MailEntity extends BaseEntity {

    @Column(name = "email_type", length = 5, nullable = false)
    @Enumerated(EnumType.STRING)
    private EmailType emailType;

    @Column(name = "email", length = 40, unique = true)
    private String email;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    PeopleEntity people;
}
