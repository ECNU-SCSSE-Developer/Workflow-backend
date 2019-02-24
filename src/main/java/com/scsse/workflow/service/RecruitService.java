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
     * @return
     */
    List<User> findAllMemberOfRecruit(Integer recruitId);

    /**
     * 返回一个招聘信息的所有关注者
     * @param recruitId
     * @return
     */
    List<User> findAllFollowerOfRecruit(Integer recruitId);

    /**
     * 返回一个招聘信息的所有申请者
     * @param recruitId
     * @return
     */
    List<User> findAllApplicantOfRecruit(Integer recruitId);
}
