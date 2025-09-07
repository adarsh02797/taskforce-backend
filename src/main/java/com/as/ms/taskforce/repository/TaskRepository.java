package com.as.ms.taskforce.repository;

import com.as.ms.taskforce.entity.Task;
import com.as.ms.taskforce.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByQuestIdAndStatus(Long questId, TaskStatus status);
    List<Task> findByQuestId(Long questId);
    Long countByQuestId(Long questId);
    Long countByQuestIdAndStatus(Long questId, TaskStatus status);

}
