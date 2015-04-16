package com.tma.service;

/**
 * Interface for all formula
 *
 * @author  NghiTran
 * @version 1.0
 * @since 2015.04.15
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
    double getNumberFinish();

    /**
     * Get result of formula
     * 
     * @return result is number PI
     */
    double getResult();

    /**
     * Call to stop application
     */
    void stopApp();
    /**
     * Start to calculation formula
     */
    void calculate();
}
