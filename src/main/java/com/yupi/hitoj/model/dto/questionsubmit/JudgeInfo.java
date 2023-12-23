package com.yupi.hitoj.model.dto.questionsubmit;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 消耗时间 （ms）
     */
    private Long time;

    /**
     * 消耗空间 （kb）
     */
    private Long memory;

    /**
     * 程序执行信息
     */
    private String message;

}
