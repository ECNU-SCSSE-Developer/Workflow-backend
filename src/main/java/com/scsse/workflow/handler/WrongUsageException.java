package com.scsse.workflow.handler;

/**
 * @author Alfred Fu
 * Created on 2019/10/7 4:43 下午
 */
public class WrongUsageException extends Exception {
    public WrongUsageException(String message) {
        super(message);
    }
}
