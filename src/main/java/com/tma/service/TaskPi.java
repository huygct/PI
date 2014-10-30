package com.tma.service;

import java.util.concurrent.Callable;

/**
 * TaskPi: uses to calculation pi with bound, from nStart to nEnd
 *
 * @author NghiTran
 * @version 1.1
 * @since 2014-10-08
 */
public class TaskPi implements Callable<Double> {

    // nStart: defined the number first in space calculate
    private double nStart;
    // nEnd: the number end in space calculate
    private double nEnd;
    // result after run thread.
    private double result = 0;

    /**
     * Constructor for task
     * @param nStart number first
     * @param nEnd number end in space calculator
     */
    public TaskPi(double nStart, double nEnd) {
        this.nStart = nStart;
        this.nEnd = nEnd;
    }

    public double getResult() {
        return result;
    }

    /**
     * Calculation Pi from number nStart to number nEnd
     */
    public Double call() throws Exception {
        double sign = (nStart % 2) == 0 ? 1.0 : -1.0;

        for (double i = nStart; i <= nEnd; i++) {
            result += sign / (2 * i + 1);
            sign *= -1;
        }
        // System.out.println("%%%%% " +result);
        return result;
    }
}
