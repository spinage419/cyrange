package com.cyrange.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserChallengesPageQueryDTO implements Serializable {

    private int userId;
    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

}
