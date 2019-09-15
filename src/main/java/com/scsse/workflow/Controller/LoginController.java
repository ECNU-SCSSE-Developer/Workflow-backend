package com.scsse.workflow.Controller;

import com.scsse.workflow.service.LoginService;
import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-15 08:56
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public Result onLogin(@RequestParam(name = "code") String code) {
        String openid = loginService.getWxSession(code);
        return ResultUtil.success(openid);
    }


}
