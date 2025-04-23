package com.cesar.school.core.teams;

import lombok.RequiredArgsConstructor;

import com.cesar.school.core.teams.Member;
import java.time.LocalDate;

@RequiredArgsConstructor
public class GiveFeedbackService {
    private final FeedbackRepository feedbackRepository;

    public void execute(Member author, Member recipient, String comment, boolean positive) {
        Feedback feedback = Feedback.builder()
                .author(author)
                .recipient(recipient)
                .comment(comment)
                .positive(positive)
                .date(LocalDate.now())
                .build();
        feedbackRepository.save(feedback);
    }
}
