package com.scsse.workflow.service;

import com.scsse.workflow.entity.Recruit;
import com.scsse.workflow.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
