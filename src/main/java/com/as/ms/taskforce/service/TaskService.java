package com.as.ms.taskforce.service;

import com.as.ms.taskforce.entity.Task;
import com.as.ms.taskforce.enums.TaskStatus;
import com.as.ms.taskforce.exception.ResourceNotFoundException;
import com.as.ms.taskforce.repository.TaskRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task getTaskById(@NotNull Long id) {
        return taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task with id: "+id+" not found"));
    }

    @Transactional
    public Task save(Task t) {
        return taskRepository.save(t);
    }

    public void deleteTaskById(Long id) {
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException("Task with id - "+id+" not found");
        }
        taskRepository.deleteById(id);
    }
    public void markAsDone(Long id) {
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException("Task with id - "+id+" not found");
        }
        Task t = getTaskById(id);
        t.setStatus(TaskStatus.DONE);
        t.setDoneDate(LocalDate.now());
        t.setActualHours(t.getEstimateHours());
        save(t);
    }
    public void markAsProgress(Long id) {
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException("Task with id - "+id+" not found");
        }
        Task t = getTaskById(id);
        t.setStatus(TaskStatus.IN_PROGRESS);
        save(t);
    }
    public void markAsTodo(Long id) {
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException("Task with id - "+id+" not found");
        }
        Task t = getTaskById(id);
        t.setStatus(TaskStatus.TODO);
        save(t);
    }

    public void incrementRevisionCount(Long id){
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException("Task with id - "+id+" not found");
        }
        Task t = getTaskById(id);
        t.setRevisionCount(t.getRevisionCount()+1);
    }



    public List<Task> getPendingTasksByQuestId(Long questId){
        return taskRepository.findByQuestIdAndStatus(questId, TaskStatus.TODO);
    }

    public List<Task> getCompletedTasksByQuestId(Long questId){
        return taskRepository.findByQuestIdAndStatus(questId, TaskStatus.DONE);
    }
    public List<Task> getAllTasksByQuestId(Long questId){
        return taskRepository.findByQuestId(questId);
    }
    public Long getDoneCountByQuestId(Long questId){
        return taskRepository.countByQuestIdAndStatus(questId,TaskStatus.DONE);
    }
    public Long getAllCountByQuestId(Long questId){
        return taskRepository.countByQuestId(questId);
    }
    public Long getPendingCountByQuestId(Long questId){
        return getAllCountByQuestId(questId) - getDoneCountByQuestId(questId);
    }
}
