package com.tma.decorator;

import com.tma.service.Formula;
import com.tma.service.Input;

/**
 * User Decorator pattern to create FormulaPI have time run
 * FormulaDecorator, abstract class  implement Formula
 * Created by NghiTran on 15/04/2015.
 *
 * @author  NghiTran
 * @version 1.0
 * @since 2015.04.15
 */
public abstract class FormulaDecorator implements Formula{

    protected Formula decoratedFormula;

    /**
     * Create construction
     * @param decoratedFormula is a formula
     */
    public FormulaDecorator (Formula decoratedFormula) {
        this.decoratedFormula = decoratedFormula;
    }

    public void setInput(Input input) {

    }

    public double getNumberFinish() {
        return 0;
    }

    public double getResult() {
        return 0;
    }

    public void stopApp() {

    }
}