package com.scsse.workflow;

import com.scsse.workflow.util.MVCUtil.PredicateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alfred Fu
 * Created on 2019/9/20 3:34 下午
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PredicateUtilTest {

    @Test
    public void transferTimeFromPSTToGMTTest() {
        assertEquals(PredicateUtil.transferTimeFromPSTToGMT("2019-09-20T13:20:00"),
                "2019-09-20 05:20:00"
        );
    }
}
