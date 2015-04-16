package com.tma.leibniz;

import static org.junit.Assert.*;

import com.tma.leibniz.InputImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author  NghiTran
 * @version 1.0
 * @since 2014.12.26
 */
public class InputImplTest {

    InputImpl number;
    
    public InputImplTest() {
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
        number = new InputImpl(100000000.0, 30000.0);
    }

    @After
    public void tearDown() throws Exception {
    }

    // Test Method get numberOfCalculation
    @Test
    public void testGetNumberOfCalculation() {
        assertEquals(100000000.0, number.getNumberOfCalculation(), 0.01);
    }
    
    // Test Method get threadBound
    @Test
    public void testGetNumberOfCalculationInAThread() {
        assertEquals(30000.0, number.getNumberOfCalculationInAThread(), 0.001);
    }
}
