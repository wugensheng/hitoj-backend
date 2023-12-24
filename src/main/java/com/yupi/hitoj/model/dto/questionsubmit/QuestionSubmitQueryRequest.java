package com.yupi.hitoj.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yupi.hitoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 语言
     */
    private String language;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 判题状态 0-待判题 1-判题中 2-成功 3-失败
     */
    private Integer status;

    /**
     * 创建用户 id
     */
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}