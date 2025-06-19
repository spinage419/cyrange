package com.cyrange.service.impl;

import com.cyrange.constant.MessageConstant;
import com.cyrange.context.BaseContext;
import com.cyrange.dto.UserChallengesPageQueryDTO;
import com.cyrange.dto.UserLoginDTO;
import com.cyrange.dto.UserRegisterDTO;
import com.cyrange.dto.UserUpdateDTO;
import com.cyrange.entity.TimeTrendResult;
import com.cyrange.entity.User;
import com.cyrange.exception.AccountNotFoundException;
import com.cyrange.exception.BaseException;
import com.cyrange.exception.PasswordErrorException;
import com.cyrange.mapper.UserMapper;
import com.cyrange.result.PageResult;
import com.cyrange.service.UserService;
import com.cyrange.utils.RedisService;
import com.cyrange.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;
    @Autowired
    private RedisService redisService;
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO) {
       User user =userMapper.getByUsername(userLoginDTO.getUsername());

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        String password=userLoginDTO.getPassword();
        //密码比对
        //对密码进行MD5加密
       password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return user;
    }

    /**
     * 获取主页信息
     * @return
     */
    public StatsInfoVO getStates() {
        StatsInfoVO statsInfoVO=new StatsInfoVO(redisService.getTodayChallenges(),redisService.getSuccessfulBreaches(),redisService.getOnlineUsers());
        return statsInfoVO;
    }

    /**
     * 用户注册
     * @param userRegisterDTO
     */
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user =userMapper.getByUsername(userRegisterDTO.getUsername());
        if(user!=null){
            throw new BaseException(MessageConstant.ALREDY_EXISTS);
        }
        if(userRegisterDTO.getUsername()==null||userRegisterDTO.getPassword()==null){
            throw new BaseException(MessageConstant.USERANME_OR_PASSWORD_IS_NULL);
        }
        userRegisterDTO.setPassword(DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes()));
        userMapper.addUser(userRegisterDTO);
    }


    /**
     * 获取用户资料
     * @return
     */
    public ProfileVO getUserProfile() {
        int currentid= BaseContext.getCurrentId();
        User user=userMapper.getByUserId(currentid);
        ProfileVO profileVO  = new ProfileVO();
        profileVO.setUserId(user.getId());
        profileVO.setUsername(user.getUsername());
        profileVO.setTotalScore(user.getTotalScore());
        profileVO.setMail(user.getMail());
        profileVO.setPhone(user.getPhone());
        return profileVO;
    }

    /**
     * 获取用户答题Stats
     * @return
     */
    public StatsDataVO getSolveStats() {
        StatsDataVO  data = new StatsDataVO();
        int userId= BaseContext.getCurrentId();
        //  分类分布
        data.setCategoryDistribution(userMapper.getCategoryDistribution(userId));

        // 时间趋势处理
        List<TimeTrendResult> rawData = userMapper.getRawTimeTrend(userId);
        TimeTrendVO timeTrend  =  new  TimeTrendVO();
        List<String>  dates  = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();
        int  accumulated =  0;

        for (TimeTrendResult  item  :  rawData)  {
            dates.add((String) item.getMonth());
            accumulated += (Integer) item.getScore();
            scores.add(accumulated);
        }

        timeTrend.setDates(dates);
        timeTrend.setScores(scores);
        data.setTimeTrend(timeTrend);
        return data;
    }

    /**
     * 分页查询
     * @param userChallengesPageQueryDTO
     * @return
     */
    public PageResult pagequery(UserChallengesPageQueryDTO userChallengesPageQueryDTO) {
        int userId= BaseContext.getCurrentId();
        userChallengesPageQueryDTO.setUserId(userId);
        PageHelper.startPage(userChallengesPageQueryDTO.getPage(), userChallengesPageQueryDTO.getPageSize());
        Page<UserChallengeRecord> page = userMapper.pageQuery(userChallengesPageQueryDTO);
        long pagetotal=page.getTotal();
        List<UserChallengeRecord> records=page.getResult();
        return new PageResult(pagetotal,records);
    }

    /**
     * 用户更新资料
     * @param updateDTO
     */
    public void updateUserProfile(UserUpdateDTO updateDTO) {
        int userId= BaseContext.getCurrentId();
        User user =userMapper.getByUsername(updateDTO.getUsername());
        if(user!=null){
            if (user.getId()!=userId) {
                throw new BaseException(MessageConstant.ALREDY_EXISTS);
            }
        }
        updateDTO.setId(userId);
        userMapper.updateUserProfile(updateDTO);
    }

}
