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
        when(input.getNumberOfCalculation()).thenReturn(1000000000.0);
        when(input.getNumberOfCalculationInAThread()).thenReturn(30000.0);
        formulaPi.calculate();
    }

    // Test constructor FormulaPI
    @Test
    public void testFormulaPI() {
    }

    //Test get Result
    @Test
    public void testGetResult() {
        assertEquals(Math.PI, formulaPi.getResult()*4, 0.000000001);
    }
    
    // Test get number thread finish
    @Test
    public void testGetNumberThreadFinish() {
        assertEquals(33334.0, (formulaPi.getNumberFinish()/input.getNumberOfCalculationInAThread()), 0.000000001);
    }
    
    // Test calculate
    @Test
    public void testCalulate() {
        assertEquals(Math.PI, formulaPi.getResult()*4, 0.000000001);
    }
}
