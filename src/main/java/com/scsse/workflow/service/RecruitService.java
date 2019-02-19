package com.scsse.workflow.service;

import com.scsse.workflow.entity.Recruit;
import org.springframework.stereotype.Component;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:17
 */
@Component
public interface RecruitService {

    Recruit findRecruitById(Integer recruitId);
    void createRecruit(Recruit recruit);
    void updateRecruit(Recruit recruit);
    void deleteRecruitById(Integer recruitId);

}
