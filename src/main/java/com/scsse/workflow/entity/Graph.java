package com.scsse.workflow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 21:23
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
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

    @JsonBackReference
    @ManyToMany(mappedBy = "graph")
    private List<Vector> vectors;


    public int size(){
        return vectors.size();
    }



    public Graph(String graphName, String activityName, User manager) {
        this.graphName = graphName;
        this.activityName = activityName;
        this.manager = manager;
    }
}
