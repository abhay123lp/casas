package demo.manning.spinner;

//see \Chapter 10\5
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class NumberSpinnerDemo extends JFrame {
    
    /*
     *  contentPane
     *    -- Panel
     *        -- Label + Spinner
     */
    public NumberSpinnerDemo() {
        super("Spinner Demo (Numbers)");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Select integer: "));
        
        
        SpinnerModel model = new SpinnerNumberModel(new Integer(0), // initial
                                                                    // value
                new Integer(0), // Minimum value
                null, // Maximum value - not set
                new Integer(2) // Step
        );
        JSpinner spn = new JSpinner(model);
        panel.add(spn);
        getContentPane().add(panel, BorderLayout.NORTH);
        setSize(400, 75);
    }

    public static void main(String args[]) {
        NumberSpinnerDemo mainFrame = new NumberSpinnerDemo();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
