package com.scsse.workflow.service;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-14 19:42
 */
public interface LoginService {
    String APP_ID = "wx0a0ebdd345e4d552";
    String APP_SECRET = "470d2a118a04cbafe063bdf8403054cd";


    String getWxSession(String code);
}
