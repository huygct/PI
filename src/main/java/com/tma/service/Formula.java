package com.tma.service;

import com.tma.model.Input;

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
     * @param input is all what user input
     */
    void setInput(Input input);

    /**
     * Get number threads were finish ~ size list
     * 
     * @return numberThreadFinish number Threads were finish
     */
    int getNumberThreadFinish();

    /**
     * Get result of formula
     * 
     * @return result is number PI
     */
    double getResult();

    /**
     * Get time run Application
     * @return time run of application
     */
    long getTimeRun();

    /**
     * Call to stop application
     */
    void stopApp();
    /**
     * Start to calculation formula
     */
    void calculate();
}
