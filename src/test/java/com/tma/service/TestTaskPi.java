package com.tma.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
*
* @author Nghitran a test task pi
* @version 1.0
* @since 2014-10-09
*
*/
public class TestTaskPi {

    TaskPi taskPi;

    @Before
    public void setUp() throws Exception {
        taskPi = new TaskPi(0, 1000000.0);
    }

    // Test run
    @Test
    public void testRun() throws Exception  {
        double result = taskPi.call();
        assertEquals(3.14, taskPi.getResult()*4, 0.01);
    }

}
