package com.scsse.workflow.util;

import com.scsse.workflow.util.container.Pair;
import com.scsse.workflow.util.container.PairImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyPairTest {

    @Test
    void testPairInterface() {

        String TEST_KEY_1 = "testKey";
        String TEST_VALUE_1 = "testValue";
        Integer TEST_KEY_2 = 3;
        Integer TEST_VALUE_2 = 4;
        // test case 1 for string,string
        Pair<String, String> pair = new PairImpl<>();
        pair.setKey(TEST_KEY_1);
        pair.setValue(TEST_VALUE_1);
        Assertions.assertEquals(TEST_KEY_1, pair.getKey());
        Assertions.assertEquals(TEST_VALUE_1, pair.getValue());
        // test case 2 for integer,integer
        Pair<Integer, Integer> pair2 = new PairImpl<>();
        pair2.setKey(TEST_KEY_2);
        pair2.setValue(TEST_VALUE_2);
        Assertions.assertEquals(TEST_KEY_2, pair2.getKey());
        Assertions.assertEquals(TEST_VALUE_2, pair2.getValue());
        // test case 3 String Integer
        Pair<String, Integer> pair3 = new PairImpl<>();
        pair3.setKey(TEST_KEY_1);
        pair3.setValue(TEST_VALUE_2);
        Assertions.assertEquals(TEST_KEY_1, pair3.getKey());
        Assertions.assertEquals(TEST_VALUE_2, pair3.getValue());
    }

}
