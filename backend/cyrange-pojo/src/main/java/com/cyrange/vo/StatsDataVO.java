package com.cyrange.vo;

import lombok.Data;

import java.util.List;

@Data
public class StatsDataVO {
    private List<CategoryDistributionVO> categoryDistribution;
    private TimeTrendVO timeTrend;
}
