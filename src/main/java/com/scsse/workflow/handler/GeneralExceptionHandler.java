package com.scsse.workflow.handler;

import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultCode;
import com.scsse.workflow.util.Result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author Alfred Fu
 * Created on 2019/10/7 4:37 下午
 */
@RestControllerAdvice
public class GeneralExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    /**
     * 处理BindException(后端校验参数)
     *
     * @param e Exception
     * @return result
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handler(BindException e) {
        Result result = ResultUtil.error(ResultCode.CODE_400);
        result.setMsg(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        return result;
    }

    /**
     * 通用异常处理Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handleUnexpectedException(Exception e) {
        Result result = ResultUtil.error(ResultCode.CODE_500);
        result.setMsg(e.getMessage());
        e.printStackTrace();
        return result;
    }

    /**
     * 捕获主动抛出的异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(WrongUsageException.class)
    public Result handleMyException(Exception e) {
        Result result = ResultUtil.error(ResultCode.CODE_402);
        result.setMsg(e.getMessage());
//        e.printStackTrace();
        logger.error(e.getMessage());
        return result;
    }
}
