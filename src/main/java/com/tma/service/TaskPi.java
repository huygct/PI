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

    private double nEnd;
    private double nStart;
    private double result = 0;

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