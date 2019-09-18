package com.scsse.workflow.repository;

import com.scsse.workflow.entity.model.Vector;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alfred Fu
 * Created on 2019-02-21 18:40
 */
public interface VectorRepository extends JpaRepository<Vector, Integer> {
    Vector findByVectorId(Integer vectorId);


}
