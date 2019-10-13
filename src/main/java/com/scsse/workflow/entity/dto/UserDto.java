package com.scsse.workflow.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alfred Fu
 * Created on 2019/9/18 8:20 上午
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Integer userId;

    private String username;

    private String userNumber;

    private String userGrade;

    private String userPhone;

    private String userEmail;

    private String userSpecialty;

    private String userResume;

    private String wxId;

    private String college;
}
