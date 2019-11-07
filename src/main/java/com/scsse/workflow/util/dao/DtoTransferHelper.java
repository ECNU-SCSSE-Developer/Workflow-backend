package com.scsse.workflow.util.dao;

import com.scsse.workflow.entity.dto.*;
import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Team;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Alfred Fu
 * Created on 2019/9/22 7:02 下午
 */
@Component
public class DtoTransferHelper {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(DtoTransferHelper.class);


    @Autowired
    public DtoTransferHelper(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public <T> List<T> transferToListDto(Collection<?> collection, TransferToListDtoOneParam<T> method) {
        List<T> result = new ArrayList<>();
        collection.stream().map(method::transferToDto).forEach(result::add);
        return result;
    }

    /**
     * Experimental!
     * 通过反射自动查找方法来转换Dto List
     *
     * Requirements:
     *
     * Supposed that the class name of the instance is className, then
     *
     * 1. the DTO class named with className+'Dto' should exists.
     * 2. the method named with 'transferTo' + className + 'Dto' exists.
     *
     * @param collection Prototype List
     * @return Dto List
     */
    public <T> List<T> transferToListDto(Collection<?> collection) {
        final String PATTERN_PREFIX = "transferTo";
        final String PATTERN_POSTFIX = "(Dto)?";
        final String LIST_ADD = "add";

        if (collection.toArray().length > 0) {
            Class instanceClass = collection.toArray()[0].getClass();
            // search methods in this helper to find if there is a suitable method to transfer instance
            for (Method method : this.getClass().getMethods()) {
                // if matches then call the method
                if (method.getName().matches(PATTERN_PREFIX + instanceClass.getSimpleName() + PATTERN_POSTFIX)) {
                    List<T> result = new ArrayList<>();
                    collection.stream().map(object -> {
                        // call the method to cast the class
                        try {
                            Class dtoClass = method.getReturnType();
                            return dtoClass.cast(method.invoke(this, object));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            logger.error(e.getMessage(), e);
                            return null;
                        }
                    }).forEach(
                        // then add it to the list
                            object -> {
                                try {
                                    result.getClass().getMethod(LIST_ADD, Object.class)
                                        .invoke(result, object);
                                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                    logger.error(e.getMessage(), e);
                                }
                            }
                    );
                    return result;
                }
            }
        }
        return new ArrayList<>();
    }


    public <T> List<T> transferToListDto(Collection<?> collection, Object secondParam, TransferToListDtoTwoParam<T> method) {
        List<T> result = new ArrayList<>();
        collection.stream().map(each -> method.transferToDto(each, secondParam)).forEach(result::add);
        return result;
    }

    public RecruitDto transferToRecruitDto(Recruit recruit, User user) {
        RecruitDto result = modelMapper.map(recruit, RecruitDto.class);
        // CAUTION
        if (user != null) {
            if (user.getApplyRecruits().contains(recruit)) {
                result.setApplied(true);
            }
            if (user.getFollowRecruits().contains(recruit)) {
                result.setFollowed(true);
            }
            if (user.getSuccessRecruits().contains(recruit)) {
                result.setAssigned(true);
            }
        }
        result.setOrganizer(recruit.getManager());
        return result;
    }

    public UserDto transferToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
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


    public ActivityDto transferToActivityDto(Activity activity) {
        return modelMapper.map(activity, ActivityDto.class);
    }


    public TeamDto transferToTeamDto(Team team) {
        TeamDto result = new TeamDto();
        modelMapper.map(team, result);
        return result;
    }


}
