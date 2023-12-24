package com.yupi.hitoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.hitoj.model.dto.question.QuestionQueryRequest;
import com.yupi.hitoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.hitoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.hitoj.model.entity.Question;
import com.yupi.hitoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hitoj.model.entity.User;
import com.yupi.hitoj.model.vo.QuestionSubmitVO;
import com.yupi.hitoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author wugensheng
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2023-12-23 16:04:24
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return 提交记录的ID
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目 封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
