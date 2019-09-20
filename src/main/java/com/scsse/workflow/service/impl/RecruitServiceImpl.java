package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.RecruitRepository;
import com.scsse.workflow.repository.TagRepository;
import com.scsse.workflow.repository.UserRepository;
import com.scsse.workflow.service.RecruitService;
import com.scsse.workflow.util.PredicateUtil;
import com.scsse.workflow.util.RequestUtil;
import com.scsse.workflow.util.UserUtil;
import javafx.util.Pair;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
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

    private UserRepository userRepository;

    private UserUtil userUtil;

    @Autowired
    public RecruitServiceImpl(ModelMapper modelMapper, RecruitRepository recruitRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.recruitRepository = recruitRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<RecruitDto> findPaginationRecruit(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "creatTime");
        List<RecruitDto> result = new ArrayList<>();
        User currentUser = userRepository.findByOpenid(RequestUtil.getOpenId());
        recruitRepository.findAll(pageable).get().map(
                o -> transferRecruitToDto(o, currentUser)
        ).forEach(result::add);
        return result;
    }

    @Override
    public List<RecruitDto> findPaginationRecruitWithCriteria(Integer pageNum, Integer pageSize, HashMap<Integer, Pair<String, String>> queryParam) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "createTime");
        List<RecruitDto> result = new ArrayList<>();
        User currentUser = userRepository.findByOpenid(RequestUtil.getOpenId());
        recruitRepository.findAll((Specification<Recruit>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            PredicateUtil predicateHelper = new PredicateUtil<>(criteriaBuilder, root);
            queryParam.forEach(
                    (predicateType, KV) -> predicateList.add(predicateHelper.generatePredicate(predicateType, KV.getKey(), KV.getValue()))
            );
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        }, pageable).get().map(
                o -> transferRecruitToDto(o, currentUser)
        ).forEach(result::add);
        return result;
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
    public void addMember(Integer userId, Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        User user = userRepository.findByUserId(userId);
        if (recruit != null && user != null) {
            recruit.getMembers().add(user);
            recruitRepository.save(recruit);
        }
    }

    @Override
    public void removeMember(Integer userId, Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        User user = userRepository.findByUserId(userId);
        if (recruit != null && user != null) {
            recruit.getMembers().remove(user);
            recruitRepository.save(recruit);
        }
    }


    @Override
    public Set<User> findAllMemberOfRecruit(Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        return recruit.getMembers();
    }

    @Override
    public Set<User> findAllFollowerOfRecruit(Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        return recruit.getFollowers();
    }

    @Override
    public Set<User> findAllApplicantOfRecruit(Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        return recruit.getApplicants();
    }

    @Override
    public Set<Tag> findAllTagOfRecruit(Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        return recruit.getRecruitTags();
    }

    @Override
    public void bindTagToRecruit(Integer recruitId, Integer tagId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        Tag tag = tagRepository.findByTagId(tagId);
        if (recruit != null && tag != null && !recruit.getRecruitTags().contains(tag)) {
            recruit.getRecruitTags().add(tag);
            recruitRepository.save(recruit);
        }
    }

    @Override
    public void unBindTagToRecruit(Integer recruitId, Integer tagId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        Tag tag = tagRepository.findByTagId(tagId);
        if (recruit != null && tag != null) {
            recruit.getRecruitTags().remove(tag);
            recruitRepository.save(recruit);
        }
    }

    public List<RecruitDto> transferRecruitToListDto(Set<Recruit> recruitSet, User user) {
        List<RecruitDto> result = new ArrayList<>();
        recruitSet.stream().map(recruit -> transferRecruitToDto(recruit, user)).forEach(result::add);
        return result;
    }

    public RecruitDto transferRecruitToDto(Recruit recruit, User user) {
        RecruitDto result = modelMapper.map(recruit, RecruitDto.class);
        if (user.getApplyRecruits().contains(recruit)) {
            result.setApplied(true);
        }
        if (user.getFollowRecruits().contains(recruit)) {
            result.setFollowed(true);
        }
        if (user.getSuccessRecruits().contains(recruit)) {
            result.setAssigned(true);
        }
        result.setOrganizer(recruit.getManager());
        return result;
    }
}
