package com.scsse.workflow.util.DAOUtil;

import com.scsse.workflow.entity.dto.ActivityDto;
import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.dto.UserDetailPage;
import com.scsse.workflow.entity.dto.UserDto;
import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author Alfred Fu
 * Created on 2019/9/22 7:02 下午
 */
@Component
public class DtoTransferHelper {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Autowired
    public DtoTransferHelper(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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

    @Transactional
    public UserDetailPage transferToUserDetailPage(User user) {
        UserDetailPage result = modelMapper.map(user, UserDetailPage.class);
        Set<User> colleagueSet = new HashSet<>();
        user.getApplyRecruits().forEach(recruit -> colleagueSet.addAll(recruit.getMembers()));
        result.setColleagueNumber(colleagueSet.size());
        result.setFollowerNumber(userRepository.findFollowerNumberByUserId(user.getUserId()));
        result.setFollowingPeopleNumber(user.getFollowUser().size());
        return result;
    }

    public List<UserDetailPage> transferToUserDetailPage(Collection<User> userSet) {
        List<UserDetailPage> result = new ArrayList<>();
        userSet.stream().map(this::transferToUserDetailPage).forEach(result::add);
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
