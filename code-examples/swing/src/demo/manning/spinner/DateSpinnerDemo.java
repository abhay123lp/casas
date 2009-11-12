package demo.manning.spinner;

//see \Chapter 10\6

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

class DateSpinnerDemo extends JFrame {
    public DateSpinnerDemo() {
        super("Spinner Demo (Dates)");
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.add(new JLabel("Select date: "));
        
        SpinnerModel model = new SpinnerDateModel(new Date(), // initial value
                null, // Minimum value - not set
                null, // Maximum value - not set
                Calendar.DAY_OF_MONTH // Step
        );
        JSpinner spn = new JSpinner(model);
        p.add(spn);
        getContentPane().add(p, BorderLayout.NORTH);
        setSize(300, 75);
    }

    public static void main(String args[]) {
        DateSpinnerDemo mainFrame = new DateSpinnerDemo();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
