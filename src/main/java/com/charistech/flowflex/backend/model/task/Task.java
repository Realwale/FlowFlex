package com.charistech.flowflex.backend.model.task;

import com.charistech.flowflex.backend.common.BaseEntity;
import com.charistech.flowflex.backend.model.user.AppUser;
import com.charistech.flowflex.backend.model.workflow.Workflow;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    private String name;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private AppUser assignee;

    @ManyToOne
    @JoinColumn(name = "workflow")
    private Workflow workflow;

    @Column(columnDefinition = "JSONB")
    private String taskData;
}
