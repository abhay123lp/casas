package demo.manning.textcomponent;

//see \Chapter11\6

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

class FormattedSpinnerDemo extends JFrame {
    public FormattedSpinnerDemo() {
        super("Spinner Demo (Formatted)");
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        p.add(new JLabel("Dollar amount: "));
        SpinnerModel model = new SpinnerNumberModel(new Double(100.01),
                new Double(0), null, new Double(20));
        JSpinner spn = new JSpinner(model);
        
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) spn.getEditor())
                .getTextField();
        ftf.setColumns(10);
        NumberFormatter nf = new NumberFormatter(NumberFormat
                .getCurrencyInstance(Locale.US));
        DefaultFormatterFactory dff = new DefaultFormatterFactory();
        dff.setDefaultFormatter(nf);
        dff.setDisplayFormatter(nf);
        dff.setEditFormatter(nf);
        ftf.setFormatterFactory(dff);
        p.add(spn);
        getContentPane().add(p, BorderLayout.NORTH);
        pack();
    }

    public static void main(String args[]) {
        FormattedSpinnerDemo mainFrame = new FormattedSpinnerDemo();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}