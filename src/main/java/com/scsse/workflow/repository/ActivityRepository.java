package com.scsse.workflow.repository;

import com.scsse.workflow.entity.Activity;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:08
 */
public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    Activity findByActivityId(Integer activityId);
    List<Activity> findAllActivity();
}
