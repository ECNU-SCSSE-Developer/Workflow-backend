package com.scsse.workflow.util;

import javafx.util.Builder;
import javafx.util.Pair;

import java.util.HashMap;

/**
 * @author Alfred Fu
 * Created on 2019/9/18 4:11 下午
 */
public class QueryParameterBuilder implements Builder {

    private HashMap<Integer, Pair<String,String>> queryParameter = new HashMap<>();

    public QueryParameterBuilder addParameter(int predicateType,String key, String value){
        if (value != null && !value.isEmpty()) {
            queryParameter.put(predicateType, new Pair<>(key, value));
        }
        return this;
    }

    public void resetParameter(){
        queryParameter = new HashMap<>();
    }

    @Override
    public HashMap<Integer,Pair<String,String>> build() {
        return queryParameter;
    }
}
