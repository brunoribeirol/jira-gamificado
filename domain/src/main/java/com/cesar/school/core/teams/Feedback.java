package com.cesar.school.core.teams;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Member recipient;

    private String comment;

    private LocalDate date;

    private boolean positive;

    public int calculateBonus() {
        return positive ? 10 : 0;
    }
}
