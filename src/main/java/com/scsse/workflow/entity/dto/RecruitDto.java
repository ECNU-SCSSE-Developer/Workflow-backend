package com.scsse.workflow.entity.dto;

import com.scsse.workflow.entity.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alfred Fu
 * Created on 2019/9/18 8:34 上午
 */
@Getter
@Setter
@NoArgsConstructor
public class RecruitDto {
    private Integer recruitId;

    private String recruitName;

    private String state;

    private Integer recruitWillingNumber;

    private Integer recruitRegisteredNumber;

    private Integer activityId;

    private String activityName;

    // 是否关注
    private boolean isFollowed = false;
    // 是否申请
    private boolean isApplied = false;
    // 是否成功加入
    private boolean isAssigned = false;

    // 组织者
    private User organizer;
}
