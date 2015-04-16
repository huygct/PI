package com.tma.leibniz;

import com.tma.service.Input;

/**
 * Class Input: defined formula's components
 * @author  NghiTran
 * @version 1.0
 * @since 2015.04.15
 */
public class InputImpl implements Input {

    // input quantity calculate
    private final double numberOfCalculation;

    // divide numberOfCalculation to create many task, each calculating task  1 amount is called numberOfCalculationInAThread
    private final double numberOfCalculationInAThread;

    // Constructor of Number
    public InputImpl(double quantityCalculate, double quantityCalculateInAThread) {
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
