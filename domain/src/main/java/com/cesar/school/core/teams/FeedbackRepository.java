package com.cesar.school.core.teams;

import java.util.List;

public interface FeedbackRepository {
    List<Feedback> findByRecipient(Member recipient);
    void save(Feedback feedback);
}