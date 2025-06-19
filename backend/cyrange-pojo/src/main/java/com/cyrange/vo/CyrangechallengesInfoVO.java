package com.cyrange.vo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CyrangechallengesInfoVO {
    private int questionId;
    private String title;
    private String description;
    private String category;
    private String author;
    private boolean isStart;
    private int remainingSeconds;
    private String accessUrl;
}
