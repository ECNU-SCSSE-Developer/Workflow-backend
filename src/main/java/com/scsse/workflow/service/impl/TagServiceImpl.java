package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.model.Tag;
import com.scsse.workflow.repository.TagRepository;
import com.scsse.workflow.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alfred Fu
 * Created on 2019-02-19 20:19
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    private ModelMapper modelMapper;

    private TagRepository tagRepository;

    public TagServiceImpl(ModelMapper modelMapper, TagRepository tagRepository) {
        this.modelMapper = modelMapper;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findTagById(Integer tagId) {
        return tagRepository.findByTagId(tagId);
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Tag tag) {
        Integer tagId = tag.getTagId();
        Tag oldTag = tagRepository.findByTagId(tagId);
        modelMapper.map(tag, oldTag);
        return tagRepository.save(oldTag);
    }

    @Override
    public void deleteTagById(Integer tagId) {
        tagRepository.deleteByTagId(tagId);
    }
}
