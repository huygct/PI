package com.tma.leibniz;

import static org.junit.Assert.*;

import com.tma.leibniz.TaskPi;
import org.junit.Before;
import org.junit.Test;

/**
*
 * @author  NghiTran
 * @version 1.0
 * @since 2014.12.26
 */

public class TestTaskPi {

    TaskPi taskPi;

    @Before
    public void setUp() throws Exception {
        // bound of thread is 0 to 100000.0
        taskPi = new TaskPi(0, 1000000.0);
    }

    // Test run
    @Test
    public void testRun() throws Exception  {
        taskPi.call();
        assertEquals(3.14, taskPi.getResult()*4, 0.01);
    }

}