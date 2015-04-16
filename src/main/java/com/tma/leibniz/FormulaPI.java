package com.tma.leibniz;

import com.tma.service.Formula;
import com.tma.service.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Formula PI. Create task to calculate base on input
 * Calculation pi with many task. Then sum result each task
 *
 * @author  NghiTran
 * @version 1.0
 * @since 2015.04.15
 */
public class FormulaPI implements Formula {

     // Get number computer's core
    int nCore = Runtime.getRuntime().availableProcessors();

     // Create Thread pool is ExecutorService
    ExecutorService executor = Executors.newFixedThreadPool(nCore);

    // List Future save result return from callable
    List<Future<Double>> listResult = new ArrayList<Future<Double>>();

    // Input of formula PI: number of calculation and number of calculation in thread
    private InputImpl input;

    // Constructor of FormulaPI
    public FormulaPI() {

    }

    /**
     * method setInput - set value for param input.
     *
     * @param input include: numberOfCalculation and numberOfCalculationInAThread
     */
    public void setInput(Input input) {
        this.input = (InputImpl)input;
    }

    /**
     * Get number threads were run
     *
     * @return numberThread is number thread finished.
     */
    public double getNumberFinish() {
        int number = 0;
        Future<Double> future;
        for (int i = 0; i < listResult.size(); i++) {
            future = listResult.get(i);
            if (future.isDone()) {
                number++;
            } else {
                break;
            }
        }
        return number * input.getNumberOfCalculationInAThread();
    }

    /**
     * Get result of formulaPI
     * Check List<Future<Double>>, while future.isDone == true, get result from future and plus it into result.
     * If future.isDone == false is break;
     *
     * @return result is number Pi
     */
    public double getResult() {
        double result = 0.0;
        Future<Double> future;
        for (int i = 0; i < listResult.size(); i++) {
            future = listResult.get(i);
            try {
                if (future.isDone()) {
                    result += future.get();
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("------------- getResult_1()");
//                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("------------- getResult_2()");
//                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Stop application
     * executor is ThreadPool. executor call shutdownNow to stop application
     */
    public void stopApp() {
        executor.shutdownNow();
    }

    /**
     * Formula to calculate PI: Sum ((-1)^n / (2n + 1)) with n from 0 to N
     * from 0 to N --> 0 - n1, n1+1 - n2, n2+1 - n3, n3+1 - n
     * create many task, each task calculate a bound
     * numberOfCalculation is N
     * numberOfCalculationInAThread is [0 - n1] or [n1+1 - n2] ...
     * TaskPi is a Callable, it will implement work and return result, result will save into List<Future<Double>>
     * Create a ThreadPool contains number Thread to implement TaskPi.
     */
    public void calculate() {

        listResult.clear();

        double numberOfCalculation = input.getNumberOfCalculation();
        double numberOfCalculationInAThread = input.getNumberOfCalculationInAThread();

        int nLoop;
        if (numberOfCalculationInAThread == 0) {
            nLoop = 1;
        } else {
            nLoop = (int) (numberOfCalculation / numberOfCalculationInAThread)
                    + ((numberOfCalculation % numberOfCalculationInAThread) != 0 ? 1 : 0);
        }

        // System.out.println(nLoop);

        double nStart = 0;
        double nEnd = numberOfCalculationInAThread;
        for (int i = 0; i < nLoop; i++) {

            if (i != 0) {
                nStart = nEnd + 1;
                nEnd = nEnd + numberOfCalculationInAThread;
            }

            if (i == (nLoop - 1)) {
                nEnd = numberOfCalculation;
            }

            try {
                Callable<Double> task = new TaskPi(nStart, nEnd);
                Future<Double> submit = executor.submit(task);
                listResult.add(submit);
            } catch (Throwable throwable) {
                break;
            }
        }

        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();

        // Wait until all threads are finish
        try {
            executor.awaitTermination(2000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("------------- calculate()");
            // e.printStackTrace();

        }
    }
}
