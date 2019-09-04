package com.scsse.workflow.service;

import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;

import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:17
 */

public interface UserService {

    List<User> findAllUser();
    User findUserById(Integer userId);
    User createUser(User user);
    User updateUser(User user);
    void deleteUserById(Integer userId);

    /**
     * 用户关注一条招聘
     * @param userId
     * @param recruitId
     */
    void bindFollowedRecruitToUser(Integer userId, Integer recruitId);

    /**
     * 用户申请一条招聘
     * @param userId
     * @param recruitId
     */
    void bindRegisteredRecruitToUser(Integer userId, Integer recruitId);

    /**
     * 用户取消关注一条招聘
     * @param userId
     * @param recruitId
     */
    void unBindFollewedRecruitToUser(Integer userId, Integer recruitId);

    /**
     * 用户取消申请一条招聘
     * @param userId
     * @param recruitId
     */
    void unBindRegisteredRecruitToUser(Integer userId, Integer recruitId);

    /**
     * 返回一个用户关注的所有招聘
     * @param userId
     * @return
     */
    List<Recruit> findAllFollowedRecruit(Integer userId);

    /**
     * 返回一个用户应聘的所有招聘
     * @param userId
     * @return
     */
    List<Recruit> findAllRegisteredRecruit(Integer userId);

    /**
     * 返回一个用户绑定的所有tag
     * @param userId
     * @return List{Tag}
     */
    Set<Tag> findAllTagOfUser(Integer userId);

    /**
     * 给一个user绑定一个tag
     * @param userId
     * @param tagId
     */
    void bindTagToUser(Integer userId, Integer tagId);

    /**
     * 给一个user解绑一个tag
     * @param userId
     * @param tagId
     */
    void unBindTagToUser(Integer userId, Integer tagId);
}
