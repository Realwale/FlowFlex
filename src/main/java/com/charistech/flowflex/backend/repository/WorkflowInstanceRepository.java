package com.charistech.flowflex.backend.repository;

import com.charistech.flowflex.backend.model.workflow.WorkflowInstance;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowInstanceRepository extends BaseEntityJpaRepository<WorkflowInstance>{
}
