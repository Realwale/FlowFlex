package com.charistech.flowflex.backend.model.workflow;

import com.charistech.flowflex.backend.common.BaseEntity;
import com.charistech.flowflex.backend.model.task.Task;
import com.charistech.flowflex.backend.model.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "workflows")
public class Workflow extends BaseEntity {

    private String name;

    @Lob
    private String description;
    private String status;

    @Column(columnDefinition = "JSONB")
    private String workflowData;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private AppUser creator;

    @OneToMany(mappedBy = "workflow")
    private List<Task>tasks;
}
