package com.scsse.workflow.service;

import com.scsse.workflow.entity.Activity;
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





}
