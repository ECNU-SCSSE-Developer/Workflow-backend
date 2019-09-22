package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.dto.ActivityDto;
import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.dto.UserDto;
import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.ActivityRepository;
import com.scsse.workflow.repository.RecruitRepository;
import com.scsse.workflow.repository.TagRepository;
import com.scsse.workflow.repository.UserRepository;
import com.scsse.workflow.service.RecruitService;
import com.scsse.workflow.service.UserService;
import com.scsse.workflow.util.DtoTransferHelper;
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

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, DtoTransferHelper dtoTransferHelper, RecruitRepository recruitRepository, UserRepository userRepository, TagRepository tagRepository, ActivityRepository activityRepository) {
        this.modelMapper = modelMapper;
        this.dtoTransferHelper = dtoTransferHelper;
        this.recruitRepository = recruitRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public List<UserDto> findAllUser() {
        return dtoTransferHelper.transferToUserDto(userRepository.findAll());
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Integer userId = user.getUserId();
        User oldUser = userRepository.findByUserId(userId);
        modelMapper.map(user, oldUser);
        return userRepository.save(oldUser);

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
        return null;
    }

    @Override
    public List<UserDto> findAllColleague(Integer userId) {
        return null;
    }

    @Override
    public List<UserDto> findAllFollowedUser(Integer userId) {
        return null;
    }

    @Override
    public List<UserDto> findAllFollowingUser(Integer userId) {
        return null;
    }

    @Override
    public List<RecruitDto> findAllFollowedRecruit(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return dtoTransferHelper.transferToRecruitDto(user.getFollowRecruits(), user);
        } else {
            return null;
        }
    }

    @Override
    public List<RecruitDto> findAllRegisteredRecruit(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return dtoTransferHelper.transferToRecruitDto(user.getApplyRecruits(), user);
        } else {
            return null;
        }
    }

    @Override
    public List<RecruitDto> findAllAssignedRecruit(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return dtoTransferHelper.transferToRecruitDto(user.getSuccessRecruits(), user);
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
        if (originUser!=null && followUser!=null){
            originUser.getFollowUser().add(followUser);
            userRepository.save(originUser);
        }

    }

    @Override
    public void unfollowUser(Integer originUserId, Integer followUserId) {
        User originUser = userRepository.findByUserId(originUserId);
        User followUser = userRepository.findByUserId(followUserId);
        if (originUser!=null && followUser!=null){
            originUser.getFollowUser().remove(followUser);
            userRepository.save(originUser);
        }
    }
}
