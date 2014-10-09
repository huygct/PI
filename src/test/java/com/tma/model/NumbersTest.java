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
public class NumbersTest {

    Numbers number;
    
    public NumbersTest() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        // Create number
        number = new Numbers(100.0, 30.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    // Test Method get numberN
    @Test
    public void testGetNumberN() {
        assertEquals(100.0, number.getNumberN(), 0.01);
    }

    // Test Method set numberN
    @Test
    public void testSetNumberN() {
        number.setNumberN(10000.0);
        assertEquals(10000.0, number.getNumberN(), 0.001);
    }
    
    // Test Method get threadBound
    @Test
    public void testGetThreadBound() {
        assertEquals(30, number.getThreadBound(), 0.001);
    }
    
    // Test Method set threadBound
    @Test
    public void testSetThreadBound() {
        number.setThreadBound(50.0);
        assertEquals(50, number.getThreadBound(), 0.001);
    }
}
