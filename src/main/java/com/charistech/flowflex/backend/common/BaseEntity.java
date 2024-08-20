package com.charistech.flowflex.backend.common;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @SequenceGenerator(name = "FLOWFLEX_TABLE_SEQUENCE", sequenceName = "FLOWFLEX_TABLE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLOWFLEX_TABLE_SEQUENCE")
    private Long id;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", updatable = false)
    private Date createAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on")
    private Date updateAt;
}
