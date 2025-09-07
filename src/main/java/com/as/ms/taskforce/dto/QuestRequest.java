package com.as.ms.taskforce.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class QuestRequest {
    private String name;
}
