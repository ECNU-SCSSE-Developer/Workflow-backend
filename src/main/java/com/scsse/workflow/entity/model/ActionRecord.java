package com.scsse.workflow.entity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 记录一个组内的计划
 *
 * @author Alfred Fu
 * Created on 2019/9/25 10:56 上午
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class ActionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer actTimeRecordId;

    @Column(length = 800)
    private String content;

    @Column
    private Date createTime = new Date();

    @ManyToOne
    @JoinColumn(name = "userId")
    private User triggerUser;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;


    /**
     * 根据Task生成ActionRecord {完成}
     *
     * @param user triggerUser
     * @param team 保存ActionRecord的Team
     * @param task 状态为完成的task
     * @return ActionRecord
     */
    public static ActionRecord generateTaskFinishedRecord(User user, Team team, Vector task) {
        final String SPLITTER = " ";
        final String SUCCESS_STATUS = "成功";

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setTriggerUser(user);
        actionRecord.setTeam(team);
        actionRecord.setContent(
                new SimpleDateFormat("hh:mm").format(new Date()) + SPLITTER +
                        user.getUsername() + SPLITTER +
                        task.getVectorName() + SPLITTER +
                        SUCCESS_STATUS
        );

        return actionRecord;
    }

}
