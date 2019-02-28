package com.scsse.workflow.repository;

import com.scsse.workflow.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:07
 */
public interface TagRepository extends JpaRepository<Tag,Integer> {
    Tag findByTagId(Integer tagId);
    void deleteByTagId(Integer tagId);



}
