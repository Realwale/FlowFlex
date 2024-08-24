package com.charistech.flowflex.backend.repository;

import com.charistech.flowflex.backend.model.task.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseEntityJpaRepository<Task>{
}
