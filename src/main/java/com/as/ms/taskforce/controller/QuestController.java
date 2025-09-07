package com.as.ms.taskforce.controller;

import com.as.ms.taskforce.dto.QuestRequest;
import com.as.ms.taskforce.dto.QuestResponse;
import com.as.ms.taskforce.entity.Quest;
import com.as.ms.taskforce.exception.ResourceNotFoundException;
import com.as.ms.taskforce.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/quest")
@RequiredArgsConstructor
public class QuestController {
    private final QuestService questService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Quest> getAllQuests(){
        try{
            return questService.getAll();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unassigned")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestResponse> getUnassignedQuests(){
        try{
            return questService.getUnassignedQuest();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(questService.getQuestById(id), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/{id}/task/pending")
    public ResponseEntity<?> getPendingTasks(@PathVariable Long id){
        try{
            return new ResponseEntity<>(questService.getPendingTasksByQuestId(id), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/{id}/task/done")
    public ResponseEntity<?> getCompletedTasks(@PathVariable Long id){
        try{
            return new ResponseEntity<>(questService.getCompletedTasksByQuestId(id), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/{id}/task")
    public ResponseEntity<?> getAllTasks(@PathVariable Long id){
        try{
            return new ResponseEntity<>(questService.getAllTasksByQuestId(id), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/{id}/progress/refresh")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void refreshProgress(@PathVariable Long id){
        try{
            questService.refreshProgress(id);
        }catch (ResourceNotFoundException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (Exception e){
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/est")
    public ResponseEntity<?> getEstimateTime(@PathVariable Long id){
        try{
            return new ResponseEntity<>(questService.calculateEstimateHours(id), HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quest createQuest(@RequestBody QuestRequest request){
        try{
            return questService.createQuest(request);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuest(@PathVariable Long id){
        try{
            questService.deleteQuest(id);
        }catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
