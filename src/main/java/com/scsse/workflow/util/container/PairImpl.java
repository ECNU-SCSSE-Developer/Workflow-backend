package com.scsse.workflow.util.container;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PairImpl<K, V> implements Pair<K, V> {

    private K internalKey;

    private V internalValue;

    public PairImpl(K internalKey, V internalValue) {
        this.internalKey = internalKey;
        this.internalValue = internalValue;
    }

    @Override
    public K getKey() {
        return internalKey;
    }

    @Override
    public void setKey(K key) {
        internalKey = key;
    }

    @Override
    public V getValue() {
        return internalValue;
    }

    @Override
    public void setValue(V value) {
        internalValue = value;
    }
}
