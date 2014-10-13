package com.tma.service;

import com.tma.model.Numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Formula PI. Calculation pi with many task. Then sum result each task
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
    private Numbers number;

    /**
     * Constructor of FormulaPI
     */
    public FormulaPI() {

    }

    /**
     * method setNumber - set value for param number.
     *
     * @param number include: N and bound of a thread
     */
    @Override
    public void setNumber(Numbers number) {
        this.number = number;
    }

    @Override
    public long getTimeRun() {
        return 0;
    }

    /**
     * Get number threads were run
     * @return numberThread is number thread finish, at the moment.
     */
    @Override
    public int getNumberThreadFinish() {
        int numberThread = 0;
        Future<Double> future;
        for (int i = 0; i < listResult.size(); i++) {
            future = listResult.get(i);
            if(future.isDone()) {
                numberThread++;
            }
            else {
                break;
            }
        }
        return numberThread;
    }

    /**
     * Get result of formulaPI
     *
     * @return result is number Pi
     */
    @Override
    public double getResult() {
        double result = 0.0;
        Future<Double> future;
        for (int i = 0; i < listResult.size(); i++) {
            future = listResult.get(i);
            try {
                if(future.isDone()) {
                    // System.out.println(future.isDone() + " - " + i + " = " + future.get());
                    result += future.get();
                }
                else {
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
     */
    @Override
    public void stopApp() {
        executor.shutdownNow();
    }

    /**
     * Formula to calculate PI: Sum ((-1)^n / (2n + 1)) with n from 0 to N
     */
    @Override
    public void calculate() {

        listResult.clear();

        double numberSize = number.getNumberN();
        double nCalculation = number.getThreadBound();

        int nLoop;
        if (nCalculation == 0) {
            nLoop = 1;
        } else {
            nLoop = (int)((numberSize - numberSize % nCalculation) / nCalculation)
                    + ((numberSize % nCalculation) != 0 ? 1 : 0);
        }

        // System.out.println(nLoop);

        double nStart = 0;
        double nEnd = nCalculation;
        for (int i = 0; i < nLoop; i++) {

            if (i != 0) {
                nStart = nEnd + 1;
                nEnd = nEnd + nCalculation;
            }

            if (i == (nLoop - 1)) {
                nEnd = numberSize;
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
