package com.scsse.workflow.service;

import com.scsse.workflow.entity.Recruit;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:17
 */
@Service
public interface RecruitService {

    List<Recruit> findAllRecruit();
    Recruit findRecruitById(Integer recruitId);
    Recruit createRecruit(Recruit recruit);
    Recruit updateRecruit(Recruit recruit);
    void deleteRecruitById(Integer recruitId);

}
