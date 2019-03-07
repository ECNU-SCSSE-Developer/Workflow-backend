package com.scsse.workflow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 21:23
 */
@Getter
@Setter
@ToString
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
    @JoinColumn(name = "graph_id",unique = true)
    private Graph graph;

    @ManyToMany
    @JsonBackReference(value = "nextVectors")
    @JoinTable(name = "edge",
            joinColumns = @JoinColumn(name = "begin_vector_id"),
            inverseJoinColumns = @JoinColumn(name = "end_vector_id"))
    private List<Vector> nextVectors;

    @ManyToMany(mappedBy = "nextVectors")
    @JsonBackReference(value = "lastVectors")
    private List<Vector> lastVectors;


    public Vector(String vectorName, String vectorDescription, Date vectorCreateTime, User manager, Graph graph) {
        this.vectorName = vectorName;
        this.vectorDescription = vectorDescription;
        this.vectorCreateTime = vectorCreateTime;
        this.manager = manager;
        this.graph = graph;
    }
}
