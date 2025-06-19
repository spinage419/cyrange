package com.cyrange.service;

import com.cyrange.dto.SubmissionInfo;
import com.cyrange.vo.ContainerVO;
import com.cyrange.vo.CyrangechallengesInfoVO;
import com.cyrange.vo.CyrangechallengesVO;
import com.cyrange.entity.Categories;

import java.util.List;
import java.util.Map;

public interface ChallengesService {
    /**
     * 创建题目
     */
    void createChallenge(int id);

    /**
     * 删除容器
     * @param id
     */
    void deleteChallenge(int currentid,int id);

    /**
     * 获取简单题目信息
     * @return
     */
    List<CyrangechallengesVO> getSimpleChallengesInfo();

    /**
     * 获取题目详细信息
     * @param id
     * @return
     */
    CyrangechallengesInfoVO getMoreChallengesInfo(int id);

    /**
     * Change access modifier
     * @param
     * @return
     */
    List<Categories> getCategories();

    Map<String, String> getIsStart(int id);

    /**
     * 检验flag
     * @param submissionInfo
     */
    void checkFlag(SubmissionInfo submissionInfo);

    /**
     * 容器延时
     * @param id
     */
    void extendTime(int id);
}
