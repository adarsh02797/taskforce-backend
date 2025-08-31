package com.as.ms.taskforce.entity;

import com.as.ms.taskforce.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description = "";
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;
    private LocalDate done_date = null;
    private Integer revision_count = 0;
    private Integer estimateHours = 3;
    private Integer actualHours = 3;
    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;
}
