package com.scsse.workflow.util.mvc;

import com.scsse.workflow.util.container.Pair;
import com.scsse.workflow.util.container.PairImpl;

import java.util.HashMap;

/**
 * @author Alfred Fu
 * Created on 2019/9/18 4:11 下午
 */
public class QueryParameterBuilder {

    private HashMap<Integer, Pair<String, String>> queryParameter = new HashMap<>();

    public QueryParameterBuilder addParameter(int predicateType, String key, String value) {
        if (value != null && !value.isEmpty()) {
            queryParameter.put(predicateType, new PairImpl<>(key, value));
        }
        return this;
    }

    public void resetParameter() {
        queryParameter = new HashMap<>();
    }

    public HashMap<Integer, Pair<String, String>> build() {
        return queryParameter;
    }
}
