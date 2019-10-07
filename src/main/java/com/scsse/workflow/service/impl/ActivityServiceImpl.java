package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.dto.ActivityDto;
import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.ActivityRepository;
import com.scsse.workflow.repository.RecruitRepository;
import com.scsse.workflow.repository.TagRepository;
import com.scsse.workflow.service.ActivityService;
import com.scsse.workflow.util.DAOUtil.DtoTransferHelper;
import com.scsse.workflow.util.DAOUtil.UserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:18
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private final ModelMapper modelMapper;

    private final UserUtil userUtil;

    private final DtoTransferHelper dtoTransferHelper;

    private final ActivityRepository activityRepository;

    private final RecruitRepository recruitRepository;

    private final TagRepository tagRepository;

    @Autowired
    public ActivityServiceImpl(ModelMapper modelMapper, UserUtil userUtil, DtoTransferHelper dtoTransferHelper, ActivityRepository activityRepository, RecruitRepository recruitRepository, TagRepository tagRepository) {
        this.modelMapper = modelMapper;
        this.userUtil = userUtil;
        this.dtoTransferHelper = dtoTransferHelper;
        this.activityRepository = activityRepository;
        this.recruitRepository = recruitRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<ActivityDto> findAllActivity() {
        return dtoTransferHelper.transferToListDto(activityRepository.findAll(), eachItem -> dtoTransferHelper.transferToActivityDto((Activity) eachItem));
    }

    @Override
    public List<ActivityDto> findAllExpiredActivity() {
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().stream()
                // if the time now is greater than the signUpDeadline
                .filter(activity -> LocalDate.now().compareTo
                        (activity.getActivitySignUpDeadline().toInstant().atZone(ZoneId.of("Asia/Shanghai")).
                                toLocalDate()) > 0)
                // but less than the activity time
                .filter(activity -> LocalDate.now().compareTo
                        (activity.getActivityTime().toInstant().atZone(ZoneId.of("Asia/Shanghai")).
                                toLocalDate()) < 0)
                .forEach(activities::add);
        return dtoTransferHelper.transferToListDto(activities, eachItem -> dtoTransferHelper.transferToActivityDto((Activity) eachItem));
    }

    @Override
    public List<ActivityDto> findAllFinishedActivity() {
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().stream()
                // if the time now is greater than the activity time
                .filter(activity -> LocalDate.now().compareTo
                        (activity.getActivityTime().toInstant().atZone(ZoneId.of("Asia/Shanghai")).
                                toLocalDate()) > 0)
                .forEach(activities::add);
        return dtoTransferHelper.transferToListDto(activities, eachItem -> dtoTransferHelper.transferToActivityDto((Activity) eachItem));

    }

    @Override
    public List<ActivityDto> findAllFreshActivity() {
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().stream()
                // if the time now is less than the signUpDeadline
                .filter(activity -> LocalDate.now().compareTo
                        (activity.getActivitySignUpDeadline().toInstant().atZone(ZoneId.of("Asia/Shanghai")).
                                toLocalDate()) < 0)
                .forEach(activities::add);
        return dtoTransferHelper.transferToListDto(activities, eachItem -> dtoTransferHelper.transferToActivityDto((Activity) eachItem));

    }

    @Override
    public Activity findActivityById(Integer activityId) {
        return activityRepository.findByActivityId(activityId);
    }

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        Integer activityId = activity.getActivityId();
        Activity oldActivity = activityRepository.findByActivityId(activityId);
        modelMapper.map(activity, oldActivity);
        return activityRepository.save(oldActivity);
    }

    @Override
    public void deleteActivityById(Integer activityId) {
        activityRepository.deleteById(activityId);
    }

    @Override
    public List<RecruitDto> findAllRecruitOfActivity(Integer activityId) {
        return dtoTransferHelper.transferToListDto(
                recruitRepository.findAllByActivity_ActivityId(activityId), userUtil.getLoginUser(),
                (firstParam, secondParam) -> dtoTransferHelper.transferToRecruitDto((Recruit) firstParam,(User) secondParam)
        );
    }

    @Override
    public Set<Tag> findAllTagOfActivity(Integer activityId) {
        Activity activity = activityRepository.findByActivityId(activityId);
        return activity.getActivityTags();
    }

    @Override
    public void bindTagToActivity(Integer activityId, Integer tagId) {
        Activity activity = activityRepository.findByActivityId(activityId);
        Tag tag = tagRepository.findByTagId(tagId);
        if (activity != null && tag != null && !activity.getActivityTags().contains(tag)) {
            activity.getActivityTags().add(tag);
            activityRepository.save(activity);
        }

    }

    @Override
    public void unBindTagToActivity(Integer activityId, Integer tagId) {
        Activity activity = activityRepository.findByActivityId(activityId);
        activity.getActivityTags().remove(tagRepository.findByTagId(tagId));
        activityRepository.save(activity);
    }
}
