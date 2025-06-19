package com.cyrange.vo;

import lombok.Data;


@Data
public class UserChallengeRecord {
    private String challengeName;
    private String categoryName;
    private int point;
    private String finishTime;
}
