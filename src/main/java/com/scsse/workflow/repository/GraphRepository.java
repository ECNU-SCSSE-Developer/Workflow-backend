package com.scsse.workflow.repository;


import com.scsse.workflow.entity.model.Graph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alfred Fu
 * Created on 2019-02-21 18:40
 */
public interface GraphRepository extends JpaRepository<Graph,Integer> {

    @EntityGraph(value = "Graph.vectors")
    Graph findWithVectorsByGraphId(Integer graphId);
    Graph findByGraphId(Integer graphId);
}
