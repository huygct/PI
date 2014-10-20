package com.tma.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class Number. 
 * @author NghiTran
 * @version 1.0
 * @since 2014-10-07
 */
public class InputTest {

    Input number;
    
    public InputTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        // Create input
        number = new Input(100.0, 30.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    // Test Method get numberOfCalculation
    @Test
    public void testGetNumberOfCalculation() {
        assertEquals(100.0, number.getNumberOfCalculation(), 0.01);
    }
    
    // Test Method get threadBound
    @Test
    public void testGetNumberOfCalculationInAThread() {
        assertEquals(30, number.getNumberOfCalculationInAThread(), 0.001);
    }
}
