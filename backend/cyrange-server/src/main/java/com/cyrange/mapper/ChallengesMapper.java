package com.cyrange.mapper;

import com.cyrange.entity.Categories;
import com.cyrange.entity.Cyrangechallenges;
import com.cyrange.entity.UserChallengeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChallengesMapper {

    /**
     * 依据id查询题目
     * @param id
     * @return
     */
    @Select("select * from cyrangechallenges where question_id = #{id}")
    Cyrangechallenges getChallengeById(int id);

    @Select("select author from cyrangechallenges where question_id = #{id}")
    String gettest(int id);

    @Select("select * from cyrangechallenges")
    List<Cyrangechallenges> getChallenges();

    @Select("select * from cyrangechallenges where question_id = #{id}")
    Cyrangechallenges getChallenge(int id);

    @Select("select * from categories")
    List<Categories> getCategories();

    List<UserChallengeRecord> getUserChallengeRecords(int userId);

    /**
     * 检查用户是否已经答对过某个题目
     * @param userId 用户 ID
     * @param questionId 题目 ID
     * @param isCorrect 是否答对
     * @return 是否存在记录
     */
    boolean existsByUserIdAndQuestionIdAndIsCorrect(int userId, int questionId, boolean isCorrect);

    /**
     * 插入用户的答题记录
     * @param record 答题记录
     */
    void insert(UserChallengeRecord record);

    /**
     * 增加题目的正确答题人数
     * @param questionId 题目 ID
     */
    void incrementParticipants(int questionId);

    /**
     * 获取题目分数
     * @param questionId
     * @return
     */
    @Select("select points from cyrangechallenges where question_id =#{questionId}")
    int getChallengePointById(int questionId);

    /**
     * 用户积分更新
     * @param userId
     * @param point
     */
    void addUserRecord(int userId, int point);
}
