package com.tma.leibniz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class FormulaPITest {

    FormulaPI formulaPi;
    InputImpl input;
    
    // Create FormulaPI and Number class used mock
    @Before
    public void setUp() throws Exception {
        formulaPi = new FormulaPI();
        input = mock(InputImpl.class);
        formulaPi.setInput(input);
        when(input.getNumberOfCalculation()).thenReturn(100000.0);
        when(input.getNumberOfCalculationInAThread()).thenReturn(30000.0);
    }

    // Test constructor FormulaPI
    @Test
    public void testFormulaPI() {
        assertEquals(0L, formulaPi.getResult(), 0.001);
    }

    //Test get Result
    @Test
    public void testGetResult() {
        assertEquals(0L, formulaPi.getResult(), 0.001);
    }
    
    // Test get number thread finish
    @Test
    public void testgetNumberThreadFinish() {
        formulaPi.calculate();
        assertEquals(4, formulaPi.getNumberFinish());
    }
    
    // Test calculate
    @Test
    public void testCalulate() {
        formulaPi.calculate();
        assertEquals(3.14, formulaPi.getResult()*4, 0.01);
    }
}
