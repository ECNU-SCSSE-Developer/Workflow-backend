package com.scsse.workflow.util;

import com.scsse.workflow.entity.dto.ActivityDto;
import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.dto.UserDto;
import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019/9/22 7:02 下午
 */
@Component
public class DtoTransferHelper {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoTransferHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RecruitDto transferToRecruitDto(Recruit recruit, User user) {
        RecruitDto result = modelMapper.map(recruit, RecruitDto.class);
        if (user.getApplyRecruits().contains(recruit)) {
            result.setApplied(true);
        }
        if (user.getFollowRecruits().contains(recruit)) {
            result.setFollowed(true);
        }
        if (user.getSuccessRecruits().contains(recruit)) {
            result.setAssigned(true);
        }
        result.setOrganizer(recruit.getManager());
        return result;
    }

    public List<RecruitDto> transferToRecruitDto(Collection<Recruit> recruitSet, User user) {
        List<RecruitDto> result = new ArrayList<>();
        recruitSet.stream().map(recruit -> transferToRecruitDto(recruit, user)).forEach(result::add);
        return result;
    }

    public UserDto transferToUserDto(User user) {
        UserDto result = modelMapper.map(user, UserDto.class);
        return result;
    }

    public List<UserDto> transferToUserDto(Collection<User> userSet) {
        List<UserDto> result = new ArrayList<>();
        userSet.stream().map(this::transferToUserDto).forEach(result::add);
        return result;
    }

    public ActivityDto transferToActivityDto(Activity activity) {
        ActivityDto result = modelMapper.map(activity, ActivityDto.class);
        return result;
    }

    public List<ActivityDto> transferToActivityDto(Collection<Activity> activitySet) {
        List<ActivityDto> result = new ArrayList<>();
        activitySet.stream().map(this::transferToActivityDto).forEach(result::add);
        return result;
    }

}
