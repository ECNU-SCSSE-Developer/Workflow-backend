package com.scsse.workflow.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.UserRepository;
import com.scsse.workflow.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-15 08:40
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 调取微信服务器接口,获取openid,为方便起见直接返回前端openid，不整第三方key了
     */
    @Override
    public String getWxSession(String code) {

        RestTemplate restTemplate = new RestTemplate();

        //覆盖RestTemplate默认的异常处理方法
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) {
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) {

            }
        };
        restTemplate.setErrorHandler(responseErrorHandler);

        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("appid", APP_ID);//小程序的appId
        body.add("secret", APP_SECRET);//小程序的secret
        body.add("js_code", code);

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HttpHeaders headers = new HttpHeaders();//这个对象有add()方法，可往请求头存入信息
        HttpEntity<LinkedMultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> res;
        res = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);//返回的结果信息,String.class是可以修改的，取决于怎么解析请求返回的参数

        //logger.info("res statusCode:" + res.getStatusCode());
        if (Integer.parseInt(String.valueOf(res.getStatusCode())) == 200) {
            //logger.info("res body:" + res.getBody());
            JSONObject jsonObject = JSONObject.parseObject(res.getBody());
            String sessionKey = jsonObject.getString("session_key");
            String openid = jsonObject.getString("openid");

            if (!userRepository.existsDistinctByOpenid(openid)) {
                userRepository.save(new User(openid));
            }

            return openid;
        } else {
            return null;
        }
    }
}
