package com.scsse.workflow;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class WorkflowApplicationTests {

}

