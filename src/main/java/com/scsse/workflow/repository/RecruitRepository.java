package com.scsse.workflow.repository;

import com.scsse.workflow.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:08
 */
public interface RecruitRepository extends JpaRepository<Recruit,Integer> {
    Recruit findByRecruitId(Integer recruitId);
    void deleteByRecruitId(Integer recruitId);

    Set<Recruit> findAllByActivity_ActivityId(Integer activityId);
}
