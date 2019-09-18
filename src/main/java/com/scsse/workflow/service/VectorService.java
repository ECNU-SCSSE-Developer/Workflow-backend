package com.scsse.workflow.service;

import com.scsse.workflow.entity.model.Vector;

/**
 * @author Alfred Fu
 * Created on 2019-02-21 18:39
 */

public interface VectorService {

    Vector findVectorById(Integer vectorId);

    Vector createVector(Vector vector);

    Vector updateVector(Vector vector);

    void deleteVector(Integer vectorId);


    /**
     * 给指定节点添加后继结点
     *
     * @param theVector 指定节点
     * @param successor 后继结点
     */
    void addSuccessor(Vector theVector, Vector successor);

    /**
     * 删除指定节点的后继结点
     *
     * @param theVector 指定结点
     * @param successor 待删除结点
     */
    void deleteSuccessor(Vector theVector, Vector successor);
}
