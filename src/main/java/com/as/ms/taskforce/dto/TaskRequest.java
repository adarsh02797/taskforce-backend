package com.as.ms.taskforce.dto;

import com.as.ms.taskforce.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskRequest {
    @NotNull
    private String name;
    @Builder.Default
    private String description = "";
    @Builder.Default
    private Integer estimateHours = 3;
    @NotNull
    private Long questId;
}
