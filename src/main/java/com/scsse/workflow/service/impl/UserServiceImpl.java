package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.User;
import com.scsse.workflow.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:19
 */


@Component
public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(Integer userId) {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(Integer userId) {

    }
}
