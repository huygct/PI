package com.tma.decorator;

import com.tma.model.Input;
import com.tma.service.Formula;

/**
 * User Decorator pattern to create FormulaPI have time run
 * FormulaDecorator, abstract class  implement Formula
 * Created by NghiTran on 11/10/2014.
 * @ version 1.0
 * @ since 2014.10.14
 */
public abstract class FormulaDecorator implements Formula{

    protected Formula decoratedFormula;

    /**
     * Create construction
     * @param decoratedFormula is a formula used
     */
    public FormulaDecorator (Formula decoratedFormula) {
        this.decoratedFormula = decoratedFormula;
    }

    public void setInput(Input input) {

    }

    public int getNumberThreadFinish() {
        return 0;
    }

    public double getResult() {
        return 0;
    }

    public void stopApp() {

    }
}