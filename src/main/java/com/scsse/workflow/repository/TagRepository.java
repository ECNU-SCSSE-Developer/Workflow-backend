package com.scsse.workflow.repository;

import com.scsse.workflow.entity.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:07
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByTagId(Integer tagId);

    void deleteByTagId(Integer tagId);


}
