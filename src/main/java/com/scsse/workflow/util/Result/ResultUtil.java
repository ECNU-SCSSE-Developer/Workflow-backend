package com.scsse.workflow.util.Result;

/**
 * Result的工具类,用于封装Result
 * @author Andrew Dong
 * @date 2018/10/19 15:15
 */
public class ResultUtil {


    /**
     * 请求数据成功时
     * @param object data
     * @return ResultUtil
     */
    @SuppressWarnings("unchecked")
    public static Result success(Object object){
        Result result = new Result(ResultCode.CODE_200);
        result.setData(object);
        return result;
    }

    /**
     * @return ResultUtil
     */
    public static Result success(){
        return success(null);
    }


    @SuppressWarnings("unchecked")
    public static Result error(ResultCode resultCode){
        Result result = new Result(resultCode);
        result.setData(null);
        return result;
    }
}
