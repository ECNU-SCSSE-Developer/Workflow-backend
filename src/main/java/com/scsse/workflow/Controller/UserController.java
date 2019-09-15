package com.scsse.workflow.Controller;

import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
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
}
