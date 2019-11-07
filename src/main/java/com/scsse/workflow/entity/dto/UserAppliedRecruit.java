package com.scsse.workflow.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserAppliedRecruit extends UserDto {
    private int recruitId;

    private String recruitName;

    private String recruitPosition;

    private String recruitDescription;

    private String recruitState;

    private String activityName;
}
