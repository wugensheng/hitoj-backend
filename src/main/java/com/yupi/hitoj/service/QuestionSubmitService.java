package com.yupi.hitoj.service;

import com.yupi.hitoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.hitoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hitoj.model.entity.User;

/**
* @author wugensheng
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2023-12-23 16:04:24
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return 提交记录的ID
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param questionId
     * @return
     */
    int doQuestionSubmitInner(long userId, long questionId);
}
