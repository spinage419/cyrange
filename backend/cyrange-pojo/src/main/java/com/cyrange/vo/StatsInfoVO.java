package com.cyrange.vo;

import lombok.Data;

@Data
public class StatsInfoVO {
    private String todayChallenges;
    private String successfulBreaches;
    private String onlineUsers;

    public StatsInfoVO(String todayChallenges, String successfulBreaches, String onlineUsers) {
        this.todayChallenges = todayChallenges;
        this.successfulBreaches = successfulBreaches;
        this.onlineUsers = onlineUsers;
    }
}
