package com.yupi.hitoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.hitoj.annotation.AuthCheck;
import com.yupi.hitoj.common.BaseResponse;
import com.yupi.hitoj.common.ErrorCode;
import com.yupi.hitoj.common.ResultUtils;
import com.yupi.hitoj.constant.UserConstant;
import com.yupi.hitoj.exception.BusinessException;
import com.yupi.hitoj.model.dto.question.QuestionQueryRequest;
import com.yupi.hitoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.hitoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.hitoj.model.entity.Question;
import com.yupi.hitoj.model.entity.QuestionSubmit;
import com.yupi.hitoj.model.entity.User;
import com.yupi.hitoj.model.vo.QuestionSubmitVO;
import com.yupi.hitoj.service.QuestionSubmitService;
import com.yupi.hitoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交结果ID
     */
    @PostMapping("/")
    public BaseResponse<Integer> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);

        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success((int)questionSubmitId);
    }

    /**
     * 分页获取提交题目列表（除管理员外，普通用户只能看到非答案，提交代码等公开信息）
     *
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        // 脱敏
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, userService.getLoginUser(request)));
    }
}
