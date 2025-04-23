package com.cesar.school.core.gamification;

import java.util.List;

public interface RankingRepository {
    List<Ranking> findTopByType(String type);
}
