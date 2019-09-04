package com.scsse.workflow;

import com.scsse.workflow.entity.model.Vector;
import com.scsse.workflow.service.GraphService;
import com.scsse.workflow.service.VectorService;
import com.scsse.workflow.util.GraphOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alfred Fu
 * Created on 2019-02-21 22:24
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VectorServiceTest {

    private final static Logger logger = LoggerFactory.getLogger(VectorServiceTest.class);

    @Autowired
    private VectorService vectorService;

    @Autowired
    private GraphService graphService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GraphOperation graphOperation;


    @Test
    public void topologicalTest(){
        graphOperation.topologicalSort(graphService.findWithVectorsById(1))
                .stream()
                .map(Vector::getVectorId)
                .map(p->Integer.toString(p))
                .forEach(logger::info);
    }


    @Test
    public void CRUDTest(){
        Vector vector =  vectorService.findVectorById(1);
        Vector newVector = new Vector(null,"新描述",null,null,null);
        newVector.setVectorId(1);
        vectorService.updateVector(newVector);
    }

}
