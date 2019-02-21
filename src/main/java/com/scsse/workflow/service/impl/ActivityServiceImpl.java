package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.Activity;
import com.scsse.workflow.repository.ActivityRepository;
import com.scsse.workflow.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:18
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private final ModelMapper modelMapper;

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ModelMapper modelMapper, ActivityRepository activityRepository) {
        this.modelMapper = modelMapper;
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> findAllActivity() {
        return activityRepository.findAllActivity();
    }

    @Override
    public Activity findActivityById(Integer activityId) {
        return activityRepository.findByActivityId(activityId);
    }

    @Override
    public Object createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Object updateActivity(Activity activity) {
        Integer activityId = activity.getActivityId();
        Activity oldActivity = activityRepository.findByActivityId(activityId);
        modelMapper.map(activity, oldActivity);
        return activityRepository.save(oldActivity);
    }

    @Override
    public void deleteActivityById(Integer activityId) {
        Activity old_activity = activityRepository.findByActivityId(activityId);
        activityRepository.delete(old_activity);
    }
}
