package com.scsse.workflow.repository;

import com.scsse.workflow.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:08
 */
public interface RecruitRepository extends JpaRepository<Recruit,Integer> {
}
