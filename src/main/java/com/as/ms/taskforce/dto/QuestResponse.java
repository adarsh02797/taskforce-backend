package com.as.ms.taskforce.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Builder
@Data
public class QuestResponse {
    private Long id;
    private String name;
    private Double progress;
    private LocalDate startDate;
    private List<TaskResponse> tasks;
}
