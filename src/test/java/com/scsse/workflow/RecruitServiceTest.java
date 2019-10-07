package com.scsse.workflow;

import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.service.ActivityService;
import com.scsse.workflow.service.RecruitService;
import com.scsse.workflow.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alfred Fu
 * Created on 2019/9/16 6:18 下午
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class RecruitServiceTest {

    private final static Logger logger = LoggerFactory.getLogger(RecruitServiceTest.class);

    @Resource
    TagService tagService;

    @Resource
    RecruitService recruitService;

    @Resource
    ActivityService activityService;

    @Test
    public void generateRecruit() {
        Activity activity = activityService.createActivity(new Activity("testActivity"));

        for (int i = 0; i < 10; i++) {
            recruitService.createRecruit(
                    new Recruit("testRecruit" + i, activity)
            );
        }
    }
}

