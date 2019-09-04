package com.scsse.workflow.entity.dto.EchartsGraph;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-03-17 16:06
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    private String name;
    private String symbol;
    private String description;
    private ItemStyle itemStyle;
    private List<Node> children = new ArrayList<>();
}
