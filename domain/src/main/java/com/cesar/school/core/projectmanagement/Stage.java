package com.cesar.school.core.projectmanagement;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stage {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private LocalDate deadline;

        @ManyToOne
        private Project project;

        @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL)
        private List<Task> tasks;

        @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL)
        private List<Challenge> challenges;
}
