package com.tma.service;

import com.tma.model.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Formula PI. Calculation pi with many task. Then sum result each task
 *
 * @author NghiTran
 * @version 1.0
 * @since 2014-10-07
 */
public class FormulaPI implements Formula {

    /**
     * Get number computer's core
     */
    int nCore = Runtime.getRuntime().availableProcessors();
    // public ArrayBlockingQueue<Runnable> queue = new
    // ArrayBlockingQueue<Runnable>(100);
    // private ThreadPoolExecutor threadPool;
    /**
     * Create Thread pool is ExecutorService
     */
    ExecutorService executor = Executors.newFixedThreadPool(nCore);
    /**
     * List Future save result return from callable
     */
    List<Future<Double>> listResult = new ArrayList<Future<Double>>();
    /**
     * Number number is value n and thread bound that user input
     */
    private Input input;

    /**
     * Constructor of FormulaPI
     */
    public FormulaPI() {

    }

    /**
     * method setInput - set value for param input.
     *
     * @param input include: numberOfCalculation and numberOfCalculationInAThread
     */
    public void setInput(Input input) {
        this.input = input;
    }

    /**
     * Get time run of application
     *
     * @return time run
     */
    public long getTimeRun() {
        return 0;
    }

    /**
     * Get number threads were run
     *
     * @return numberThread is number thread finished.
     */
    public int getNumberThreadFinish() {
        int numberThread = 0;
        Future<Double> future;
        for (int i = 0; i < listResult.size(); i++) {
            future = listResult.get(i);
            if (future.isDone()) {
                numberThread++;
            } else {
                break;
            }
        }
        return numberThread;
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
                    // System.out.println(future.isDone() + " - " + i + " = " + future.get());
                    result += future.get();
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
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
            nLoop = (int) ((numberOfCalculation - numberOfCalculation % numberOfCalculationInAThread) / numberOfCalculationInAThread)
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

            Callable<Double> worker = new TaskPi(nStart, nEnd);
            Future<Double> submit = executor.submit(worker);
            listResult.add(submit);
        }

        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();

        // Wait until all threads are finish
        try {
            executor.awaitTermination(2000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
