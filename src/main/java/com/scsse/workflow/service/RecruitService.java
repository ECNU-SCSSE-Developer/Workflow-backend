package com.scsse.workflow.service;

import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:17
 */
@Service
public interface RecruitService {

    List<Recruit> findAllRecruit();
    Recruit findRecruitById(Integer recruitId);
    Recruit createRecruit(Recruit recruit);
    Recruit updateRecruit(Recruit recruit);
    void deleteRecruitById(Integer recruitId);

    /**
     * 将应聘成功的应聘者加入recruit_member中
     * @param userId
     * @param recruitId
     */
    void bindApplicantToRecruit(Integer userId, Integer recruitId);

    /**
     * 删除某个应聘中的某个成员
     * @param userId
     * @param recruitId
     */
    void unBindApplicantToRecruit(Integer userId, Integer recruitId);

    /**
     * 返回一个招聘信息的所有应聘成功的应聘者
     * @param recruitId
     * @return List{User}
     */
    Set<User> findAllMemberOfRecruit(Integer recruitId);

    /**
     * 返回一个招聘信息的所有关注者
     * @param recruitId
     * @return List{User}
     */
    Set<User> findAllFollowerOfRecruit(Integer recruitId);

    /**
     * 返回一个招聘信息的所有申请者
     * @param recruitId
     * @return List{User}
     */
    Set<User> findAllApplicantOfRecruit(Integer recruitId);

    /**
     * 返回一个招聘信息的所有tag
     * @param recruitId
     * @return List{Tag}
     */
    Set<Tag> findAllTagOfRecruit(Integer recruitId);

    /**
     * 给一个招聘信息绑定一个tag
     * @param recruitId
     * @param tagId
     */
    void bindTagToRecruit(Integer recruitId, Integer tagId);

    /**
     * 给一个招聘信息解绑一个tag
     * @param recruitId
     * @param tagId
     */
    void unBindTagToRecruit(Integer recruitId, Integer tagId);
}
