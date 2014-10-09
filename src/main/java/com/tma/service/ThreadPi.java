package com.tma.service;

/**
 * ThreadPi: uses to calculation pi with bound, from nStart to nEnd
 * @author NghiTran
 * @version 1.1
 * @since 2014-10-08
 */
public class ThreadPi implements Runnable {

    private double nEnd;
    private double nStart;
    private double result = 0;

    public ThreadPi(double nStart, double nEnd) {
        this.nStart = nStart;
        this.nEnd = nEnd;
    }
    
    public double getResult() {
        return result;
    }

    /**
     * Calculation Pi from nStart to nEnd
     */
    public void run() {

        double sign = (nStart % 2) == 0 ? 1.0 : -1.0;

        for (double i = nStart; i < nEnd; i++) {
            result += sign / (2 * i + 1);
            sign *= -1;
        }

        FormulaPI._piVector.add(result*4);
    }
}
