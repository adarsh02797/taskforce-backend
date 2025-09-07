package com.as.ms.taskforce.controller;

import com.as.ms.taskforce.dto.TaskRequest;
import com.as.ms.taskforce.dto.TaskResponse;
import com.as.ms.taskforce.entity.Quest;
import com.as.ms.taskforce.entity.Task;
import com.as.ms.taskforce.exception.ResourceNotFoundException;
import com.as.ms.taskforce.mapper.TaskMapper;
import com.as.ms.taskforce.service.QuestService;
import com.as.ms.taskforce.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final QuestService questService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try{
            log.info("Fetching Task with id- {}", id);
            Task task = taskService.getTaskById(id);
            log.info("Fetch successful!");
            return ResponseEntity.ok(TaskMapper.toResponse(task));
        }catch (ResourceNotFoundException e){
            log.error("Fetch failed!", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequest request) {
        try {
            log.info("Fetching Quest with id- {}", request.getQuestId());
            Quest quest = questService.getQuestById(request.getQuestId());
            log.info("Fetch successful!");
            Task t = TaskMapper.toEntity(request, quest);
            Task task = taskService.save(t);
            log.info("Task saved");
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }catch (ResourceNotFoundException e){
            log.error("ERROR: " ,e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error("ERROR: " ,e);
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        try{
            taskService.deleteTaskById(id);
        }catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @PutMapping("done/{id}")
    public void markAsDone(@PathVariable Long id){
        try{
            taskService.markAsDone(id);
        }catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("progress/{id}")
    public void markAsProgress(@PathVariable Long id){
        try{
            taskService.markAsProgress(id);
        }catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("todo/{id}")
    public void markAsTodo(@PathVariable Long id){
        try{
            taskService.markAsTodo(id);
        }catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/revise/{id}")
    public void incrementRevisionCount(@PathVariable Long id){
        try{
            taskService.incrementRevisionCount(id);
        }catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}
