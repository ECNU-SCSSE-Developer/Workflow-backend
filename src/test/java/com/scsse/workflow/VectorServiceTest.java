package com.scsse.workflow;

import com.scsse.workflow.service.VectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final VectorService vectorService;

    private final ModelMapper modelMapper;

    public VectorServiceTest(VectorService vectorService, ModelMapper modelMapper) {
        this.vectorService = vectorService;
        this.modelMapper = modelMapper;
    }

    @Test
    public void contextLoadTest(){
        
    }

}
