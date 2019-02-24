package com.scsse.workflow.entity.dto;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @Author JJJJJJ
 * @Date 2019/2/24 20:14
 */
public class RecruitDetailDto {

    private Integer recruitId;

    private String recruitName;

    private String recruitPosition;

    private String recruitDescription;

    private String recruitState;

    private Integer recruitWillingNumber;

    private Integer recruitRegisteredNumber;

    // 创建者
    private Integer userId;

    private Integer activityId;

    private String activityName;
}
