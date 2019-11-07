package com.scsse.workflow.repository;

import com.scsse.workflow.entity.model.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:08
 */
public interface RecruitRepository extends JpaRepository<Recruit, Integer>, JpaSpecificationExecutor<Recruit> {
    Recruit findByRecruitId(Integer recruitId);

    void deleteByRecruitId(Integer recruitId);

    Set<Recruit> findAllByActivity_ActivityId(Integer activityId);

    Set<Recruit> findAllByManager_UserId(Integer userId);
}
