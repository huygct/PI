package com.tma.service;

import com.tma.model.Numbers;

/**
 * Interface for all formula
 * 
 * @author NghiTran
 * @version 1.0
 * @since 2014-10-07
 */
public interface Formula {
    /**
     * Set number for formula
     * 
     * @param number
     */
    void setNumber(Numbers number);

    /**
     * Get number threads were run ~ size vector
     * 
     * @return i
     */
    double getI();

    /**
     * Get result of formula
     * 
     * @return result
     */
    double getResult();

    /**
     * Call to stop thread
     */
    void suspendThread();

    /**
     * Call to stop application
     */
    void stopApp();
    /**
     * Start to calculation formula
     */
    void calculate();
}
