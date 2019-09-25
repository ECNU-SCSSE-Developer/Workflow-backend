package com.scsse.workflow.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alfred Fu
 * Created on 2019/9/24 8:36 下午
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDetailPage extends UserDto {
    int colleagueNumber;  // 同事数
    int followerNumber;   // 粉丝数
    int followingPeopleNumber;  // 关注数
}
