package com.teamsphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_tasks")
public class TaskEntity extends BaseEntity {

    @Column(name = "time_spent_minutes", nullable = false, length = 10)
    private Integer timeSpentMinutes;

    @Column(name = "task_description", nullable = false, length = 150)
    private String taskDescription;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

    @ManyToOne
    private EmployeeEntity employee;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskEntity task)) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy hProxy ? hProxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hProxy ? hProxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy hProxy ? hProxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
