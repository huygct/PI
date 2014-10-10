package com.tma.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.tma.model.Numbers;

/**
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
    List<Future<Double>> listResult = new ArrayList<Future<Double>>();
    /**
     * Vector use to save result of each thread
     */
    // public static Vector<Double> _piVector = new Vector<Double>();
    /**
     * Number number is value n that user input
     */
    private Numbers number;
    /**
     * boolean suspended - status of Thread
     */
    private boolean suspended;

    /**
     * Constructor of FormulaPI
     */
    public FormulaPI() {
        // this.threadPool = new ThreadPoolExecutor(nCore, nCore, 1000,
        // TimeUnit.SECONDS, queue);
        this.suspended = false;
    }

    /**
     * method setNumber - set value for param number.
     * 
     * @param number
     */
    public void setNumber(Numbers number) {
        this.number = number;
    }

    /**
     * get number threads were run
     */
    public double getI() {
        // return _piVector.size();
        return listResult.size();
    }

    /**
     * Get result of formulaPI
     * 
     * @return result
     */
    public double getResult() {
        // double result = 0;
        // for (int i = 0; i < _piVector.size(); i++) {
        // result += _piVector.get(i);
        // }
        // return result;
        double result = 0.0;
        for (Future<Double> future : listResult) {
            try {
                result += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Set value this.suspended is true to Thread suspend
     */
    public void suspendThread() {
        executor.shutdown();
        System.out
                .println("\n..............................CURRENT...............................");
        // System.out.println("Number current Thread " + _piVector.size());
        System.out.println("Number current Thread " + listResult.size());
    }

    /**
     * Set value this.suspended is false to Thread is continues
     */
    public void stopApp() {
        System.out.print("\nPlease wait! Application is processing. . . . . .");
        executor.shutdownNow();
    }

    /**
     * Get value suspended is true or false
     * 
     * @return suspended
     */
    public boolean getSuspended() {
        return this.suspended;
    }

    /**
     * Formula to calculation PI: Sum ((-1)^n / (2n + 1)) with n from 0 to n
     */
    public void calculate() {

        // Clear vector result
        // _piVector.clear();
        listResult.clear();

        double numberSize = number.getNumberN();
        double nCalculation = number.getThreadBound();

        double nLoop;
        if (nCalculation == 0) {
            nLoop = 1;
        } else {
            nLoop = ((numberSize - numberSize % nCalculation) / nCalculation)
                    + ((numberSize % nCalculation) != 0 ? 1 : 0);
        }

        // System.out.println(nLoop);

        long startTimeThread = System.currentTimeMillis();
        long stopTimeThread = 0;

        double nStart = 0;
        double nEnd = nCalculation;
        for (double i = 0; i < nLoop; i++) {

            if (i != 0) {
                nStart = nEnd + 1;
                nEnd = nEnd + nCalculation;
            }

            if (i == (nLoop - 1)) {
                nEnd = numberSize;
            }

            // System.out.println(i + " = " + nStart + " - " + nEnd);

            Callable<Double> worker = new ThreadPi(nStart, nEnd);
            Future<Double> submit = executor.submit(worker);
            listResult.add(submit);
            // Runnable worker = new ThreadPi(nStart, nEnd);
            // executor.execute(worker);
        }

        double result = 0.0;
        for (Future<Double> future : listResult) {
            try {
                result += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        System.out.println("----------- " + result * 4);
        executor.shutdown();
        // Wait until all threads are finish
        // try {
        // executor.awaitTermination(2000, TimeUnit.SECONDS);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        stopTimeThread = System.currentTimeMillis();
        System.out.println("\n+++++++++++++++++++++++++++++++++++");
        // System.out.println("Number Thread is: " + _piVector.size());
        System.out.println("Number Thread is: " + listResult.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out
                .println("Time finish: " + (stopTimeThread - startTimeThread));

    }
}
