package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.Recruit;
import com.scsse.workflow.repository.RecruitRepository;
import com.scsse.workflow.service.RecruitService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:18
 */
@Service
@Transactional
public class RecruitServiceImpl implements RecruitService {

    private ModelMapper modelMapper;

    private RecruitRepository recruitRepository;

    @Autowired
    public RecruitServiceImpl(ModelMapper modelMapper, RecruitRepository recruitRepository){
        this.modelMapper = modelMapper;
        this.recruitRepository = recruitRepository;
    }

    @Override
    public List<Recruit> findAllRecruit() {
        return recruitRepository.findAll();
    }

    @Override
    public Recruit findRecruitById(Integer recruitId) {
        return recruitRepository.findByRecruitId(recruitId);
    }

    @Override
    public Recruit createRecruit(Recruit recruit) {
        return recruitRepository.save(recruit);
    }

    @Override
    public Recruit updateRecruit(Recruit recruit) {
        Integer recruitId = recruit.getRecruitId();
        Recruit oldRecruit = recruitRepository.findByRecruitId(recruitId);
        modelMapper.map(recruit, oldRecruit);
        return recruitRepository.save(oldRecruit);
    }

    @Override
    public void deleteRecruitById(Integer recruitId) {
        recruitRepository.deleteByRecruitId(recruitId);
    }
}
