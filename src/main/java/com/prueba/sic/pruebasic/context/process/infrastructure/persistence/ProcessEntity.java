package com.prueba.sic.pruebasic.context.process.infrastructure.persistence;

import com.prueba.sic.pruebasic.context.employee.infrastructure.persistence.EmployeeEntity;
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
@Table(name = "process")
public class ProcessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filingNumber;

    @Column(name = "filing_year")
    private int filingYear;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private Timestamp creationDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Timestamp updateDate;

    @ManyToOne
    @JoinColumn(name = "filing_person", updatable = false)
    private PersonEntity filingPerson;

    @ManyToOne
    @JoinColumn(name = "official_received", updatable = false)
    private EmployeeEntity officialReceived;

}
