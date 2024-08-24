package com.charistech.flowflex.backend.repository;

import com.charistech.flowflex.backend.model.workflow.Workflow;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends BaseEntityJpaRepository<Workflow>{
}
