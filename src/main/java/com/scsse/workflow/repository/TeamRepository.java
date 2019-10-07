package com.scsse.workflow.repository;

import com.scsse.workflow.entity.model.Team;
import com.scsse.workflow.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019/10/6 7:55 下午
 */
public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Team> findAllByMembersContains(User user);
}
