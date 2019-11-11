package com.scsse.workflow.service;

import com.scsse.workflow.entity.dto.ActivityDto;
import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Tag;

import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:14
 */
public interface ActivityService {

    List<ActivityDto> findAllActivity();

    List<ActivityDto> findAllExpiredActivity();

    List<ActivityDto> findAllFinishedActivity();

    List<ActivityDto> findAllFreshActivity();


    ActivityDto findActivityById(Integer activityId);

    Activity createActivity(Activity activity);

    Activity updateActivity(Activity activity);

    void deleteActivityById(Integer activityId);

    List<RecruitDto> findAllRecruitOfActivity(Integer activityId);

    Set<Tag> findAllTagOfActivity(Integer activityId);

    void bindTagToActivity(Integer activityId, Integer tagId);

    void unBindTagToActivity(Integer activityId, Integer tagId);
}
