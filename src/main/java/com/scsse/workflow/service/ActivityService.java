package com.scsse.workflow.service;

import com.scsse.workflow.entity.Activity;
import com.scsse.workflow.entity.Recruit;
import com.scsse.workflow.entity.Tag;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:14
 */
public interface ActivityService {

    List<Activity> findAllActivity();
    Activity findActivityById(Integer activityId);
    Activity createActivity(Activity activity);
    Activity updateActivity(Activity activity);
    void deleteActivityById(Integer activityId);

    /**
     * 返回一个比赛的所有的招聘
     * @param activityId
     * @return List<Recruit>
     */
    List<Recruit> findAllRecruitOfActivity(Integer activityId);

    /**
     * 返回一个比赛的所有tag
     * @param activityId
     * @return List{Tag}
     */
    List<Tag> findAllTagOfActivity(Integer activityId);

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
