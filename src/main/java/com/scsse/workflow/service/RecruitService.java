package com.scsse.workflow.service;

import com.scsse.workflow.entity.Recruit;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:17
 */
@Service
public interface RecruitService {

    Recruit findRecruitById(Integer recruitId);
    void createRecruit(Recruit recruit);
    void updateRecruit(Recruit recruit);
    void deleteRecruitById(Integer recruitId);

}
