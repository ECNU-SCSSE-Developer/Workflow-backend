package com.scsse.workflow.service;

import com.scsse.workflow.entity.Tag;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:14
 */
@Service
public interface TagService {

    Tag findTagById(Integer tagId);
    void createTag(Tag tag);
    void updateTag(Tag tag);
    void deleteTagById(Integer tagId);
}
