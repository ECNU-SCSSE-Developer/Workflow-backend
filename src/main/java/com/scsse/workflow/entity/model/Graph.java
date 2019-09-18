package com.scsse.workflow.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 21:23
 */
@Getter
@Setter
@ToString(exclude = {"vectors"})
@Entity
@NoArgsConstructor
@Table(name = "graph")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "Graph.vectors", attributeNodes = @NamedAttributeNode(value = "vectors")),})
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
    @JsonBackReference(value = "graph.manager")
    @JoinColumn(name = "user_id", unique = true)
    private User manager;

    @JsonBackReference(value = "graph.vectors")
    @OneToMany(mappedBy = "graph")
    private Set<Vector> vectors = new HashSet<>();


    public Graph(String graphName, String activityName, User manager) {
        this.graphName = graphName;
        this.activityName = activityName;
        this.manager = manager;
    }

    public int size() {
        return vectors.size();
    }
}
