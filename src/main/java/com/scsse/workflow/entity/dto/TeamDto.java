package com.scsse.workflow.entity.dto;

import com.scsse.workflow.entity.model.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alfred Fu
 * Created on 2019/10/6 6:55 下午
 */
@Getter
@Setter
@NoArgsConstructor
public class TeamDto extends Team {
    private String teamName;
}
