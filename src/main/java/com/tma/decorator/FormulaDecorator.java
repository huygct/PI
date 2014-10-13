package com.tma.decorator;

import com.tma.model.Numbers;
import com.tma.service.Formula;

/**
 * FormulaDecorator, abstract class  implement Formula
 * Created by NghiTran on 11/10/2014.
 * @ version 1.0
 * @ since 2014.10.14
 */
public abstract class FormulaDecorator implements Formula{

    protected Formula decoratedFormula;

    public FormulaDecorator (Formula decoratedFormula) {
        this.decoratedFormula = decoratedFormula;
    }

    @Override
    public void setNumber(Numbers number) {

    }

    @Override
    public int getNumberThreadFinish() {
        return 0;
    }

    @Override
    public double getResult() {
        return 0;
    }

    @Override
    public void stopApp() {

    }

    @Override
    public void calculate() {
        decoratedFormula.calculate();
    }
}