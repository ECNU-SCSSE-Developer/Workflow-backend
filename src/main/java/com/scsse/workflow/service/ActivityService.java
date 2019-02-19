package com.scsse.workflow.service;

import com.scsse.workflow.entity.Activity;
import org.springframework.stereotype.Component;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:14
 */
@Component
public interface ActivityService {

    Activity findActivityById(Integer activityId);
    void createActivity(Activity activity);
    void updateActivity(Activity activity);
    void deleteActivityById(Integer activityId);

}
