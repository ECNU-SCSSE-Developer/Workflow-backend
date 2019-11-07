package com.scsse.workflow.util;

import com.scsse.workflow.util.mvc.PredicateUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alfred Fu
 * Created on 2019/9/20 3:34 下午
 */
class PredicateUtilTest {

    @Test
    void transferTimeFromPSTToGMTTest() {
        assertEquals(
            "2019-09-20 05:20:00",
            PredicateUtil.transferTimeFromPSTToGMT("2019/09/20 13:20:00")
        );
    }
}
