package com.scsse.workflow.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 21:23
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "graph")
public class Graph {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int graphId;
    @Column
    private String graphName;
    @Column
    private String activityName;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User manager;
}
