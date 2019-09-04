package com.scsse.workflow.entity.dto.EchartsGraph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Alfred Fu
 * Created on 2019-03-17 16:08
 */
@Getter
@ToString
public enum Symbol {
    ROUND_RECT("roundRect"),
    RECT("rect"),
    CIRCLE("circle"),
    ;

    private String shape;

    Symbol(String shape) {
        this.shape = shape;
    }
}
