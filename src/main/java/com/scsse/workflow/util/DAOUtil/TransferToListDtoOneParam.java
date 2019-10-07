package com.scsse.workflow.util.DAOUtil;

import javafx.beans.binding.ObjectExpression;

/**
 * @author Alfred Fu
 * Created on 2019/10/6 11:17 下午
 */
public interface TransferToListDtoOneParam<T> {
    T transferToDto(Object eachItem);
}
