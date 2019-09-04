package com.scsse.workflow.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 21:23
 */
@Getter
@Setter
@ToString(exclude = {"nextVectors","lastVectors"})
@Entity
@NoArgsConstructor
@Table(name = "vector")
public class Vector {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vectorId;
    @Column
    private String vectorName;
    @Column
    private String vectorDescription;
    @Column
    private Date vectorCreateTime = new Date();

    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User manager;

    @OneToOne
    @JsonBackReference(value = "vector.graph")
    @JoinColumn(name = "graph_id",unique = true)
    private Graph graph;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference(value = "vector.nextVectors")
    @JoinTable(name = "edge",
            joinColumns = @JoinColumn(name = "begin_vector_id"),
            inverseJoinColumns = @JoinColumn(name = "end_vector_id"))
    private Set<Vector> nextVectors;

    @ManyToMany(mappedBy = "nextVectors",fetch = FetchType.EAGER)
    @JsonBackReference(value = "vector.lastVectors")
    private Set<Vector> lastVectors;


    /**
     *
     * @param vectorName 名字
     * @param vectorDescription 描述
     * @param vectorCreateTime 创建时间
     * @param manager 管理员
     * @param graph 关联图
     */
    public Vector(String vectorName, String vectorDescription, Date vectorCreateTime, User manager, Graph graph) {
        this.vectorName = vectorName;
        this.vectorDescription = vectorDescription;
        this.vectorCreateTime = vectorCreateTime;
        this.manager = manager;
        this.graph = graph;
    }
}
