package com.tma.service;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
//    public ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
//    private ThreadPoolExecutor threadPool;
    /**
     * Create Thread pool is ExecutorService
     */
    ExecutorService executor = Executors.newFixedThreadPool(nCore);
    /**
     * Vector use to save result of each thread
     */
    public static Vector<Double> _piVector = new Vector<Double>();
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
//        this.threadPool = new ThreadPoolExecutor(nCore, nCore, 1000, TimeUnit.SECONDS, queue);
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
        return _piVector.size();
    }
    /**
     * Get result of formulaPI
     * 
     * @return result
     */
    public double getResult() {
        double result = 0;
        for (int i = 0; i < _piVector.size(); i++) {
            result += _piVector.get(i);
        }
        return result;
    }
    /**
     * Set value this.suspended is true to Thread suspend
     */
    public void suspendThread() {
        executor.shutdown();
        System.out.println("\n..............................CURRENT...............................");
        System.out.println("Number current Thread " +_piVector.size());
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
    public void calculation() {

        // Clear vector result
        _piVector.clear();
        
        double numberSize = number.getNumberN();
        double nCalculation = number.getThreadBound();

        double nLoop = ((numberSize - numberSize % nCalculation) / nCalculation) + ((numberSize % nCalculation) != 0 ? 1 : 0);
        
        long startTimeThread = System.currentTimeMillis();
        long stopTimeThread = 0;

        for (double i = 0; i < nLoop; i++) {
//            System.out.println(i + " = " + i * nCalculation + " - "
//                    + (nCalculation + (i * nCalculation)));

            double nStart = (i * nCalculation);
            double nEnd = nCalculation + (i * nCalculation);

            if (i == (nLoop - 1)) {
                nEnd = numberSize;
            }
            
            // Thang Nhan bao ko co cai nay thi no van chaay. boi vi, shutdownnow chi la bat cai co bao interrupted
//            if(Thread.currentThread().isInterrupted()) {
//                break;
//            }

            Runnable worker = new ThreadPi(nStart, nEnd);
            executor.execute(worker);
        }
        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
        // Wait until all threads are finish
        try {
            executor.awaitTermination(2000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        stopTimeThread = System.currentTimeMillis();
        System.out.println("\n+++++++++++++++++++++++++++++++++++");
        System.out.println("Number Thread is: " +_piVector.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out
                .println("Time finish: " + (stopTimeThread - startTimeThread));
    }
}
