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
@Table(name = "t_addresses")
public class AddressEntity extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.LAZY)
    List<PeopleEntity> people;

    @Column(name = "addr_type", length = 5, nullable = false)
    private String addrType;

    @Column(name = "addr_info", length = 300)
    private String addrInfo;

}
