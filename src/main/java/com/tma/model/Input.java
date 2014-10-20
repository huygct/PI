package com.tma.model;

/**
 * Class Number: defined formula's components
 * @author NghiTran
 * @version 1.1
 * @since 2014-10-07
 */
public class Input {

    // input number of calculate
    private double numberN;

    // divide numberN to create many task, each calculating task  1 amount is called threadBound
    private double threadBound;

    // Constructor of Number
    public Input(double numberN, double threadBound) {
        this.numberN = numberN;
        this.threadBound = threadBound;
    }

    public double getNumberN() {
        return numberN;
    }

    public void setNumberN(double numberN) {
        this.numberN = numberN;
    }

    public double getThreadBound() {
        return threadBound;
    }

    public void setThreadBound(double threadBound) {
        this.threadBound = threadBound;
    }
}
