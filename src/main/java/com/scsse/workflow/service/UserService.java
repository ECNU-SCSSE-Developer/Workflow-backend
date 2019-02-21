package com.scsse.workflow.service;

import com.scsse.workflow.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:17
 */
@Service
public interface UserService {

    User findUserById(Integer userId);
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(Integer userId);
}
