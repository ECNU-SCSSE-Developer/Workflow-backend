package com.scsse.workflow.service;

import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:17
 */
@Service
public interface RecruitService {

    List<RecruitDto> findPaginationRecruit(Integer pageNum, Integer pageSize);

    List<RecruitDto> findPaginationRecruitWithCriteria(Integer pageNum, Integer pageSize, final HashMap<String, String> queryParam);

    Recruit findRecruitById(Integer recruitId);

    Recruit createRecruit(Recruit recruit);

    Recruit updateRecruit(Recruit recruit);

    void deleteRecruitById(Integer recruitId);

    /**
     * 将应聘成功的应聘者加入recruit_member中
     *
     * @param userId    用户主键
     * @param recruitId 招聘主键
     */
    void addMember(Integer userId, Integer recruitId);

    /**
     * 删除某个应聘中的某个成员
     *
     * @param userId    用户主键
     * @param recruitId 招聘主键
     */
    void removeMember(Integer userId, Integer recruitId);

    /**
     * 返回一个招聘信息的所有应聘成功的应聘者
     *
     * @param recruitId 招聘主键
     * @return List{User}
     */
    Set<User> findAllMemberOfRecruit(Integer recruitId);

    /**
     * 返回一个招聘信息的所有关注者
     *
     * @param recruitId 招聘主键
     * @return List{User}
     */
    Set<User> findAllFollowerOfRecruit(Integer recruitId);

    /**
     * 返回一个招聘信息的所有申请者
     *
     * @param recruitId 招聘主键
     * @return List{User}
     */
    Set<User> findAllApplicantOfRecruit(Integer recruitId);

    /**
     * 返回一个招聘信息的所有tag
     *
     * @param recruitId 招聘主键
     * @return List{Tag}
     */
    Set<Tag> findAllTagOfRecruit(Integer recruitId);

    /**
     * 给一个招聘信息绑定一个tag
     *
     * @param recruitId 招聘主键
     * @param tagId     标签
     */
    void bindTagToRecruit(Integer recruitId, Integer tagId);

    /**
     * 给一个招聘信息解绑一个tag
     *
     * @param recruitId 招聘主键
     * @param tagId     标签
     */
    void unBindTagToRecruit(Integer recruitId, Integer tagId);


    List<RecruitDto> transferRecruitToListDto(Set<Recruit> recruitSet, User user);

    RecruitDto transferRecruitToDto(Recruit recruit, User user);


}
