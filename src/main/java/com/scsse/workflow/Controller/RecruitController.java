package com.scsse.workflow.Controller;

import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-14 15:49
 */
@RestController
public class RecruitController {

    /**
     * 分页返回指定时间前(防止新数据写入到数据库时出现bug)的应聘数据(每页10条)
     * @param name 应聘名称(可选，供界面上的搜索按钮使用，若有就SQL再加个模糊查询)
     * @param recruitPosition 职位
     * @param time 指定时间点
     * @param offset 偏移量，从0开始
     * @return
     * 例:
     *  url:
     *      /recruit/all?recruitPosition=java_backend&&time=xxx&&offset=1  xxx时间点前第2页的职位为后端的应聘数据
     *      /recruit/all?name=java?recruitPosition=java_backend&&time=xxx&&offset=0 xxx时间点前第1页的职位为后端的recruitName带java的应聘数据
     *  属性:
     *      organizer:应聘发起人
     *      star:此人是否关注了该比赛
     *      recruitName:此条应聘名称
     *      ...
     *
     *  json:
     *      [{
     *          organizer: {
     *              userId: xxx,
     *              username: 'xxx',
     *          },
     *          follow：true,
     *          recruitName: 'xxx',
     *          recruitId: xxx,
     *          recruitWillingNumber: xxx,
     *          recruitRegisteredNumber: xxx,
     *          activityName: 'xxx'
     *      },{
     *          xxx
     *      },{
     *          xxx
     *      }]
     */
    @GetMapping("/recruit/all")
    public Result getRecruitList(@RequestParam() String name, @RequestParam() String recruitPosition, @RequestParam() Date time, @RequestParam() Integer offset){
        return ResultUtil.success();
    }

    /**
     * 某条招聘的具体信息
     * @param id recruitId
     * @return
     * 例:
     *  url:
     *      /recruit/1
     *
     *  属性:
     *      organizer:发起人
     *
     *  json:
     *      {
     *          organizer: {
     *              userId: xxx,
     *              username: 'xxx',
     *          },
     *          recruitName: 'xxx',
     *          recruitPosition: 'xxx',
     *          activityName: 'xxx',
     *          recruitWillingNumber: xxx,
     *          recruitDescription: 'xxx'
     *      }
     *
     */
    @GetMapping("/recruit/{id}")
    public Result getRecruitDetail(@PathVariable() Integer id){
        return ResultUtil.success();
    }

    /**
     * 获取调用者关注的所有应聘
     * @param openid 调用者的openid
     * @return
     * 例:
     *  url:
     *      /recruit/followed
     *
     *  Response Json:
     *      [{
     *          organizer: {
     *              userId: xxx,
     *              username: 'xxx',
     *          },
     *          follow：true,
     *          recruitName: 'xxx',
     *          recruitId: xxx,
     *          recruitWillingNumber: xxx,
     *          recruitRegisteredNumber: xxx,
     *          activityName: 'xxx'
     *      },{
     *          xxx
     *      },{
     *          xxx
     *      }]
     */
    @GetMapping("/recruit/followed")
    public Result getFollowedRecruit(@RequestAttribute() String openid){
        return ResultUtil.success();
    }

    /**
     * 获取调用者申请应聘的所有应聘
     * @param openid openid
     * @return Recruit
     */
    @GetMapping("/recruit/applied")
    public Result getAppliedRecruit(@RequestAttribute() String openid){
        return ResultUtil.success();
    }


    /**
     * 创建一条应聘
     * @param recruit 招聘
     * @return Recruit
     * 例:
     *  url:
     *      /recruit
     *
     *  属性:
     *      略
     *
     */
    @PostMapping("/recruit")
    public Result createOneRecruit(@RequestBody() Recruit recruit, @RequestAttribute() String openid){
        return ResultUtil.success();
    }

    /**
     * 关注一条应聘
     * @param recruitId 该条应聘的id
     * @param openid 调用者的openid
     * @return
     * 例:
     *  url:
     *      PUT /user/1/recruit/1
     */
    @PostMapping("/user/{openid}/recruit/{recruitId}")
    public Result followOneRecruit(@PathVariable() Integer recruitId,  @PathVariable String openid){
        return ResultUtil.success();
    }

    /**
     * 取消关注一条应聘
     * @param recruitId 应聘id
     * @param openid 调用者的openid
     * @return
     * 例:
     *  url:
     *      DELETE /user/1/recruit/1
     */
    @DeleteMapping("/user/{openid}/{recruitId}/unfollow")
    public Result unfollowOneRecruit(@PathVariable() Integer recruitId, @PathVariable String openid){
        return ResultUtil.success();
    }
}
