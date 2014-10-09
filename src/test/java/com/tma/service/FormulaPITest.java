package com.tma.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.tma.model.Numbers;

public class FormulaPITest {

    FormulaPI formulaPi;
    Numbers number;
    
    // Create FormulaPI and Number class used mock
    @Before
    public void setUp() throws Exception {
        formulaPi = new FormulaPI();
        number = mock(Numbers.class);
        formulaPi.setNumber(number);
        when(number.getNumberN()).thenReturn(100000.0);
        when(number.getThreadBound()).thenReturn(3000.0);
    }

    // Test constructor FormulaPI
    @Test
    public void testFormulaPI() {
        assertEquals(0L, formulaPi.getResult(), 0.001);
        assertSame(false, formulaPi.getSuspended());
    }

    //Test get Result
    @Test
    public void testGetResult() {
        assertEquals(0L, formulaPi.getResult(), 0.001);
    }
}
