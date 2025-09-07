package com.as.ms.taskforce.dto;

import com.as.ms.taskforce.entity.Quest;
import com.as.ms.taskforce.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private LocalDate doneDate;
    private Integer revisionCount;
    private Integer estimateHours;
    private Integer actualHours;
    private Long questId;
}
