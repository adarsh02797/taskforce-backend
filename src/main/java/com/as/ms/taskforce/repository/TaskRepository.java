package com.as.ms.taskforce.repository;

import com.as.ms.taskforce.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
