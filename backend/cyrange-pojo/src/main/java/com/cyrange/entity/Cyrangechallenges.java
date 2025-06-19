package com.cyrange.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cyrangechallenges {
    private int questionId;
    private String title;
    private String description;
    private String category;
    private String author;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String createdBy;
    private String updatedBy;
    private String deploymentFileurl;
    private String serviceFileurl;
    private String ingressFileurl;
    private int points;
    private int participants;
}
