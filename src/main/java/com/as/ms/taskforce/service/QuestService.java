package com.as.ms.taskforce.service;

import com.as.ms.taskforce.dto.QuestRequest;
import com.as.ms.taskforce.dto.QuestResponse;
import com.as.ms.taskforce.entity.Quest;
import com.as.ms.taskforce.entity.Task;
import com.as.ms.taskforce.enums.TaskStatus;
import com.as.ms.taskforce.exception.ResourceNotFoundException;
import com.as.ms.taskforce.mapper.QuestMapper;
import com.as.ms.taskforce.repository.QuestRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuestService {
    private final QuestRepository questRepository;
    private final TaskService taskService;

    public Quest getQuestById(@NotNull Long questId) {
        return questRepository.findById(questId).orElseThrow(()->new ResourceNotFoundException("Quest with id- "+ questId +" not found"));
    }

    public List<Task> getPendingTasksByQuestId(Long questId){
        return taskService.getPendingTasksByQuestId(questId);
    }

    public List<Task> getCompletedTasksByQuestId(Long questId){
        return taskService.getCompletedTasksByQuestId(questId);
    }

    public List<Task> getAllTasksByQuestId(Long questId){
        return taskService.getAllTasksByQuestId(questId);
    }

    public void refreshProgress(Long questId){
        Quest q = getQuestById(questId);
        q.setProgress(
                (taskService.getDoneCountByQuestId(questId) *100.0)
                / taskService.getAllCountByQuestId(questId)
        );
        questRepository.save(q);
    }

    public Long calculateEstimateHours(Long questId){
        Quest q = getQuestById(questId);
        List<Task> tasks = q.getTasks();
        return tasks.stream()
                .map(Task::getEstimateHours)
                .filter(Objects::nonNull)
                .mapToLong(Integer::longValue)
                .sum();
    }

    @Transactional
    public Quest createQuest(QuestRequest request){
        Quest q = QuestMapper.toEntity(request);
        return questRepository.save(q);
    }

    @Transactional
    public void deleteQuest(Long id){
        if(!questRepository.existsById(id))throw new ResourceNotFoundException("Quest with id-"+id+" not found");
        questRepository.deleteById(id);
    }


    public List<Quest> getAll() {
        return questRepository.findAll();
    }

    public List<QuestResponse> getUnassignedQuest() {
        return questRepository.findAll().stream()
                .map(quest -> {
                    List<Task> unassignedTasks = quest.getTasks().stream()
                            .filter(task -> task.getStatus()== TaskStatus.UNASSIGNED)
                            .toList();
                    Quest filteredQuest = Quest.builder()
                            .id(quest.getId())
                            .name(quest.getName())
                            .progress(quest.getProgress())
                            .tasks(unassignedTasks)
                            .build();
                    return QuestMapper.toResponse(filteredQuest);
                })
                .filter(qr -> !qr.getTasks().isEmpty())
                .toList();
    }
}
