package com.scsse.workflow.entity.dto.EchartsGraph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Alfred Fu
 * Created on 2019-03-17 15:58
 */

@Getter
@ToString
public enum Color {

    ORANGE("rgb(255, 190, 118)"),
    GREEN("'rgb(106, 176, 76)"),
    RED("rgb(252, 92, 101)"),
    ;

    private String rgbMode;

    Color(String rgbMode) {
        this.rgbMode = rgbMode;
    }
}

