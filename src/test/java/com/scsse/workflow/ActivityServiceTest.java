package com.scsse.workflow;

import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.service.ActivityService;
import com.scsse.workflow.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @Author JJJJJJ
 * @Date 2019/2/21 19:50
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ActivityServiceTest {

    private final static Logger logger = LoggerFactory.getLogger(ActivityServiceTest.class);

    @Autowired
    ActivityService activityService;

    @Autowired
    TagService tagService;

    @Test
    public void test() {
    }

    @Test
    public void bindTagToActivityTest(){
        Activity activity = new Activity();
        activity.setActivityName("test_activity");
        activity = activityService.createActivity(activity);
        Tag tag = new Tag();
        tag.setTagName("test_tag");
        tag = tagService.createTag(tag);
        // pre-condition
        assertNotNull(activityService.findActivityById(activity.getActivityId()));
        assertNotNull(tagService.findTagById(tag.getTagId()));
        // test binding
        activityService.bindTagToActivity(activity.getActivityId(),tag.getTagId());
        assertTrue(
                activityService.findActivityById(activity.getActivityId()).getActivityTags().contains(
                        tagService.findTagById(tag.getTagId())
                )
        );
        // test unbind
        activityService.unBindTagToActivity(activity.getActivityId(),tag.getTagId());
        assertFalse(
                activityService.findActivityById(activity.getActivityId()).getActivityTags().contains(
                        tagService.findTagById(tag.getTagId())
                )
        );
    }

}
