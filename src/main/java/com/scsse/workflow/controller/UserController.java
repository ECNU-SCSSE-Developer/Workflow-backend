package com.scsse.workflow.controller;

import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.service.UserService;
import com.scsse.workflow.util.DAOUtil.UserUtil;
import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-15 09:49
 */
@RestController
public class UserController {

    private final UserUtil userUtil;

    private final UserService userService;

    @Autowired
    public UserController(UserUtil userUtil, UserService userService) {
        this.userUtil = userUtil;
        this.userService = userService;
    }

    /**
     * 获取某个用户的具体信息
     *
     * @param userId user primary key
     * @return User
     */
    @GetMapping("/user/{userId}")
    public Result getUserDetail(@PathVariable Integer userId) {
        return ResultUtil.success(userService.findUserDetail(userId));
    }

    /**
     * 编辑自己的个人信息
     *
     * @param user 需要更新的User信息
     * @return 200 OK
     * e.g.
     * Put /user/self
     * Request Body
     * {
     * username: "test",
     * userNumber: "10105101111"
     * }
     */
    @PutMapping("/user/self")
    public Result updateUserInformation(@RequestBody User user) {
        user.setUserId(userUtil.findLoginUserId());
        return ResultUtil.success(
                userService.updateUser(user)
        );
    }

    /**
     * 获取用户关注的所有user
     *
     * @param userId 用户主键
     * @return List{User}
     */
    @GetMapping("/user/{userId}/followingUser")
    public Result getFollowingUser(@PathVariable Integer userId) {
        return ResultUtil.success(
                userService.findAllFollowingUser(userId)
        );
    }

    /**
     * 获取用户的粉丝
     *
     * @param userId 用户主键
     * @return List{User}
     */
    @GetMapping("/user/{userId}/followedUser")
    public Result getFollowedUser(@PathVariable Integer userId) {
        return ResultUtil.success(
                userService.findAllFollowedUser(userId)
        );
    }

    /**
     * 获取用户的同事 (同个招聘的人)
     *
     * @param userId 用户主键
     * @return List{User}
     */
    @GetMapping("/user/{userId}/colleague")
    public Result getColleague(@PathVariable Integer userId) {
        return ResultUtil.success(
                userService.findAllColleague(userId)
        );
    }

    /**
     * 获取用户关注的所有应聘
     *
     * @return List{RecruitDto}
     * 例:
     * url:
     * GET /user/1/followedRecruit
     */
    @GetMapping("/user/{userId}/followedRecruit")
    public Result getFollowedRecruit(@PathVariable Integer userId) {
        return ResultUtil.success(
                userService.findAllFollowedRecruit(userId)
        );
    }


    /**
     * 获取用户关注的所有比赛
     *
     * @param userId 调用者的openid
     * @return List{Activity}
     * <p>
     * e.g.
     * GET /user/1/followedActivity
     */
    @GetMapping("/user/{userId}/followedActivity")
    public Result getFollowedActivity(@PathVariable() Integer userId) {
        return ResultUtil.success(
                userService.findAllFollowedActivity(userId)
        );
    }




    /**
     * 关注一个user
     *
     * @param followedUserId 要关注的用户的userId
     * @return 例:
     * url:
     * PUT /user/follower/2
     */
    @PutMapping("/user/follower/{followedUserId}")
    public Result followUser(@PathVariable Integer followedUserId) {
        Integer originUserId = userUtil.findLoginUserId();
        userService.followUser(originUserId, followedUserId);
        return ResultUtil.success();
    }

    /**
     * 取消关注一个user
     *
     * @param followedUserId 要关注的用户的userId
     * @return 例:
     * url:
     * DELETE /user/follower/2
     */
    @DeleteMapping("/user/follower/{followedUserId}")
    public Result unfollowUser(@PathVariable Integer followedUserId) {
        Integer originUserId = userUtil.findLoginUserId();
        userService.unfollowRecruit(originUserId, followedUserId);
        return ResultUtil.success();
    }

    /**
     * 关注一条应聘
     *
     * @param recruitId 该条应聘的id
     * @return 例:
     * url:
     * PUT /user/recruit/1
     */
    @PutMapping("/user/recruit/{recruitId}")
    public Result followRecruit(@PathVariable() Integer recruitId) {
        userService.followRecruit(userUtil.findLoginUserId(), recruitId);
        return ResultUtil.success();
    }

    /**
     * 取消关注一条应聘
     *
     * @param recruitId 应聘id
     * @return 例:
     * url:
     * DELETE /user/recruit/1
     */
    @DeleteMapping("/user/recruit/{recruitId}")
    public Result unfollowRecruit(@PathVariable() Integer recruitId) {
        userService.unfollowRecruit(userUtil.findLoginUserId(), recruitId);
        return ResultUtil.success();
    }

    /**
     * 关注一个活动
     *
     * @param activityId 活动id
     * @return 例:
     * url:
     * PUT /user/recruit/1
     */
    @PutMapping("/user/activity/{activityId}")
    public Result followActivity(@PathVariable() Integer activityId) {
        userService.followActivity(userUtil.findLoginUserId(), activityId);
        return ResultUtil.success();
    }

    /**
     * 取消关注一个活动
     *
     * @param activityId 活动id
     * @return 例:
     * url:
     * DELETE /user/recruit/1
     */
    @DeleteMapping("/user/activity/{activityId}")
    public Result unfollowActivity(@PathVariable() Integer activityId) {
        userService.unfollowActivity(userUtil.findLoginUserId(), activityId);
        return ResultUtil.success();
    }
}
