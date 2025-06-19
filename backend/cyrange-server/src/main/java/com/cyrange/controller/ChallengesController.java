package com.cyrange.controller;

import com.cyrange.annotation.RateLimit;
import com.cyrange.context.BaseContext;
import com.cyrange.dto.SubmissionInfo;
import com.cyrange.kubernetes.KubernetesOption;
import com.cyrange.vo.ContainerVO;
import com.cyrange.vo.CyrangechallengesInfoVO;
import com.cyrange.vo.CyrangechallengesVO;
import com.cyrange.entity.Categories;
import com.cyrange.result.Result;
import com.cyrange.service.ChallengesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/challenges")
@Slf4j
public class ChallengesController {
    @Autowired
    public ChallengesService challengesService;

    /**
     * 创建容器
     * @param id
     * @return
     */
    @RateLimit(cooldown = 60) // 应用频率限制
    @GetMapping("/container/{id}")
    public Result createChallenge(@PathVariable int id)  {
        log.info("创建{}题的容器",id);
        challengesService.createChallenge(id);
        return Result.success();
    }

    @RateLimit(cooldown = 60) // 应用频率限制
    @GetMapping("/container/extend/{id}")
    public Result extendTime(@PathVariable int id){
        log.info("容器延时",id);
        challengesService.extendTime(id);
        return Result.success();
    }
    /**
     * 删除容器
     * @param id
     * @return
     */
    @RateLimit(cooldown = 60) // 应用频率限制
    @DeleteMapping("/{id}")
    public Result<String> deleteChallenge(@PathVariable int id){
        log.info("删除{}题的容器",id);
        int currentid= BaseContext.getCurrentId();
        challengesService.deleteChallenge(currentid,id);
        return Result.success();
    }

    /**
     * 获取题目简单信息
     * @return
     */
    @GetMapping("")
    public Result<List<CyrangechallengesVO>> getSimpleChallengesInfo(){
        log.info("获取题目简单信息");
        List<CyrangechallengesVO> cyrangechallengesVOs=challengesService.getSimpleChallengesInfo();
        return  Result.success(cyrangechallengesVOs);
    }

    /**
     * 获取题目详细信息
     * @return
     */
    @GetMapping("/{id}")
    public Result<CyrangechallengesInfoVO> getMoreChallengesInfo(@PathVariable int id){
        log.info("获取题目详细信息");
        CyrangechallengesInfoVO cyrangechallengesInfoVO=challengesService.getMoreChallengesInfo(id);
        Map<String,String> maps=challengesService.getIsStart(id);
        if(maps!=null){
            cyrangechallengesInfoVO.setStart(true);
            cyrangechallengesInfoVO.setRemainingSeconds(Integer.parseInt(maps.get("time")));
            cyrangechallengesInfoVO.setAccessUrl(maps.get("url"));
        }
        else{
            cyrangechallengesInfoVO.setStart(false);
        }
        return  Result.success(cyrangechallengesInfoVO);
    }

    /**
     * 获取题目分类
     * @return
     */
    @GetMapping("/categories")
    public Result<List<Categories>> getCategories(){
        log.info("获取题目分类");
        List<Categories> categories=challengesService.getCategories();
        return Result.success(categories);
    }


    /**
     * 检验flag
     * @return
     */
    @PostMapping ("/attempt")
    public Result checkFlag(@RequestBody SubmissionInfo submissionInfo){
        log.info("检验flag{}",submissionInfo.getSubmission());
        challengesService.checkFlag(submissionInfo);
        return  Result.success();
    }

}