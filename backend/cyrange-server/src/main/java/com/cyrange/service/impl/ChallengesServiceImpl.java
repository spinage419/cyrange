package com.cyrange.service.impl;

import com.cyrange.context.BaseContext;
import com.cyrange.dto.SubmissionInfo;
import com.cyrange.entity.UserChallengeRecord;
import com.cyrange.exception.BaseException;
import com.cyrange.exception.ContainerHaveException;
import com.cyrange.vo.ContainerVO;
import com.cyrange.vo.CyrangechallengesInfoVO;
import com.cyrange.vo.CyrangechallengesVO;
import com.cyrange.entity.Categories;
import com.cyrange.entity.Cyrangechallenges;
import com.cyrange.kubernetes.KubernetesOption;
import com.cyrange.mapper.ChallengesMapper;
import com.cyrange.service.ChallengesService;
import com.cyrange.utils.RedisService;
import io.kubernetes.client.openapi.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ChallengesServiceImpl implements ChallengesService {
    @Autowired
    public ChallengesMapper challengesMapper;
    @Autowired
    public KubernetesOption kubernetesOption;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTimerService redisTimerService;

    /**
     * 创建题目
     */
    public void createChallenge(int id) {
        int currentid= BaseContext.getCurrentId();
        String value=redisService.getStatus(String.valueOf(currentid));
        if(value!=null){
            this.deleteChallenge(currentid, Integer.parseInt(value));
        }
        UUID flaguuid = UUID.randomUUID();
        UUID urluuid = UUID.randomUUID();
        String filename=urluuid.toString();
        String suffix = ".challenge.cyrange.xin";
        // 拼接UUID字符串和后缀
        String url = urluuid.toString() + suffix;
        // 将 UUID 转换为字符串，并去掉其中的连字符（-），因为题目中的示例没有连字符
        String uuidString = flaguuid.toString();
        // 拼接前缀和 UUID 字符串
        String flag = "cyrange{" + uuidString + "}";
        log.info(url);
        redisService.save(String.valueOf(currentid), String.valueOf(id),filename,flag);
        redisService.saveStatus(String.valueOf(currentid),String.valueOf(id));
        Cyrangechallenges cyrangechallenges=challengesMapper.getChallengeById(id);
        redisTimerService.startTimer(String.valueOf(currentid), String.valueOf(id),3600, TimeUnit.SECONDS);
//        //阿里云oss
////        String deploymentFileUrl=cyrangechallenges.getDeploymentFileurl();
////        String serviceFileUrl =cyrangechallenges.getServiceFileurl();
////        String ingressFileUrl =cyrangechallenges.getIngressFileurl();
////        File deploymentfile =kubernetesOption.loadUrlFile(deploymentFileUrl);
////        File servicefile =kubernetesOption.loadUrlFile(serviceFileUrl);
////        File ingressfile =kubernetesOption.loadUrlFile(ingressFileUrl);
//          File deploymentfile =new File("D:\\k8s-yaml\\"+id+"deployment.yaml");
//          File servicefile=new File("D:\\k8s-yaml\\"+id+"service.yaml");
//          File ingressfile=new File("D:\\k8s-yaml\\"+id+"ingress.yaml");
//          File configmapfile=new File("D:\\k8s-yaml\\"+id+"configmap.yaml");
        File deploymentfile = new File("/k8syaml/" + id + "deployment.yaml");
        File servicefile = new File("/k8syaml/" + id + "service.yaml");
        File ingressfile = new File("/k8syaml/" + id + "ingress.yaml");
        File configmapfile = new File("/k8syaml/" + id + "configmap.yaml");
          deploymentfile=kubernetesOption.updateDeploymentFile(deploymentfile,filename);
          servicefile=kubernetesOption.updateServiceFile(servicefile,filename);
          ingressfile=kubernetesOption.updateIngressFile(ingressfile,filename,url);
          configmapfile=kubernetesOption.updateConfigMapFile(configmapfile,filename,flag);
        if(configmapfile!=null){
            kubernetesOption.createConfigMapFromYaml(configmapfile);
        }
        if(deploymentfile != null){
            try {
                kubernetesOption.createDeploymentFromYaml(deploymentfile);
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
      }
        if(servicefile != null){
            try {
                kubernetesOption.createServiceFromYaml(servicefile);
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(ingressfile != null){
            try {
                kubernetesOption.createIngressFromYaml(ingressfile);
            } catch (ApiException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 删除容器
     * @param id
     */
    public void deleteChallenge(int currentid,int id) {
        Map<String,String> strings =redisService.get(String.valueOf(currentid), String.valueOf(id));
        String containerId=strings.get("containerId");
        if(strings!=null) {
            kubernetesOption.deleteDeployment("deployment-"+containerId);
            kubernetesOption.deleteService("service-"+containerId);
            kubernetesOption.deleteIngress("ingress-"+containerId);
            kubernetesOption.deleteConfigMap("configmap-"+containerId);
           redisService.delete(String.valueOf(currentid), String.valueOf(id));
           redisService.deleteStatus(String.valueOf(currentid));
           redisTimerService.deleteTimer(String.valueOf(currentid)+"|"+String.valueOf(id));
        }
        else{
            throw new BaseException("容器已删除");
        }
    }

    /**
     * 获取简单题目信息
     * @return
     */
    public List<CyrangechallengesVO> getSimpleChallengesInfo() {
//        // 获取原始数据
//        List<Cyrangechallenges> cyrangechallenges = challengesMapper.getChallenges();
//
//        // 初始化目标集合
//        List<CyrangechallengesVO> cyrangechallengesVOList = new ArrayList<>();
//
//        // 遍历原始数据，逐个拷贝属性
//        for (Cyrangechallenges challenge : cyrangechallenges) {
//            CyrangechallengesVO vo = new CyrangechallengesVO();
//            BeanUtils.copyProperties(challenge, vo); // 拷贝属性
//            cyrangechallengesVOList.add(vo); // 添加到目标集合
//        }
//
//        // 返回整个列表
//        return cyrangechallengesVOList;
        int userId= BaseContext.getCurrentId();
        List<Cyrangechallenges> cyrangechallenges = challengesMapper.getChallenges();

        // 2. 获取当前用户的答题记录
        List<UserChallengeRecord> userRecords = challengesMapper.getUserChallengeRecords(userId);

        // 3. 将答题记录转换为 Map，方便快速查找
        Map<Long, Boolean> solvedMap = new HashMap<>();
        for (UserChallengeRecord record : userRecords) {
            if (record.getIsCorrect() == 1) { // 只记录答对的题目
                solvedMap.put(record.getChallengeId(), true);
            }
        }

        // 4. 初始化目标集合
        List<CyrangechallengesVO> cyrangechallengesVOList = new ArrayList<>();

        // 5. 遍历原始数据，逐个拷贝属性并设置 solved 字段
        for (Cyrangechallenges challenge : cyrangechallenges) {
            CyrangechallengesVO vo = new CyrangechallengesVO();
            BeanUtils.copyProperties(challenge, vo); // 拷贝属性

            // 根据答题记录设置 solved 字段
            vo.setSolved(solvedMap.containsKey((long) challenge.getQuestionId()));

            cyrangechallengesVOList.add(vo); // 添加到目标集合
        }

        // 6. 返回整个列表
        return cyrangechallengesVOList;
    }

    /**
     * 获取题目详细信息
     * @param id
     * @return
     */
    public CyrangechallengesInfoVO getMoreChallengesInfo(int id) {
        Cyrangechallenges cyrangechallenges = challengesMapper.getChallenge(id);
        CyrangechallengesInfoVO cyrangechallengesInfoVO=new CyrangechallengesInfoVO();
        BeanUtils.copyProperties(cyrangechallenges,cyrangechallengesInfoVO);
        return  cyrangechallengesInfoVO;
    }

    /**
     * Change access modifier
     * @param
     * @return
     */
    public List<Categories> getCategories() {
        List<Categories> categories =challengesMapper.getCategories();
        return  categories;
    }

    @Override
    public Map<String, String> getIsStart(int id) {
        int currentid= BaseContext.getCurrentId();
        if(!redisTimerService.getStatus(String.valueOf(currentid)+"|"+String.valueOf(id))){
            return null;
        }
        Map<String,String> containers=redisService.get(String.valueOf(currentid),String.valueOf(id));
        String containerId=containers.get("containerId");
        if(containerId==null) {
            return null;
        }
        String time= String.valueOf(redisTimerService.getRemainingTime(String.valueOf(currentid)+"|"+String.valueOf(id)));
        String suffix = ".challenge.cyrange.xin";
        // 拼接UUID字符串和后缀
        String url ="http://"+ containerId + suffix;
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("time", time);
        resultMap.put("url", url);
        log.info(time);
        log.info(url);
        return resultMap;
    }

    /**
     * 检验flag
     * @param submissionInfo
     */
    public void checkFlag(SubmissionInfo submissionInfo) {
        int currentUserId= BaseContext.getCurrentId();
        int id=submissionInfo.getQuestionId();
        if(!redisTimerService.getStatus(String.valueOf(currentUserId)+"|"+String.valueOf(id))){
            throw new BaseException("容器未创建");
        }
        String flag=submissionInfo.getSubmission().strip();

        Map<String,String> containers=redisService.get(String.valueOf(currentUserId),String.valueOf(id));
        String containerId=containers.get("containerId");
        log.info(containers.get("flag"));
        if(containerId==null){
            throw new BaseException("容器未创建");
        }
        if(!flag.equals(containers.get("flag"))){
            throw new BaseException("不对哟");
        }
        else{
            if (!hasUserSolvedQuestion(currentUserId, id)) {
                recordUserSubmission(currentUserId, id, true); // true 表示答对
                // 6. 更新题目统计数据
                updateChallengeStatistics(id);
                //更新用户积分
                addUserRecord(currentUserId, id);
                // 7. 返回成功信息（可以通过返回值或异常处理）
                log.info("用户 {} 答对了题目 {}", currentUserId, id);
            }
        }
    }

    /**
     * 检查用户是否已经答对过某个题目
     * @param userId 用户 ID
     * @param questionId 题目 ID
     * @return 是否已经答对
     */
    private boolean hasUserSolvedQuestion(int userId, int questionId) {
        // 查询用户的答题记录，检查是否有答对的记录
        return challengesMapper.existsByUserIdAndQuestionIdAndIsCorrect(userId, questionId,true);
    }

    /**
     * 记录用户的答题结果
     * @param userId 用户 ID
     * @param questionId 题目 ID
     * @param isCorrect 是否答对
     */
    private void recordUserSubmission(int userId, int questionId, boolean isCorrect) {
        UserChallengeRecord record = new UserChallengeRecord();
        record.setUserId(Long.valueOf(userId));
        record.setChallengeId(Long.valueOf(questionId));
        record.setIsCorrect(isCorrect ? 1 : 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        record.setFinishTime(LocalDateTime.now().format(formatter));
        challengesMapper.insert(record);
    }

    private void addUserRecord(int userId, int questionId){
        int point=challengesMapper.getChallengePointById(questionId);
        challengesMapper.addUserRecord(userId,point);
    }
    /**
     * 更新题目的统计数据
     * @param questionId 题目 ID
     */
    private void updateChallengeStatistics(int questionId) {
        // 更新题目的正确答题人数
        challengesMapper.incrementParticipants(questionId); // 参与人数 +1
    }

    /**
     * 容器延时
     * @param id
     */
    public void extendTime(int id) {
        int currentid= BaseContext.getCurrentId();
        if(!redisTimerService.getStatus(String.valueOf(currentid)+"|"+String.valueOf(id))){
            throw  new BaseException("容器未创建");
        }
        Map<String,String> containers=redisService.get(String.valueOf(currentid),String.valueOf(id));
        String containerId=containers.get("containerId");
        if(containerId==null) {
            throw  new BaseException("容器未创建");
        }
        redisTimerService.extendTimer(String.valueOf(currentid), String.valueOf(id),3600, TimeUnit.SECONDS);
    }
}
