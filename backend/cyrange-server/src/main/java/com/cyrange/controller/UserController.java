package com.cyrange.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.StrUtil;
import com.cyrange.constant.JwtClaimsConstant;
import com.cyrange.dto.UserChallengesPageQueryDTO;
import com.cyrange.dto.UserLoginDTO;
import com.cyrange.dto.UserRegisterDTO;
import com.cyrange.dto.UserUpdateDTO;
import com.cyrange.entity.User;
import com.cyrange.properties.JwtProperties;
import com.cyrange.result.PageResult;
import com.cyrange.result.Result;
import com.cyrange.service.UserService;
import com.cyrange.utils.JwtUtil;
import com.cyrange.vo.*;
import com.github.benmanes.caffeine.cache.Cache;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@Slf4j
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private Cache<String, String> captchaCache;

    /**
     * 获取验证码
     * @param
     */
    @GetMapping("/captcha")
    public Result<CaptchaResponseVO> generateCaptcha() {
        log.info("生成验证码");
        // 生成验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40,4,8);
        String code = lineCaptcha.getCode();

        // 生成token并存入缓存
        String token = UUID.randomUUID().toString();
        captchaCache.put(token, code);

        // 将验证码图片转换为Base64编码
        BufferedImage bufferedImage = lineCaptcha.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
            String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());
            CaptchaResponseVO captchaResponseVO = new CaptchaResponseVO(base64Image, token);
           return Result.success(captchaResponseVO);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("生成验证码失败");
        }
    }

    /**
     * 管理员登录
     *UserLoginDTO
     * @param
     * @return
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("管理员登录：{}", userLoginDTO);

        String cacheCode = captchaCache.getIfPresent(userLoginDTO.getCaptchaToken());
        if (StrUtil.isEmpty(cacheCode)) {
            return Result.error("验证码已过期");
        }

        if (!cacheCode.equalsIgnoreCase(userLoginDTO.getCaptcha())) {
            return Result.error("验证码错误");
        }

        // 清除验证码
        captchaCache.invalidate(userLoginDTO.getCaptchaToken());
        User user = userService.login(userLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);


// 解析 Token，获取过期时间 exp
        Claims decodedClaims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        Date expiration = decodedClaims.getExpiration();
        long exp = expiration.getTime() / 1000;  // 转换为 Unix 时间戳（秒）

// 构建 UserVO，包含 exp
        UserVO userVO = UserVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .exp(exp)  // 设置过期时间
                .build();


        return Result.success(userVO);
    }

    /**
     * 获取主页stats
     * @return
     */
    @GetMapping("/stats")
    public Result<StatsInfoVO> getStats(){
        log.info("获取state");
        StatsInfoVO statsInfoVO=userService.getStates();
        return Result.success(statsInfoVO);
    }

    /**
     * 用户注册
     * @param userRegisterDTO
     * @return
     */
    @PostMapping("/register")
    public Result<String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO){
        log.info("注册用户");
        String cacheCode = captchaCache.getIfPresent(userRegisterDTO.getCaptchaToken());
        if (StrUtil.isEmpty(cacheCode)) {
            return Result.error("验证码已过期");
        }

        if (!cacheCode.equalsIgnoreCase(userRegisterDTO.getCaptcha())) {
            return Result.error("验证码错误");
        }

        // 清除验证码
        captchaCache.invalidate(userRegisterDTO.getCaptchaToken());
        userService.registerUser(userRegisterDTO);
        return Result.success();
    }

    /**
     * 获取用户资料
     * @return
     */
    @GetMapping("/user/profile")
    public Result<ProfileVO> getUserProfile(){
        log.info("获取用户资料");
        ProfileVO profileVO=userService.getUserProfile();
        return Result.success(profileVO);
    }

    /**
     * 获取用户答题Stats
     * @return
     */
    @GetMapping("user/stats")
    public  Result<StatsDataVO>  getSolveStats()  {
        log.info("获取用户答题Stats");
        StatsDataVO statsDataVO=userService.getSolveStats();
        return Result.success(statsDataVO);
    }


    /**
     * 用户做题记录分页查询
     *
     * @param userChallengesPageQueryDTO
     * @return
     */
    @GetMapping("/user/submissions")
    @ApiOperation("分页查询")
    public Result<PageResult> page(UserChallengesPageQueryDTO userChallengesPageQueryDTO) {
        log.info("分页查询");
        PageResult pageResult = userService.pagequery(userChallengesPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 用户更新资料
     * @param updateDTO
     * @return
     */
    @PutMapping("/user/profile")
    public Result<String> updateProfile(@RequestBody UserUpdateDTO updateDTO) {
        log.info("用户更新资料");
        userService.updateUserProfile( updateDTO);
        return  Result.success();
    }
}
