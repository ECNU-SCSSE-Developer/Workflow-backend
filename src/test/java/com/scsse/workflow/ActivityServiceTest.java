package com.scsse.workflow;

import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.service.ActivityService;
import com.scsse.workflow.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author JJJJJJ
 * @Date 2019/2/21 19:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceTest {

    private final static Logger logger = LoggerFactory.getLogger(ActivityServiceTest.class);

    @Autowired
    ActivityService activityService;

    @Autowired
    TagService tagService;

    @Test
    public void test() {
    }

    @Test
    public void testBindTagToActivity(){
        Activity activity = new Activity();
        activity.setActivityName("test_activity1");
        activityService.createActivity(activity);
        Tag tag = new Tag();
        tag.setTagName("test_tag1");
        tagService.createTag(tag);
        activityService.bindTagToActivity(1,1);
    }

}
