package com.tma.pi;

import com.tma.decorator.TimeFormulaPI;
import com.tma.factory.FormulaFactory;
import com.tma.leibniz.InputImpl;
import com.tma.service.Formula;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.*;

/**
 * Create GUI and run Application
 * Created by Nghi Tran on 4/15/2015.
 */
public class App extends JPanel implements ActionListener {

    /**
     * serialVersion
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create formula Pi
     */
    Formula formulaPi;

    /**
     * Create formulaFactory
     */
    FormulaFactory formulaFactory = new FormulaFactory();

    /**
     * JTextArea where print information and result calculation
     */
    JTextArea displayArea;

    /**
     * JTextField - value input
     */
    JTextField typingArea_n = new JTextField(20);;
    JTextField typingArea_numberOfAThread = new JTextField(20);;
    JLabel jLabel1 = new JLabel("Number n of Pi Formula: ");;
    JLabel jLabel2 = new JLabel("Number of calculation in a Thread: ");;
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel grid = new JPanel(new GridLayout(3,1));

    JButton buttonStart = new JButton("Start");
    JButton buttonStop = new JButton("Stop");
    JButton buttonResultCurrent = new JButton("Current Result");

    // check press key
    boolean checkPress = false;

    /**
     * constructor of Application
     */
    public App() {
        super(new BorderLayout());

        JButton button = new JButton("Clear");
        button.addActionListener(this);

        // Button for Decorator to calculate pi and have time
        buttonStart.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Get value JTextField
                checkPress = true;

                double quantityCalculate = 10000; // default quantityCalculate is 10000
                double quantityCalculateInAThread = quantityCalculate;
                try {
                    quantityCalculate = Double.parseDouble(typingArea_n.getText());
                    quantityCalculateInAThread = Double.parseDouble(typingArea_numberOfAThread.getText());
                    if (quantityCalculate < 0 || quantityCalculateInAThread < 0) {
                        System.out.println("Input must be a number >= 0 ");
                    } else {
                        if (quantityCalculateInAThread == 0) {
                            quantityCalculateInAThread = quantityCalculate;
                        }

                        if (quantityCalculateInAThread > quantityCalculate) {
                            System.out.println("Number of calculation in a thread must less than number N!");
                        } else {
                            calculatePi(quantityCalculate, quantityCalculateInAThread);
                            checkPress = false;
                        }
                    }
                    // System.out.println(quantityCalculate + " - " +quantityCalculateInAThread);
                } catch (Exception e2) {
                    System.out.println("Input must be number!");
                }
            }
        });

        // Button to stop calculate Pi
//        buttonStop.setEnabled(false);
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formulaPi.stopApp();
                buttonStart.setEnabled(true);
                buttonStop.setEnabled(false);
                buttonResultCurrent.setEnabled(false);
            }
        });

        // Button to get result current
//        buttonResultCurrent.setEnabled(false);
        buttonResultCurrent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out
                        .println("\n..............................CURRENT...............................");
                System.out.println("Finished from 0 to " + (long)formulaPi.getNumberFinish() + ", current PI = "
                        + formulaPi.getResult() * 4);
                System.out.println("................................................................................");
            }
        });

        typingArea_n.setText("1000000000");
        typingArea_numberOfAThread.setText("30000000");
        panel1.add(jLabel1);
        panel1.add(typingArea_n);

        panel2.add(buttonStart);
        panel2.add(buttonResultCurrent);
        panel2.add(buttonStop);

        panel3.add(jLabel2);
        panel3.add(typingArea_numberOfAThread);

        grid.add(panel1);
        grid.add(panel3);
        grid.add(panel2);

        // Uncomment this if you wish to turn off focus
        // traversal. The focus subsystem consumes
        // focus traversal keys, such as Tab and Shift Tab.
        // If you uncomment the following line of code, this
        // disables focus traversal and the Tab events will
        // become available to the key event listener.
        // typingArea_n.setFocusTraversalKeysEnabled(false);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        PrintStream printStream = new PrintStream(new CustomOutputStream(
                displayArea));
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 325));

        add(grid, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.PAGE_END);
        // add(buttonStart_NotTime, BorderLayout.WEST);
        // add(buttonStart, BorderLayout.EAST);
    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /**
     * Create and show GUI
     */
    private static void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(false);

        // Create and set up the window.
        JFrame frame = new JFrame("PI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new App();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * button clear help to clear JTextArea
     */
    public void actionPerformed(ActionEvent arg0) {
        displayArea.setText("");
    }

    /**
     * Create Formula with formula's name. Set number into formula. Create
     * threadPi and start it. Create thread name is printRunning, it is used to
     * print '*', that help we know application is running
     *
     * @param quantityCalculate  user input quantityCalculate, calculate Pi from 0 to quantityCalculate
     * @param quantityCalculateInAThread Bound of each thread, user will input it.
     */
    private void calculatePi(final double quantityCalculate, final double quantityCalculateInAThread) {
        formulaPi = formulaFactory.getFormula("PI");
        // Create thread: printRunning
        final Thread threadPi = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                InputImpl input = new InputImpl(quantityCalculate, quantityCalculateInAThread);
                formulaPi.setInput(input);

                TimeFormulaPI timeFormulaPI = new TimeFormulaPI(formulaPi);
                timeFormulaPI.calculate();
                long timRun = timeFormulaPI.getTimeRun();
                // formula1.calculate();

                System.out.println("\n+++++++++++++++++++++++++++++++++++");
                System.out.println("Time finish: " + timRun);
                System.out.println("+++++++++++++++++++++++++++++++++++");
                System.out.println("Completed, PI = "
                        + formulaPi.getResult() * 4);

                buttonStop.setEnabled(false);
                buttonResultCurrent.setEnabled(false);
                buttonStart.setEnabled(true);
            }
        });
        threadPi.start();

        Thread printRunning = new Thread(new Runnable() {
            public void run() {
                buttonStop.setEnabled(true);
                buttonResultCurrent.setEnabled(true);
                buttonStart.setEnabled(false);
                while (threadPi.isAlive()) {
                    try {
                        System.out.print("*");
                        Thread.sleep(1000);
                        System.out.print(".");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("------------------- printRunning()");
                        // e.printStackTrace();
                    }
                }
            }
        });
        printRunning.start();
    }
}
