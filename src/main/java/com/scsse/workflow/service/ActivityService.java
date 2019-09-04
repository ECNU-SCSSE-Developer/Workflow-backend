package com.scsse.workflow.service;

import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;

import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:14
 */
public interface ActivityService {

    List<Activity> findAllActivity();
    List<Activity> findAllExpiredActivity();
    List<Activity> findAllFinishedActivity();
    List<Activity> findAllFreshActivity();


    Activity findActivityById(Integer activityId);
    Activity createActivity(Activity activity);
    Activity updateActivity(Activity activity);
    void deleteActivityById(Integer activityId);

    /**
     * 返回一个比赛的所有的招聘
     * @param activityId
     * @return List<Recruit>
     */
    Set<Recruit> findAllRecruitOfActivity(Integer activityId);

    /**
     * 返回一个比赛的所有tag
     * @param activityId
     * @return List{Tag}
     */
    Set<Tag> findAllTagOfActivity(Integer activityId);

    /**
     * 给一个比赛绑定一个tag
     * @param activityId
     * @param tagId
     */
    void bindTagToActivity(Integer activityId, Integer tagId);

    /**
     * 给一个比赛解绑一个tag
     * @param activityId
     * @param tagId
     */
    void unBindTagToActivity(Integer activityId, Integer tagId);
}
