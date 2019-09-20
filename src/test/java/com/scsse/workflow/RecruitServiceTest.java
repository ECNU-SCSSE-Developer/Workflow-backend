package com.scsse.workflow;

import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.service.ActivityService;
import com.scsse.workflow.service.RecruitService;
import com.scsse.workflow.service.TagService;
import com.scsse.workflow.util.PredicateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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




    @Test
    public void bindTagToRecruitTest() {
        // pre-condition
        Activity activity = activityService.createActivity(new Activity("testActivity"));
        Recruit recruit = recruitService.createRecruit(new Recruit("testRecruit", activity));
        Tag tag = tagService.createTag(new Tag("testTag", "testTagDes"));
        assertNotNull(activity);
        assertNotNull(recruit);
        assertNotNull(tag);
        // test binding
        recruit.getRecruitTags().add(tag);
        recruit = recruitService.updateRecruit(recruit);

        assertTrue(
                recruitService.findRecruitById(recruit.getRecruitId())
                        .getRecruitTags()
                        .contains(tag)
        );
        // test unbinding
        recruit.getRecruitTags().remove(tag);
        recruit = recruitService.updateRecruit(recruit);
        assertFalse(
                recruitService.findRecruitById(recruit.getRecruitId())
                        .getRecruitTags()
                        .contains(tag)
        );
    }
}
