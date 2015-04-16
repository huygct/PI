package com.tma.factory;

import com.tma.service.Formula;
import com.tma.leibniz.FormulaPI;

/**
 * Factory design pattern
 * this class help create a Formula object with passing param is Formula's type.
 * @author  NghiTran
 * @version 1.0
 * @since 2015.04.15
 */

public class FormulaFactory {
    
    /**
     * @param formulaType variable description for Formula
     * @return Formula
     */
    public Formula getFormula(String formulaType) {
        if (formulaType == null) {
            return null;
        }
        // Create new FormulaPI Object 
        if (formulaType.equalsIgnoreCase("PI")) {
            return new FormulaPI();
        }
        return null;
    }
}
