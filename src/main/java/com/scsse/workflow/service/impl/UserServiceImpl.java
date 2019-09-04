package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.TagRepository;
import com.scsse.workflow.repository.UserRepository;
import com.scsse.workflow.service.UserService;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private TagRepository tagRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, TagRepository tagRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<User> findAllUser(){
        return userRepository.findAll();
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
    public void bindFollowedRecruitToUser(Integer userId, Integer recruitId) {

    }

    @Override
    public void bindRegisteredRecruitToUser(Integer userId, Integer recruitId) {

    }

    @Override
    public void unBindFollewedRecruitToUser(Integer userId, Integer recruitId) {

    }

    @Override
    public void unBindRegisteredRecruitToUser(Integer userId, Integer recruitId) {

    }

    @Override
    public List<Recruit> findAllFollowedRecruit(Integer userId) {
        return null;
    }

    @Override
    public List<Recruit> findAllRegisteredRecruit(Integer userId) {
        return null;
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
        if(user != null && tag != null){
            user.getUserTags().add(tag);
            userRepository.save(user);
        }

    }

    @Override
    public void unBindTagToUser(Integer userId, Integer tagId) {

    }
}
