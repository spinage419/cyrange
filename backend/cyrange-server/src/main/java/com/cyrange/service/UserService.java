package com.cyrange.service;

import com.cyrange.dto.UserChallengesPageQueryDTO;
import com.cyrange.dto.UserLoginDTO;
import com.cyrange.dto.UserRegisterDTO;
import com.cyrange.dto.UserUpdateDTO;
import com.cyrange.entity.User;
import com.cyrange.result.PageResult;
import com.cyrange.vo.ProfileVO;
import com.cyrange.vo.StatsDataVO;
import com.cyrange.vo.StatsInfoVO;

public interface UserService {


    /**
     * 管理员登陆
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 获取主页信息
     * @return
     */
    StatsInfoVO getStates();

    /**
     * 用户注册
     * @param userRegisterDTO
     */
    void registerUser(UserRegisterDTO userRegisterDTO);

    /**
     * 获取用户资料
     * @return
     */
    ProfileVO getUserProfile();

    /**
     * 获取用户答题Stats
     * @return
     */
    StatsDataVO getSolveStats();

    /**
     * 分页查询
     * @param userChallengesPageQueryDTO
     * @return
     */
    PageResult pagequery(UserChallengesPageQueryDTO userChallengesPageQueryDTO);

    /**
     * 用户更新资料
     * @param updateDTO
     */
    void updateUserProfile(UserUpdateDTO updateDTO);
}
