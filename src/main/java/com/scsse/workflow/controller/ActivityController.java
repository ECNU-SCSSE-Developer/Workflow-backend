package com.scsse.workflow.controller;

import com.scsse.workflow.service.ActivityService;
import com.scsse.workflow.util.result.Result;
import com.scsse.workflow.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alfred Fu
 * Created on 2019-03-07 20:48
 */

@RestController
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * 获取所有活动
     *
     * @param type {fresh,expire,finish}
     *             fresh：未到报名截止时间
     *             expire：超过报名截止时间，但活动尚未开始
     *             finish：活动已经结束
     * @return List{Activity}
     */
    @GetMapping("/activity/all")
    public Result findAllActivity(@RequestParam(name = "type", required = false, defaultValue = "normal") String type) {
        switch (type) {
            case "fresh":
                return ResultUtil.success(activityService.findAllFreshActivity());
            case "expire":
                return ResultUtil.success(activityService.findAllExpiredActivity());
            case "finish":
                return ResultUtil.success(activityService.findAllFinishedActivity());
            default:
                return ResultUtil.success(activityService.findAllActivity());
        }

    }

    /**
     * 获取某个活动的具体信息
     *
     * @param activityId 活动Id
     * @return Activity
     * <p>
     * e.g.
     * GET /activity/1
     */
    @GetMapping("/activity/{activityId}")
    public Result getActivityDetail(@PathVariable Integer activityId) {
        return ResultUtil.success(activityService.findActivityById(activityId));
    }


}
