package com.yupi.hitoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.yupi.hitoj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.hitoj.model.entity.QuestionSubmit;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 提目表
 * @TableName questionSubmit
 */
@TableName(value ="questionSubmit")
@Data
public class QuestionSubmitVO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID) // 非连续自增
    private Long id;

    /**
     * 语言
     */
    private String language;

    /**
     * 代码
     */
    private String code;

    /**
     * 判题信息 json 对象
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态 0-待判题 1-判题中 2-成功 3-失败
     */
    private Integer status;

    /**
     * 题目ID
     */
    private Long questionSubmitId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交题目人信息
     */
    private UserVO userVO;


    /**
     * 题目信息
     */
    private QuestionVO questionVO;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * 包装类转对象
     *
     * @param questionSubmitVO
     * @return
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        JudgeInfo judgeinfo = questionSubmitVO.getJudgeInfo();
        if (judgeinfo != null) {
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeinfo));
        }

        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param questionSubmit
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        JudgeInfo judgeInfo = JSONUtil.toBean(questionSubmit.getJudgeInfo(), JudgeInfo.class);
        questionSubmitVO.setJudgeInfo(judgeInfo);
        return questionSubmitVO;
    }
}