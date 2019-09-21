package com.scsse.workflow.controller;

import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.service.UserService;
import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
import com.scsse.workflow.util.UserUtil;
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
     * @param openid user primary key
     * @return User
     */
    @GetMapping("/user/{openid}")
    public Result getUserDetail(@PathVariable() String openid) {
        return ResultUtil.success(userService.findUserById(userUtil.findUserIdByOpenid(openid)));
    }

    /**
     * 编辑个人信息
     *
     * @param user   需要更新的User信息
     * @param openid user唯一标示
     * @return 200 OK
     * <p>
     * e.g.
     * Put /user/1
     * <p>
     * JsonBody
     * {
     * username: "test",
     * userNumber: "10105101111"
     * }
     */
    @PutMapping("/user/{openid}")
    public Result updateUserInformation(@RequestBody User user, @PathVariable String openid) {
        user.setUserId(userUtil.findLoginUserId());
        return ResultUtil.success(
                userService.updateUser(user)
        );
    }

    /**
     * 获取调用者关注的所有user
     *
     * @param openid 调用者的openid
     * @return List{User}
     */
    @GetMapping("/user/Follower")
    public Result getFollowedUser(@RequestAttribute() String openid) {
        return ResultUtil.success(

        );
    }

    /**
     * 关注一个user
     *
     * @param userId     调用者的openid
     * @param followerId 要关注的用户user的id(即openid)
     * @return 例:
     * url:
     * PUT /user/1/follower/2
     */
    @PutMapping("/user/{userId}/follower/{followerId}")
    public Result followOneUser(@PathVariable String userId, @PathVariable String followerId) {
        Integer _originUserId = userUtil.findLoginUserId();
        Integer _followerUserId = userUtil.findUserIdByOpenid(followerId);
        return ResultUtil.success();
    }

    /**
     * 取消关注一个user
     *
     * @param userId     该user的id(即openid)
     * @param followerId 要关注的用户user的id(即openid)
     * @return 例:
     * url:
     * DELETE /user/1/follower/2
     */
    @DeleteMapping("/user/{userId}/follower/{followerId}")
    public Result unfollowOneUser(@PathVariable String userId, @PathVariable String followerId) {
        Integer _originUserId = userUtil.findLoginUserId();
        Integer _followerUserId = userUtil.findUserIdByOpenid(followerId);
        return ResultUtil.success();
    }


    /**
     * 关注一条应聘
     *
     * @param recruitId 该条应聘的id
     * @param openid    调用者的openid
     * @return 例:
     * url:
     * PUT /user/1/recruit/1
     */
    @PutMapping("/user/{openid}/recruit/{recruitId}")
    public Result followOneRecruit(@PathVariable() Integer recruitId, @PathVariable String openid) {
        userService.followRecruit(userUtil.findLoginUserId(), recruitId);
        return ResultUtil.success();
    }

    /**
     * 取消关注一条应聘
     *
     * @param recruitId 应聘id
     * @param openid    调用者的openid
     * @return 例:
     * url:
     * DELETE /user/1/recruit/1
     */
    @DeleteMapping("/user/{openid}/{recruitId}/unfollow")
    public Result unfollowOneRecruit(@PathVariable() Integer recruitId, @PathVariable String openid) {
        userService.unfollowRecruit(userUtil.findLoginUserId(), recruitId);
        return ResultUtil.success();
    }
}
