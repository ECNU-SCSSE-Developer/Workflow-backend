package com.scsse.workflow.controller;

import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.service.RecruitService;
import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
import com.scsse.workflow.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-14 15:49
 */
@RestController
public class RecruitController {

    private final UserUtil userUtil;

    private final RecruitService recruitService;

    @Autowired
    public RecruitController(UserUtil userUtil, RecruitService recruitService) {
        this.userUtil = userUtil;
        this.recruitService = recruitService;
    }


    /**
     * 分页返回指定时间前(防止新数据写入到数据库时出现bug)的应聘数据(每页10条)
     *
     * @param name            应聘名称(可选，供界面上的搜索按钮使用，若有就SQL再加个模糊查询)
     * @param recruitPosition 职位
     * @param time            指定时间点
     * @param offset          偏移量，从0开始
     * @return List{RecruitListDto}
     * 例:
     * url:
     * /recruit/all?recruitPosition=java_backend&&time=xxx&&offset=1  xxx时间点前第2页的职位为后端的应聘数据
     * /recruit/all?name=java?recruitPosition=java_backend&&time=xxx&&offset=0 xxx时间点前第1页的职位为后端的recruitName带java的应聘数据
     * @see RecruitDto 返回详细属性见此类
     * <p>
     * Response Json:
     * [{
     * organizer: {
     * userId: xxx,
     * username: 'xxx',
     * },
     * recruitName: 'xxx',
     * activityName: 'xxx'
     * ...
     * },{...},{...}]
     */
    @GetMapping("/recruit/all")
    public Result getRecruitList(@RequestParam String name,
                                 @RequestParam String recruitPosition,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss") Date time,
                                 @RequestParam Integer offset) {
        return ResultUtil.success();
    }

    /**
     * 某条招聘的具体信息
     *
     * @param id recruitId
     * @return RecruitDto
     * 例:
     * url:
     * /recruit/1
     * @see RecruitDto 返回详细属性见此类
     */
    @GetMapping("/recruit/{id}")
    public Result getRecruitDetail(@PathVariable() Integer id) {
        return ResultUtil.success();
    }

    /**
     * 获取<b>调用者</b>关注的所有应聘
     *
     * @return List{RecruitDto}
     * 例:
     * url:
     * /recruit/followed
     */
    @GetMapping("/recruit/followed")
    public Result getFollowedRecruit() {
        return ResultUtil.success();
    }

    /**
     * 获取<b>调用者</b>申请应聘的所有应聘
     *
     * @return RecruitDto
     * @see RecruitDto
     */
    @GetMapping("/recruit/applied")
    public Result getAppliedRecruit() {
        return ResultUtil.success();
    }


    /**
     * 创建一条应聘
     *
     * @param recruit 招聘
     * @return RecruitListDto
     * 例:
     * url:
     * /recruit
     * @see RecruitDto
     */
    @PostMapping("/recruit")
    public Result createOneRecruit(@RequestBody() Recruit recruit, @RequestAttribute() String openid) {
        return ResultUtil.success();
    }

}
