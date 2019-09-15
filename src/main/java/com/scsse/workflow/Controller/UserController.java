package com.scsse.workflow.Controller;

import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.web.bind.annotation.*;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-15 09:49
 */
@RestController
public class UserController {

    /**
     * 获取某个用户的具体信息
     * @param openid user primary key
     * @return User
     */
    @GetMapping("/user/{openid}")
    public Result getUserDetail(@PathVariable() String openid){
        return ResultUtil.success();
    }

    /**
     * 获取调用者关注的所有user
     * @param openid 调用者的openid
     * @return List{User}
     */
    @GetMapping("/user/follower")
    public Result getFollowedUser(@RequestAttribute() String openid){
        return ResultUtil.success();
    }

    /**
     * 编辑个人信息
     * @param user 需要更新的User信息
     * @param openid user唯一标示
     * @return 200 OK
     *
     * e.g.
     *      Put /user/1
     *
     * JsonBody
     *      {
     *          username: "test",
     *          userNumber: "10105101111"
     *      }
     *
     */
    @PutMapping("/user/{openid}")
    public Result editInformation(@RequestBody User user, @PathVariable String openid){
        return ResultUtil.success();
    }

    /**
     * 关注一个user
     * @param userId 调用者的openid
     * @param followerId 要关注的用户user的id(即openid)
     * @return
     * 例:
     *  url:
     *      PUT /user/1/follower/2
     */
    @PutMapping("/user/{userId}/follower/{followerId}")
    public Result followOneUser(@PathVariable String userId, @PathVariable String followerId){
        return ResultUtil.success();
    }

    /**
     * 取消关注一个user
     * @param userId 该user的id(即openid)
     * @param followerId 要关注的用户user的id(即openid)
     * @return
     * 例:
     *  url:
     *      DELETE /user/1/follower/2
     */
    @DeleteMapping("/user/{userId}/follower/{followerId}")
    public Result unfollowOneUser(@PathVariable String userId, @PathVariable String followerId){
        return ResultUtil.success();
    }
}
