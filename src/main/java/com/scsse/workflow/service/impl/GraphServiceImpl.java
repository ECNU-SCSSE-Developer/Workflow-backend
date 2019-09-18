package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.model.Graph;
import com.scsse.workflow.repository.GraphRepository;
import com.scsse.workflow.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alfred Fu
 * Created on 2019-02-21 18:39
 */


@Service
@Transactional
public class GraphServiceImpl implements GraphService {

    private final GraphRepository graphRepository;

    @Autowired
    public GraphServiceImpl(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }


    @Override
    public Graph findSimpleGraphById(Integer graphId) {
        return graphRepository.findByGraphId(graphId);
    }

    @Override
    public Graph findWithVectorsById(Integer graphId) {
        return graphRepository.findWithVectorsByGraphId(graphId);
    }
}
