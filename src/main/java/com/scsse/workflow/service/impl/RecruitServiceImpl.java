package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.dto.UserDto;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.entity.model.User;
import com.scsse.workflow.repository.RecruitRepository;
import com.scsse.workflow.repository.TagRepository;
import com.scsse.workflow.repository.UserRepository;
import com.scsse.workflow.service.RecruitService;
import com.scsse.workflow.util.DAOUtil.DtoTransferHelper;
import com.scsse.workflow.util.MVCUtil.PredicateUtil;
import com.scsse.workflow.util.MVCUtil.RequestUtil;
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

    private final ModelMapper modelMapper;

    private final DtoTransferHelper dtoTransferHelper;

    private final RecruitRepository recruitRepository;

    private final TagRepository tagRepository;

    private final UserRepository userRepository;

    @Autowired
    public RecruitServiceImpl(ModelMapper modelMapper, DtoTransferHelper dtoTransferHelper, RecruitRepository recruitRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.dtoTransferHelper = dtoTransferHelper;
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
                o -> dtoTransferHelper.transferToRecruitDto(o, currentUser)
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
                o -> dtoTransferHelper.transferToRecruitDto(o, currentUser)
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
    public List<UserDto> findAllMemberOfRecruit(Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        return dtoTransferHelper.transferToListDto(recruit.getMembers(),eachItem -> dtoTransferHelper.transferToUserDto((User) eachItem));
    }

    @Override
    public List<UserDto> findAllFollowerOfRecruit(Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        return dtoTransferHelper.transferToListDto(recruit.getFollowers(),eachItem -> dtoTransferHelper.transferToUserDto((User) eachItem));
    }

    @Override
    public List<UserDto> findAllApplicantOfRecruit(Integer recruitId) {
        Recruit recruit = recruitRepository.findByRecruitId(recruitId);
        return dtoTransferHelper.transferToListDto(recruit.getApplicants(),eachItem -> dtoTransferHelper.transferToUserDto((User) eachItem));


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

}
