package com.scsse.workflow.service;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-14 19:42
 */
public interface LoginService {
    String APP_ID = "wx0eb8aa287ec3013e";
    String APP_SECRET = "978a121b32983e84be1cb134b0c3659d";


    String getWxSession(String code);
}
