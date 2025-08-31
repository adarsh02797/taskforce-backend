package com.as.ms.taskforce.repository;

import com.as.ms.taskforce.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest,Long> {
}
