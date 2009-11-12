package demo.manning.spinner;

//see \Chapter 10\8

/**
 * In this example we show how to speed up selection by adding 
 * functionality to move several interval steps at once, and to 
 * move to the beginning or end of the list quickly. This is achieved
 * by assigning the following actions to these keys:
 * 
 * • PgUp: move 5 steps up (if new value is less than maximum bound)
 * • PgDn: move 5 steps down (if new value is greater than minimum bound)
 * • Ctrl-Home: move to the maximum bound (if set)
 * • Ctrl-End: move to the minimum bound (if set)
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class SpinnerWithHotKeyDemo extends JFrame {
    public static final int PAGE_SIZE = 5;
    SpinnerNumberModel m_model;

    public SpinnerWithHotKeyDemo() {
        super("Spinner Demo (Keys)");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Use PgUp, PgDn, Crl-Home, Ctrl-End: "));
        
        
        m_model = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner spn = new JSpinner(m_model);
        panel.add(spn);
        
        spn.registerKeyboardAction(new PgUpMover(), KeyStroke.getKeyStroke(
                KeyEvent.VK_PAGE_UP, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        spn.registerKeyboardAction(new PgDnMover(), KeyStroke.getKeyStroke(
                KeyEvent.VK_PAGE_DOWN, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        spn.registerKeyboardAction(new HomeMover(), KeyStroke.getKeyStroke(
                KeyEvent.VK_HOME, KeyEvent.CTRL_MASK),JComponent.WHEN_IN_FOCUSED_WINDOW);
        getContentPane().add(panel, BorderLayout.NORTH);
        
        setSize(400, 75);
    }

    public static void main(String args[]) {
        SpinnerWithHotKeyDemo mainFrame = new SpinnerWithHotKeyDemo();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    /**
     * Moves Spinner’s value PAGE_SIZE steps up
     */
    class PgUpMover implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Integer newValue = new Integer(m_model.getNumber().intValue()
                    - PAGE_SIZE * m_model.getStepSize().intValue());
            // Check maximum value, SpinnerNumberModel won’t do it for us
            Comparable maximum = m_model.getMaximum();
            if (maximum != null && maximum.compareTo(newValue) < 0)
                return;
            m_model.setValue(newValue);
        }
    }

    /**
     * Moves Spinner’s value PAGE_SIZE steps down
     */
    class PgDnMover implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Integer newValue = new Integer(m_model.getNumber().intValue()
                    - PAGE_SIZE * m_model.getStepSize().intValue());
            // Check minimum value, SpinnerNumberModel won’t do it for us
            Comparable minimum = m_model.getMinimum();
            if (minimum != null && minimum.compareTo(newValue) > 0)
                return;
            m_model.setValue(newValue);
        }
    }

    /**
     * Moves Spinner’s value to minimum
     */
    class HomeMover implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Comparable minimum = m_model.getMinimum();
            if (minimum != null)
                m_model.setValue(minimum);
        }
    }

    /**
     * Moves Spinner’s value to maximum
     */
    class EndMover implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Comparable maximum = m_model.getMaximum();
            if (maximum != null)
                m_model.setValue(maximum);
        }
    }

}
