package com.cyrange.mapper;

import com.cyrange.dto.UserChallengesPageQueryDTO;
import com.cyrange.dto.UserRegisterDTO;
import com.cyrange.dto.UserUpdateDTO;
import com.cyrange.entity.TimeTrendResult;
import com.cyrange.entity.User;
import com.cyrange.vo.CategoryDistributionVO;
import com.cyrange.vo.UserChallengeRecord;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from  user where username=#{username} ")
    User getByUsername(String username);


    /**
     * 注册用户
     * @param userRegisterDTO
     */
    void addUser(UserRegisterDTO userRegisterDTO);

    @Select("select * from  user where id=#{id} ")
    User getByUserId(int id);

    List<CategoryDistributionVO> getCategoryDistribution(int userId);

    List<TimeTrendResult> getRawTimeTrend(int userId);

    Page<UserChallengeRecord> pageQuery(UserChallengesPageQueryDTO userChallengesPageQueryDTO);

    /**
     * 更新用户资料
     * @param updateDTO
     */
    void updateUserProfile(UserUpdateDTO updateDTO);
}
