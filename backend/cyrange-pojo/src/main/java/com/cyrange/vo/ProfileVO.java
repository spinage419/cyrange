package com.cyrange.vo;

import lombok.Data;

@Data
public class ProfileVO {
    private Long userId;
    private String username;
    private int totalScore;
    private String mail;
    private String phone;
}
