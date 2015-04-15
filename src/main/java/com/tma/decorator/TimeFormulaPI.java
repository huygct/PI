package com.tma.decorator;

import com.tma.service.Formula;

/**
 * Used calculate formula Pi have time run
 *
 * @author  NghiTran
 * @version 1.0
 * @since 2014.12.26
 */
public class TimeFormulaPI extends FormulaDecorator {

    // Value time run
    private long timeRun = 0;

    // Constructor inhered in FormulaDecorator
    public TimeFormulaPI(Formula decoratedFormula) {
        super(decoratedFormula);
    }

    /**
     * Get time run of app
     *
     * @return timeRun is time calculate of formula pi
     */
    public long getTimeRun() {
        return timeRun;
    }

    /**
     * Calculate time and run formula
     */
    @Override
    public void calculate() {
        // time start
        long startTimeThread = System.currentTimeMillis();

        decoratedFormula.calculate();

        // time end
        timeRun = System.currentTimeMillis() - startTimeThread;
    }
}
