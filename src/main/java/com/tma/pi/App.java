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

import com.tma.factory.FormulaFactory;
import com.tma.model.Numbers;
import com.tma.service.Formula;

/**
 * Create GUI and run Application
 * 
 * @author NghiTran
 * @version 1.0
 * @since 2014-10-07
 *
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
     * formula
     */
    Formula formula1;

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

    static final String newline = "\n";

    /**
     * constructor of Application
     */
    public App() {
        super(new BorderLayout());

        JButton button = new JButton("Clear");
        button.addActionListener(this);

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

        JButton buttonStart = new JButton("Start");
        // Add action listener to buttonStart
        buttonStart.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Get value JTextField
                String sNum = typingArea.getText();
                String[] s = sNum.split("-");
                checkPress = true;

                double numberSize = 10000; // default numberSize is 10000
                double threadBound = numberSize;
                try {
                    numberSize = Double.parseDouble(s[0]);
                    if (s.length == 1) {
                        threadBound = numberSize;
                    }
                    else if (s.length == 2) {
                        threadBound = Double.parseDouble(s[1]);
                    }
                    if (threadBound > numberSize) {
                        System.out.println("Thread bound must less than number N!");
                    }
                    else {
                        calcutaionPi(numberSize, threadBound);
                    }
//                    System.out.println(numberSize + " - " +threadBound);
                } catch (Exception e2) {
                    System.out.println("Input must be number!");
                }
            }
        });

        add(typingArea, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.PAGE_END);
        add(buttonStart, BorderLayout.EAST);
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

    public void keyPressed(KeyEvent e) {
        // displayInfo(e, "KEY TYPED: ");
    }

    public void keyReleased(KeyEvent e) {
        if (checkPress == true) {
            displayInfo(e, "KEY TYPED: ");
        }
    }

    public void keyTyped(KeyEvent e) {
        // displayInfo(e, "KEY TYPED: ");
    }

    /**
     * when pressed Enter Application will stop and print result current, number
     * n when pressed key 'a' application will resume and print notify
     * 
     * @param e
     * @param s
     */
    private void displayInfo(KeyEvent e, String s) {
        // when Pressed Enter call to suspendThread() of Formula Object
        if (e.getKeyCode() == 10) {
            formula1.suspendThread();
            System.out.println("Current Result: " + formula1.getResult()*4);
            System.out.println("................................................................................");
        }
        if (e.getKeyCode() == 83) {
            formula1.stopApp();
        }
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

    /**
     * button clear help to clear JTextArea
     */
    public void actionPerformed(ActionEvent arg0) {
        displayArea.setText("");
    }

    /**
     * Create Formula with formula's name. Set number into formula. Create
     * threadPi and start it. Create thread name is printRunning, it is used to
     * print '.', that help we know application is running
     * 
     * @param numberSize
     * @param threadBound
     */
    private void calcutaionPi(final double numberSize, final double threadBound) {

        formula1 = formulaFactory.getFormula("PI");
        // Create thread: printRunning
        final Thread threadPi = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Numbers number = new Numbers(numberSize, threadBound);
                formula1.setNumber(number);
                formula1.calculate();

                System.out.println("Finished all threads, PI = "
                        + formula1.getResult()*4);
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
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        printRunning.start();

    }
}
