package com.scsse.workflow;

import com.scsse.workflow.entity.Activity;
import com.scsse.workflow.service.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test() {
        List<Activity> activities = activityService.findAllActivity();
        for(Activity activity: activities){
            logger.info(activity.toString());
        }

        Activity activity = activityService.findActivityById(2);
        logger.info(activity.toString());

        Activity newActivity = new Activity("test1", new Date(), "library", "testtesttesttest", new Date());
        activityService.createActivity(newActivity);

        Activity oldActivity = activityService.findActivityById(5);
        oldActivity.setActivityDescription("这是第二届CTF夺旗赛的描述!冲鸭!!!");
        activityService.updateActivity(oldActivity);

        activityService.deleteActivityById(11);
    }

}
