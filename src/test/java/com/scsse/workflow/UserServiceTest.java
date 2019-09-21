package com.scsse.workflow;


import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author Alfred Fu
 * Created on 2019/9/15 2:42 下午
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void generateUser() {
        for (int i = 0; i < 2; i++) {
            userService.createUser(new User(
                    "testUser" + i, "INVALID_OPEN_ID_" + i
            ));
        }
    }
}
