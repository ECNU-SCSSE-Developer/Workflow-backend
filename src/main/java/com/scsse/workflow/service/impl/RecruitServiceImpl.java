package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.Recruit;
import com.scsse.workflow.service.RecruitService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:18
 */
@Service
@Transactional
public class RecruitServiceImpl implements RecruitService {
    @Override
    public Recruit findRecruitById(Integer recruitId) {
        return null;
    }

    @Override
    public void createRecruit(Recruit recruit) {

    }

    @Override
    public void updateRecruit(Recruit recruit) {

    }

    @Override
    public void deleteRecruitById(Integer recruitId) {

    }
}
