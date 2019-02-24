package com.scsse.workflow.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author JJJJJJ
 * @Date 2019/2/24 20:03
 */
@Setter
@Getter
public class RecruitListDto {

    private Integer recruitId;

    private String recruitName;

    private String state;

    private Integer recruitWillingNumber;

    private Integer recruitRegisteredNumber;

    private Integer activityId;

    private String activityName;

}
