package com.charistech.flowflex.backend.model.workflow;

import com.charistech.flowflex.backend.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "workflows")
public class WorkflowInstance extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;

    private String status;

    private Timestamp startTime;

    private Timestamp endTime;

    @Column(columnDefinition = "JSONB")
    private String instanceData;
}
