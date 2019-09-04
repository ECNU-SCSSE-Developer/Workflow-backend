package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.RecruitRepository;
import com.scsse.workflow.repository.TagRepository;
import com.scsse.workflow.service.RecruitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:18
 */
@Service
@Transactional
public class RecruitServiceImpl implements RecruitService {

    private ModelMapper modelMapper;

    private RecruitRepository recruitRepository;

    private TagRepository tagRepository;

    @Autowired
    public RecruitServiceImpl(ModelMapper modelMapper, RecruitRepository recruitRepository, TagRepository tagRepository){
        this.modelMapper = modelMapper;
        this.recruitRepository = recruitRepository;
        this.tagRepository = tagRepository;
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

    @Override
    public void bindApplicantToRecruit(Integer userId, Integer recruitId) {

    }

    @Override
    public void unBindApplicantToRecruit(Integer userId, Integer recruitId) {

    }

    @Override
    public Set<User> findAllMemberOfRecruit(Integer recruitId) {
        return null;
    }

    @Override
    public Set<User> findAllFollowerOfRecruit(Integer recruitId) {
        return null;
    }

    @Override
    public Set<User> findAllApplicantOfRecruit(Integer recruitId) {
        return null;
    }

    @Override
    public Set<Tag> findAllTagOfRecruit(Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        return recruit.getRecruitTags();
    }

    @Override
    public void bindTagToRecruit(Integer recruitId, Integer tagId) {
        //TODO:(插入前要先确保关联表里面没有该条关联数据)
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        Tag tag = tagRepository.findByTagId(tagId);
        if(recruit != null && tag != null){
            recruit.getRecruitTags().add(tag);
            recruitRepository.save(recruit);
        }
    }

    @Override
    public void unBindTagToRecruit(Integer recruitId, Integer tagId) {

    }
}
