package com.scsse.workflow.util.Result;

/**
 * @author Alfred Fu
 * Created on 2019-03-07 20:52
 */
public enum ResultCode {
    UNKNOW_ERROR(-1, "未知错误"),
    CODE_200(200, "访问成功"),
    CODE_400(400, "用户名或密码错误!"),
    CODE_401(401, "未携带Token或Token无效!"),
    CODE_402(402, "Method使用错误，请查看API"),
    CODE_403(403, "没有权限访问!"),
    CODE_404(404, "找不到访问地址"),
    CODE_500(500, "系统内部错误"),
    CODE_501(501, "对象已存在，请检测唯一性"),
    CODE_502(502, "当前版本与服务器版本不一致,请更新应用"),
    CODE_503(503, "对象不存在，请检测请求参数"),
    CODE_504(504, "服务器连接错误，请稍后重试"),
    CODE_505(505, "key校验失败"),
    CODE_506(506, "RPC服务连接失败，请稍后重试")
    ;

    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

