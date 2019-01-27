package com.scsse.workflow.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alfred Fu
 * Created on 2019-01-27 23:08
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_recruit")
public class UserRecruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer user_recruit_id;
    @Column
    private Boolean recruitFollower;
    @Column
    private Boolean recruitApplicant;
    @Column
    private Integer userId;
    @Column
    private Integer recruitId;
}
