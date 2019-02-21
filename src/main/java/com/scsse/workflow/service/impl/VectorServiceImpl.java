package com.scsse.workflow.service.impl;

import com.scsse.workflow.service.VectorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alfred Fu
 * Created on 2019-02-21 18:39
 */

@Service
@Transactional
public class VectorServiceImpl implements VectorService {
    @Override
    public int test() {
        return 1;
    }
}
