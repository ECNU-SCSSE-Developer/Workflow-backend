package com.scsse.workflow;

import com.scsse.workflow.entity.Activity;
import com.scsse.workflow.entity.Tag;
import com.scsse.workflow.service.ActivityService;
import com.scsse.workflow.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    @Transactional
    public void test() {

        /*Activity newActivity = new Activity("test1", new Date(), "library", "testtesttesttest", new Date());
        activityService.createActivity(newActivity);

        Activity newActivity2 = new Activity("test2", new Date(), "library", "testtesttesttest", new Date());
        activityService.createActivity(newActivity2);

        Activity newActivity3 = new Activity("test3", new Date(), "library", "testtesttesttest", new Date());
        activityService.createActivity(newActivity3);

        List<Activity> activities = activityService.findAllActivity();
        for(Activity activity: activities){
            logger.info(activity.toString());
        }

        Activity activity = activityService.findActivityById(11);
        logger.info(activity.toString());


        Activity oldActivity = activityService.findActivityById(12);
        oldActivity.setActivityDescription("这是第二届CTF夺旗赛的描述!冲鸭!!!");
        activityService.updateActivity(oldActivity);*/

        activityService.deleteActivityById(10);

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
