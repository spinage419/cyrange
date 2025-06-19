package com.cyrange.vo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CyrangechallengesVO {
    private int questionId;
    private String title;
    private String category;
    private int points;
    private int participants;
    private Boolean solved;
}
