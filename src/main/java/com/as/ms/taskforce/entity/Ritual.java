package com.as.ms.taskforce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "ritual")
public class Ritual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean disabled = false;
    @ElementCollection
    @CollectionTable(name = "ritual_days", joinColumns = @JoinColumn(name = "ritual_id"))
    @MapKeyColumn(name = "day_index")
    @Column(name = "isActive")
    private Map<Integer,Boolean> days = IntStream.range(0,7)
            .boxed()
            .collect(Collectors.toMap(i->i, i->true));
}
