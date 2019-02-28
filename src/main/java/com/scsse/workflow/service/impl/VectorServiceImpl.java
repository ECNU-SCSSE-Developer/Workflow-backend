package com.scsse.workflow.service.impl;

import com.scsse.workflow.entity.Vector;
import com.scsse.workflow.repository.VectorRepository;
import com.scsse.workflow.service.VectorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alfred Fu
 * Created on 2019-02-21 18:39
 */

@Service
@Transactional
public class VectorServiceImpl implements VectorService {

    private final VectorRepository vectorRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public VectorServiceImpl(VectorRepository vectorRepository, ModelMapper modelMapper) {
        this.vectorRepository = vectorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vector findVectorById(Integer vectorId) {
        return vectorRepository.findByVectorId(vectorId);
    }

    @Override
    public Vector createVector(Vector vector) {
        return vectorRepository.save(vector);
    }

    @Override
    public Vector updateVector(Vector vector) {
        Vector oldOne = vectorRepository.findByVectorId(vector.getVectorId());
        modelMapper.map(vector,oldOne);
        return vectorRepository.save(oldOne);
    }

    @Override
    public void deleteVector(Integer vectorId) {
        vectorRepository.deleteById(vectorId);
    }

    public void addSuccessor(Vector theVector, Vector successor){
        if (theVector.getNextVectors()!=null) {
            theVector.getNextVectors().add(successor);
        }
        vectorRepository.save(theVector);

    }

    public void deleteSuccessor(Vector theVector, Vector successor){
        if (theVector.getNextVectors()!=null)
            theVector.getNextVectors().remove(successor);
        vectorRepository.save(theVector);
    }
}
