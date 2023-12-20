package com.prueba.sic.pruebasic.context.employee.infrastructure.persistence;

import com.prueba.sic.pruebasic.context.person.infrastructure.persistence.PersonEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    private Long identificationNumber;

    @Column(name = "dependence")
    private String dependence;

    @Column(name = "admission_date")
    private Timestamp admissionDate;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private Timestamp creationDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Timestamp updateDate;

    @ManyToOne
    @JoinColumn(name = "identification_number", updatable = false)
    @MapsId
    private PersonEntity person;

}
