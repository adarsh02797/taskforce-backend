package com.as.ms.taskforce.mapper;

import com.as.ms.taskforce.dto.TaskRequest;
import com.as.ms.taskforce.dto.TaskResponse;
import com.as.ms.taskforce.entity.Quest;
import com.as.ms.taskforce.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public static TaskResponse toResponse(Task task){
        return TaskResponse.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .status(task.getStatus())
                .doneDate(task.getDoneDate())
                .revisionCount(task.getRevisionCount())
                .estimateHours(task.getEstimateHours())
                .actualHours(task.getActualHours())
                .questId(task.getQuest().getId())
                .build();
    }

    public static Task toEntity(TaskRequest request, Quest quest) {
        return Task.builder()
                .name(request.getName())
                .description(request.getDescription())
                .estimateHours(request.getEstimateHours())
                .quest(quest)
                .build();
    }
}
