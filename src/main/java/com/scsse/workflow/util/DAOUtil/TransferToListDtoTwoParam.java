package com.scsse.workflow.util.DAOUtil;

/**
 * using lambda to pass a function to another function
 *
 * @author Alfred Fu
 * Created on 2019/10/6 11:51 下午
 */
public interface TransferToListDtoTwoParam<T> {
    T transferToDto(Object firstParam, Object secondParam);
}
