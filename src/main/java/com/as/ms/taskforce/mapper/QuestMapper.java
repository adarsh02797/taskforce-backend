package com.as.ms.taskforce.mapper;

import com.as.ms.taskforce.dto.QuestRequest;
import com.as.ms.taskforce.dto.QuestResponse;
import com.as.ms.taskforce.dto.TaskResponse;
import com.as.ms.taskforce.entity.Quest;
import com.as.ms.taskforce.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestMapper {
    public static Quest toEntity(QuestRequest questRequest){
        return Quest.builder()
                .name(questRequest.getName())
                .build();
    }

    public static QuestResponse toResponse(Quest quest) {
        return QuestResponse.builder()
                .id(quest.getId())
                .name(quest.getName())
                .progress(quest.getProgress())
                .startDate(quest.getStartDate())
                .tasks(mapTasks(quest.getTasks()))
                .build();
    }

    private static List<TaskResponse> mapTasks(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList());
    }
}
