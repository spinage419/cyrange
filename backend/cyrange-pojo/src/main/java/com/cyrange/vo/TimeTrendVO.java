package com.cyrange.vo;

import lombok.Data;

import java.util.List;

@Data
public class TimeTrendVO {
    private List<String> dates;
    private List<Integer>  scores;
}
