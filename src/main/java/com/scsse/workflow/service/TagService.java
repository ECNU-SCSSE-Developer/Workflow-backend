package com.scsse.workflow.service;

import com.scsse.workflow.entity.Tag;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:14
 */
public interface TagService {

    List<Tag> findAllTag();
    Tag findTagById(Integer tagId);
    Tag createTag(Tag tag);
    Tag updateTag(Tag tag);
    void deleteTagById(Integer tagId);
}
