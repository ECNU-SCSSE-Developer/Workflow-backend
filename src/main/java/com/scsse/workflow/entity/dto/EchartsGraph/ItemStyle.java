package com.scsse.workflow.entity.dto.EchartsGraph;

import lombok.*;

/**
 * @author Alfred Fu
 * Created on 2019-03-17 15:56
 */

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemStyle {
    /**
     * 边框颜色
     */
    private String borderColor;

    /**
     * 填充颜色
     */
    private String color = "#fff";
}
