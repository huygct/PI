package com.tma.decorator;

import com.tma.service.Formula;

/**
 * Used calculate formula Pi have time
 * Created by NghiTran on 12/10/2014.
 * @version 1.0
 * @since 2014.10.12
 */
public class TimeFormulaPI extends  FormulaDecorator{

    // Value time run
    private long timeRun = 0;

    public TimeFormulaPI(Formula decoratedFormula) {
        super(decoratedFormula);
    }

    @Override
    public long getTimeRun() {
        return timeRun;
    }

    @Override
    public void calculate () {
        // time start
        long startTimeThread = System.currentTimeMillis();

        decoratedFormula.calculate();

        timeRun = System.currentTimeMillis() - startTimeThread;
    }
}
