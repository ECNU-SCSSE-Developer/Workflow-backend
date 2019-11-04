package com.scsse.workflow.service;

import com.scsse.workflow.entity.dto.*;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;

import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:17
 */

public interface UserService {

    List<UserDto> findAllUser();

    UserDetailPage findUserDetail(Integer userId);

    UserDto createUser(User user);

    UserDto updateUser(User user);

    void deleteUserById(Integer userId);


    /**
     * 用户申请一条招聘
     *
     * @param userId    用户ID
     * @param recruitId 招聘ID
     */
    void registerRecruit(Integer userId, Integer recruitId);

    /**
     * 用户取消申请一条招聘
     *
     * @param userId    用户ID
     * @param recruitId 招聘ID
     */
    void unregisterRecruit(Integer userId, Integer recruitId);

    List<ActivityDto> findAllFollowedActivity(Integer userId);

    /**
     * 获取粉丝
     *
     * @param userId 用户ID
     */
    List<UserDto> findAllFollowedUser(Integer userId);

    /**
     * 获取关注的人
     *
     * @param userId 用户ID
     */
    List<UserDto> findAllFollowingUser(Integer userId);

    /**
     * 返回一个用户关注的所有招聘
     *
     * @param userId 用户ID
     * @return List{RecruitDto}
     */
    List<RecruitDto> findAllFollowedRecruit(Integer userId);

    /**
     * 返回一个用户申请的所有招聘
     *
     * @param userId 用户ID
     * @return List{RecruitDto}
     */
    List<RecruitDto> findAllRegisteredRecruit(Integer userId);

    /**
     * 返回一个用户加入的所有招聘
     *
     * @param userId 用户ID
     * @return List{RecruitDto}
     */
    List<RecruitDto> findAllAssignedRecruit(Integer userId);

    /**
     * 返回一个用户绑定的所有tag
     *
     * @param userId 用户ID
     * @return List{Tag}
     */
    Set<Tag> findAllTagOfUser(Integer userId);

    /**
     * 给一个user绑定一个tag
     *
     * @param userId 用户ID
     * @param tagId  标签ID
     */
    void bindTagToUser(Integer userId, Integer tagId);

    /**
     * 给一个user解绑一个tag
     *
     * @param userId 用户ID
     * @param tagId  标签ID
     */
    void unBindTagToUser(Integer userId, Integer tagId);

    void followActivity(Integer userId, Integer activityId);

    void unfollowActivity(Integer userId, Integer activityId);

    void followUser(Integer originUserId, Integer followUserId);

    void unfollowUser(Integer originUserId, Integer followUserId);

    void followRecruit(Integer userId, Integer recruitId);

    void unfollowRecruit(Integer userId, Integer recruitId);


    List<TeamDto> findJoinedTeam(User user);

    List<TeamDto> findCreatedTeam(User user);


}
