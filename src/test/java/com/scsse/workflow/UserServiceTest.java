package com.scsse.workflow;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

/**
 * @author Alfred Fu
 * Created on 2019/9/15 2:42 下午
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {
}
