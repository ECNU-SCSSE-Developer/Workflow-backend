package com.scsse.workflow.util.Result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Alfred Fu
 * Created on 2019-03-07 20:51
 */
@Getter
@Setter
@ToString
public class Result<T> {

    private int code;//错误码

    private String msg;//提示信息

    private T data;//具体数据

    public Result() {

    }

    Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}