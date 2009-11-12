package demo.manning.spinner;

//see \Chapter 10\7
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class ListSpinnerDemo extends JFrame {
    public ListSpinnerDemo() {
        super("Spinner Demo (List)");

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.add(new JLabel("Select state:"));
        String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE",
                "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",
                "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND",
                "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA",
                "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WV",
                "WI", "WY" };
        SpinnerModel model = new SpinnerListModel(states);
        JSpinner spn = new JSpinner(model);
        p.add(spn);
        getContentPane().add(p, BorderLayout.NORTH);
        setSize(250, 75);
    }

    public static void main(String args[]) {
        ListSpinnerDemo mainFrame = new ListSpinnerDemo();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
