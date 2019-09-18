package com.scsse.workflow.repository;

import com.scsse.workflow.entity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:08
 */
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    Activity findByActivityId(Integer activityId);

}
