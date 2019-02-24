package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.User;
import com.scsse.workflow.repository.UserRepository;
import com.scsse.workflow.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:19
 */


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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
}
