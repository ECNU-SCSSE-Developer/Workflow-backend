package com.scsse.workflow.util.MyPair;

import java.io.Serializable;

public interface Pair<K, V> extends Serializable {
    K getKey();

    void setKey(K key);

    V getValue();

    void setValue(V value);
}
