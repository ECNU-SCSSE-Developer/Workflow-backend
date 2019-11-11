package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.dto.*;
import com.scsse.workflow.entity.model.*;
import com.scsse.workflow.repository.*;
import com.scsse.workflow.service.UserService;
import com.scsse.workflow.util.dao.DtoTransferHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:19
 */


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final DtoTransferHelper dtoTransferHelper;

    private final RecruitRepository recruitRepository;

    private final UserRepository userRepository;

    private final TagRepository tagRepository;

    private final ActivityRepository activityRepository;

    private final TeamRepository teamRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, DtoTransferHelper dtoTransferHelper, RecruitRepository recruitRepository, UserRepository userRepository, TagRepository tagRepository, ActivityRepository activityRepository, TeamRepository teamRepository) {
        this.modelMapper = modelMapper;
        this.dtoTransferHelper = dtoTransferHelper;
        this.recruitRepository = recruitRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.activityRepository = activityRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<UserDto> findAllUser() {
        return dtoTransferHelper.transferToListDto(userRepository.findAll(), eachItem -> dtoTransferHelper.transferToUserDto((User) eachItem));
    }

    @Override
    public UserDetailPage findUserDetail(Integer userId) {
        return dtoTransferHelper.transferToUserDetailPage(userRepository.findByUserId(userId));
    }

    @Override
    public UserDto createUser(User user) {
        return dtoTransferHelper.transferToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(User user) {
        Integer userId = user.getUserId();
        User oldUser = userRepository.findByUserId(userId);
        modelMapper.map(user, oldUser);
        return dtoTransferHelper.transferToUserDto(userRepository.save(oldUser));

    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public void followRecruit(Integer userId, Integer recruitId) {
        User user = userRepository.findByUserId(userId);
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        if (user != null && recruit != null) {
            user.getFollowRecruits().add(recruit);
            userRepository.save(user);
        }
    }

    @Override
    public void registerRecruit(Integer userId, Integer recruitId) {
        User user = userRepository.findByUserId(userId);
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        if (user != null && recruit != null) {
            user.getApplyRecruits().add(recruit);
            userRepository.save(user);
        }
    }

    @Override
    public void unfollowRecruit(Integer userId, Integer recruitId) {
        User user = userRepository.findByUserId(userId);
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        if (user != null && recruit != null) {
            user.getFollowRecruits().remove(recruit);
            userRepository.save(user);
        }
    }

    @Override
    public void unregisterRecruit(Integer userId, Integer recruitId) {
        User user = userRepository.findByUserId(userId);
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        if (user != null && recruit != null) {
            user.getApplyRecruits().remove(recruit);
            userRepository.save(user);
        }
    }

    @Override
    public List<ActivityDto> findAllFollowedActivity(Integer userId) {
        User user = userRepository.findByUserId(userId);
        Set<Activity> activities = user.getFollowActivities();
        return dtoTransferHelper.transferToListDto(
                activities,
                activity -> dtoTransferHelper.transferToActivityDto((Activity) activity,user)
        );
    }

    @Override
    public List<UserDto> findAllFollowedUser(Integer userId) {
        return dtoTransferHelper.transferToListDto(userRepository.findUserFollower(userId));
    }

    @Override
    public List<UserDto> findAllFollowingUser(Integer userId) {
        User user =  userRepository.findByUserId(userId);
        return dtoTransferHelper.transferToListDto(user.getFollowUser());
    }

    @Override
    public List<RecruitDto> findAllFollowedRecruit(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return dtoTransferHelper.transferToListDto(
                    user.getFollowRecruits(), user,
                    (firstParam, secondParam) -> dtoTransferHelper.transferToRecruitDto((Recruit) firstParam, (User) secondParam)
            );
        } else {
            return null;
        }
    }

    @Override
    public List<RecruitDto> findAllRegisteredRecruit(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return dtoTransferHelper.transferToListDto(
                    user.getApplyRecruits(), user,
                    (firstParam, secondParam) -> dtoTransferHelper.transferToRecruitDto((Recruit) firstParam, (User) secondParam)
            );


        } else {
            return null;
        }
    }

    @Override
    public List<RecruitDto> findAllAssignedRecruit(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return dtoTransferHelper.transferToListDto(
                    user.getSuccessRecruits(), user,
                    (firstParam, secondParam) -> dtoTransferHelper.transferToRecruitDto((Recruit) firstParam, (User) secondParam)
            );
        } else {
            return null;
        }
    }

    @Override
    public Set<Tag> findAllTagOfUser(Integer userId) {
        User user = userRepository.findByUserId(userId);
        return user.getUserTags();
    }

    @Override
    public void bindTagToUser(Integer userId, Integer tagId) {
        User user = userRepository.findByUserId(userId);
        Tag tag = tagRepository.findByTagId(tagId);
        if (user != null && tag != null) {
            user.getUserTags().add(tag);
            userRepository.save(user);
        }
    }

    @Override
    public void unBindTagToUser(Integer userId, Integer tagId) {
        User user = userRepository.findByUserId(userId);
        Tag tag = tagRepository.findByTagId(tagId);
        if (user != null && tag != null) {
            user.getUserTags().remove(tag);
            userRepository.save(user);
        }
    }

    @Override
    public void followActivity(Integer userId, Integer activityId) {
        User user = userRepository.findByUserId(userId);
        Activity activity = activityRepository.findByActivityId(activityId);
        if (user != null && activity != null) {
            user.getFollowActivities().add(activity);
            userRepository.save(user);
        }
    }

    @Override
    public void unfollowActivity(Integer userId, Integer activityId) {
        User user = userRepository.findByUserId(userId);
        Activity activity = activityRepository.findByActivityId(activityId);
        if (user != null && activity != null) {
            user.getFollowActivities().remove(activity);
            userRepository.save(user);
        }
    }

    @Override
    public void followUser(Integer originUserId, Integer followUserId) {
        User originUser = userRepository.findByUserId(originUserId);
        User followUser = userRepository.findByUserId(followUserId);
        if (originUser != null && followUser != null) {
            originUser.getFollowUser().add(followUser);
            userRepository.save(originUser);
        }

    }

    @Override
    public void unfollowUser(Integer originUserId, Integer followUserId) {
        User originUser = userRepository.findByUserId(originUserId);
        User followUser = userRepository.findByUserId(followUserId);
        if (originUser != null && followUser != null) {
            originUser.getFollowUser().remove(followUser);
            userRepository.save(originUser);
        }
    }


    @Override
    public List<TeamDto> findJoinedTeam(User user) {
        return dtoTransferHelper.transferToListDto(
                teamRepository.findAllByMembersContains(user),
                eachItem -> dtoTransferHelper.transferToTeamDto((Team) eachItem)
        );
    }

    @Override
    public List<TeamDto> findCreatedTeam(User user) {
        return dtoTransferHelper.transferToListDto(
            teamRepository.findAllByManager_UserIdEquals(user.getUserId())
        );
    }

}
