package com.tma.model;

/**
 * Class Input: defined formula's components
 * @author NghiTran
 * @version 1.1
 * @since 2014-10-07
 */
public class Input {

    // input quantity calculate
    private final double numberOfCalculation;

    // divide numberOfCalculation to create many task, each calculating task  1 amount is called numberOfCalculationInAThread
    private final double numberOfCalculationInAThread;

    // Constructor of Number
    public Input(double quantityCalculate, double quantityCalculateInAThread) {
        this.numberOfCalculation = quantityCalculate;
        this.numberOfCalculationInAThread = quantityCalculateInAThread;
    }

    public double getNumberOfCalculation() {
        return numberOfCalculation;
    }

    public double getNumberOfCalculationInAThread() {
        return numberOfCalculationInAThread;
    }
}
