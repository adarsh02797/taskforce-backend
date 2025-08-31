package com.as.ms.taskforce.entity;
/*
Cheat Days Count
Streak
 */
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "params")
public class Params {
    @Id
    private String key;
    private Object value;
}
