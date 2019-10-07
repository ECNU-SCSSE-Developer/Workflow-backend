package com.scsse.workflow;

import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.model.Activity;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.service.ActivityService;
import com.scsse.workflow.service.RecruitService;
import com.scsse.workflow.service.TagService;
import com.scsse.workflow.util.DAOUtil.DtoTransferHelper;
import com.scsse.workflow.util.DAOUtil.UserUtil;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

/**
 * Those test cases are ALL HAPPY PATH
 * Just to make sure the methods can work
 * Alfred
 *
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
    RecruitService recruitService;

    @Autowired
    TagService tagService;

    @Autowired
    DtoTransferHelper dtoTransferHelper;

    @Mock
    UserUtil mockUserUtil;

    @Before()
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        doReturn(new User("username","")).when(
                mockUserUtil.getLoginUser()
        );
    }


    @Test
    public void simpleCreateAndFindTest(){
        Activity activity = new Activity("test_activity");
        activity = activityService.createActivity(activity);
        assertNotNull(activityService.findActivityById(activity.getActivityId()));
        assertTrue(activityService.findAllActivity().contains(dtoTransferHelper.transferToActivityDto(activity)));
        // update
        activity.setActivityName("test_activity2");
        activity = activityService.updateActivity(activity);
        assertEquals("test_activity2", activityService.findActivityById(activity.getActivityId()).getActivityName());
        // delete
        activityService.deleteActivityById(activity.getActivityId());
        assertNull(activityService.findActivityById(activity.getActivityId()));
    }


    @Test
    public void findAllRecruitOfActivityTest(){
        Activity activity = new Activity("test_activity");
        activity = activityService.createActivity(activity);

        RecruitDto recruit = recruitService.createRecruit(new Recruit("test_recruit",activity));
        RecruitDto recruit2 = recruitService.createRecruit(new Recruit("test_recruit2",activity));

        List<RecruitDto> result =  activityService.findAllRecruitOfActivity(activity.getActivityId());

        assertTrue(result.contains(recruit));
        assertTrue(result.contains(recruit2));
    }


    @Test
    public void bindTagToActivityTest() {
        Activity activity = new Activity("test_activity");
        activity = activityService.createActivity(activity);
        Tag tag = new Tag("test_tag","test_descr");
        Tag tag2 = new Tag("test_tag","test_descr");
        tag = tagService.createTag(tag);
        tag2 = tagService.createTag(tag2);
        // pre-condition
        assertNotNull(activityService.findActivityById(activity.getActivityId()));
        assertNotNull(tagService.findTagById(tag.getTagId()));
        // test binding
        activityService.bindTagToActivity(activity.getActivityId(), tag.getTagId());
        activityService.bindTagToActivity(activity.getActivityId(), tag2.getTagId());
        assertTrue(
                activityService.findActivityById(activity.getActivityId()).getActivityTags().contains(
                        tagService.findTagById(tag.getTagId())
                )
        );
        assertTrue(activityService.findActivityById(activity.getActivityId()).getActivityTags().contains(tagService.findTagById(tag2.getTagId())));
        //
        Set<Tag> tagOfActivity = activityService.findAllTagOfActivity(activity.getActivityId());
        assertTrue(tagOfActivity.contains(tag));
        assertTrue(tagOfActivity.contains(tag2));
        // test unbind
        activityService.unBindTagToActivity(activity.getActivityId(), tag.getTagId());
        assertFalse(
                activityService.findActivityById(activity.getActivityId()).getActivityTags().contains(
                        tagService.findTagById(tag.getTagId())
                )
        );
        tagOfActivity = activityService.findAllTagOfActivity(activity.getActivityId());
        assertFalse(tagOfActivity.contains(tag));
    }

}
