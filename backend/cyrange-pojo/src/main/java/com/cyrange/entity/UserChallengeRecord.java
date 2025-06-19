package com.cyrange.entity;

import lombok.Data;

@Data
public class UserChallengeRecord {
    private Long id;
    private Long userId;
    private Long challengeId;
    private int isCorrect;
    private String finishTime;
}
