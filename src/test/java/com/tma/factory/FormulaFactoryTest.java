package com.tma.factory;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for class FormulaPI
 * 
 * @author NghiTran
 * @version 1.0
 * @since 2014-10-07
 */
public class FormulaFactoryTest {

    FormulaFactory formulaFactory;

    public FormulaFactoryTest() {
    }

    @Before
    public void setUp() throws Exception {
        // Create formulaFactory
        formulaFactory = new FormulaFactory();
    }

    @Test
    public void testGetFormula() {
        // Test with input is null
        assertSame(null, formulaFactory.getFormula(null));
        // Test with input is any
        assertSame(null, formulaFactory.getFormula("abc"));
    }
}
