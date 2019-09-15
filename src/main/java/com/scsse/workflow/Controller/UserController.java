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
@RequestMapping("/user")
public class UserController {

    /**
     * 获取某个用户的具体信息
     * @param openid
     * @return
     */
    @GetMapping("/{openid}")
    public Result getUserDetail(@PathVariable() String openid){
        return ResultUtil.success();
    }

    /**
     * 获取调用者关注的所有user
     * @param openid 调用者的openid
     * @return
     */
    @GetMapping("/followed")
    public Result getFollowedUser(@RequestAttribute() String openid){
        return ResultUtil.success();
    }

    /**
     * 编辑个人信息
     * @param user
     * @return
     */
    @PutMapping("/edit")
    public Result editInformation(@RequestBody User user){
        return ResultUtil.success();
    }

    /**
     * 关注一个user
     * 因为是新建一条关注，所以用post
     * @param id 该user的id(即openid)
     * @param openid 调用者的openid
     * @return
     * 例:
     *  url:
     *      /user/1/follow
     */
    @PostMapping("/{id}/follow")
    public Result followOneUser(@PathVariable() Integer id, @RequestAttribute() String openid){
        return ResultUtil.success();
    }

    /**
     * 取消关注一个user
     * @param id 该user的id(即openid)
     * @param openid 调用者的openid
     * @return
     * 例:
     *  url:
     *      /user/1/unfollow
     */
    @DeleteMapping("/{id}/unfollow")
    public Result unfollowOneUser(@PathVariable() Integer id, @RequestAttribute() String openid){
        return ResultUtil.success();
    }
}
