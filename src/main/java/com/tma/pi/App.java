package com.tma.pi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tma.decorator.TimeFormulaPI;
import com.tma.factory.FormulaFactory;
import com.tma.model.Input;
import com.tma.service.Formula;

/**
 * Create GUI and run Application
 *
 * @author NghiTran
 * @version 1.0
 * @since 2014-10-07
 */
public class App extends JPanel implements KeyListener, ActionListener {

    /**
     * serialVersion
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create formulaFactory
     */
    FormulaFactory formulaFactory = new FormulaFactory();

    /**
     * Create formula Pi
     */
    Formula formulaPi;

    /**
     * JTextArea where print information and result calculation
     */
    JTextArea displayArea;

    /**
     * JTextField - value input
     */
    JTextField typingArea;

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
        JButton buttonHaveTimeRun = new JButton("Start 1");
        buttonHaveTimeRun.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Get value JTextField
                String sNum = typingArea.getText();
                String[] s = sNum.split("-");
                checkPress = true;

                double quantityCalculate = 10000; // default quantityCalculate is 10000
                double quantityCalculateInAThread = quantityCalculate;
                try {
                    quantityCalculate = Double.parseDouble(s[0]);
                    if (s.length == 1) {
                        quantityCalculateInAThread = quantityCalculate;
                    } else if (s.length == 2) {
                        quantityCalculateInAThread = Double.parseDouble(s[1]);
                    }
                    if (quantityCalculateInAThread > quantityCalculate) {
                        System.out.println("Thread bound must less than number N!");
                    } else {
                        calculatePiHaveTime(quantityCalculate, quantityCalculateInAThread);
                    }
                    // System.out.println(quantityCalculate + " - " +quantityCalculateInAThread);
                } catch (Exception e2) {
                    System.out.println("Input must be number!");
                }
            }
        });

        // Button for calculate Pi normal
        JButton buttonStart = new JButton("Start 2");
        buttonStart.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Get value JTextField
                String sNum = typingArea.getText();
                String[] s = sNum.split("-");
                checkPress = true;

                double quantityCalculate = 10000; // default quantityCalculate is 10000
                double quantityCalculateInAThread = quantityCalculate;
                try {
                    quantityCalculate = Double.parseDouble(s[0]);
                    if (s.length == 1) {
                        quantityCalculateInAThread = quantityCalculate;
                    } else if (s.length == 2) {
                        quantityCalculateInAThread = Double.parseDouble(s[1]);
                    }
                    if (quantityCalculateInAThread > quantityCalculate) {
                        System.out.println("Thread bound must less than number N!");
                    } else {
                        calculatePi(quantityCalculate, quantityCalculateInAThread);
                    }
                    // System.out.println(quantityCalculate + " - " +quantityCalculateInAThread);
                } catch (Exception e2) {
                    System.out.println("Input must be number!");
                }
            }
        });

        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
        typingArea.setText("1000000000-30000000");

        // Uncomment this if you wish to turn off focus
        // traversal. The focus subsystem consumes
        // focus traversal keys, such as Tab and Shift Tab.
        // If you uncomment the following line of code, this
        // disables focus traversal and the Tab events will
        // become available to the key event listener.
        // typingArea.setFocusTraversalKeysEnabled(false);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        PrintStream printStream = new PrintStream(new CustomOutputStream(
                displayArea));
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 325));

        add(typingArea, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.PAGE_END);
        add(buttonStart, BorderLayout.EAST);
        add(buttonHaveTimeRun, BorderLayout.WEST);
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
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame = new JFrame("KeyEventDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new App();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        // displayInfo(e, "KEY TYPED: ");
    }

    public void keyReleased(KeyEvent e) {
        if (checkPress == true) {
            displayInfo(e);
        }
    }

    public void keyTyped(KeyEvent e) {
        // displayInfo(e, "KEY TYPED: ");
    }

    /**
     * when pressed Enter Application will suspend and print result current,
     * when pressed key 's' application will stop and print result
     *
     * @param e is Key event
     */
    private void displayInfo(KeyEvent e) {
        // when Pressed Enter call to suspendThread() of Formula Object
        if (e.getKeyCode() == 10) {
            System.out
                    .println("\n..............................CURRENT...............................");
            System.out.println("Finished " + formulaPi.getNumberThreadFinish() + " threads, current PI = "
                    + formulaPi.getResult() * 4);
            System.out.println("................................................................................");
        }
        if (e.getKeyCode() == 83) {
            formulaPi.stopApp();
        }
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

                Input input = new Input(quantityCalculate, quantityCalculateInAThread);
                formulaPi.setInput(input);

                Formula timeFormulaPI = new TimeFormulaPI(formulaPi);
                timeFormulaPI.calculate();
                long timRun = timeFormulaPI.getTimeRun();
                // formula1.calculate();

                System.out.println("\n+++++++++++++++++++++++++++++++++++");
                System.out.println("Time finish: " + timRun);
                System.out.println("+++++++++++++++++++++++++++++++++++");
                System.out.println("Finished all " + formulaPi.getNumberThreadFinish() + " threads, PI = "
                        + formulaPi.getResult() * 4);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@");
                checkPress = false;
            }
        });
        threadPi.start();

        Thread printRunning = new Thread(new Runnable() {
            public void run() {
                while (threadPi.isAlive()) {
                    try {
                        System.out.print("*");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        printRunning.start();
    }

    private void calculatePiHaveTime(final double quantityCalculate, final double quantityCalculateInAThread) {

        formulaPi = formulaFactory.getFormula("PI");
        // Create thread: printRunning
        final Thread threadPi = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Input input = new Input(quantityCalculate, quantityCalculateInAThread);
                formulaPi.setInput(input);

                formulaPi.calculate();

                System.out.println("\n+++++++++++++++++++++++++++++++++++");
                System.out.println("Finished all " + formulaPi.getNumberThreadFinish() + " threads, PI = "
                        + formulaPi.getResult() * 4);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@");
                checkPress = false;
            }
        });
        threadPi.start();

        Thread printRunning = new Thread(new Runnable() {
            public void run() {
                while (threadPi.isAlive()) {
                    try {
                        System.out.print("*");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        printRunning.start();
    }
}
