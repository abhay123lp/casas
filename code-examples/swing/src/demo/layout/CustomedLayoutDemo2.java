package demo.layout;

import java.awt.*;
import javax.swing.*;

public class CustomedLayoutDemo2 extends JFrame {
    public CustomedLayoutDemo2() {
        super("Flight Reservation Dialog [Custom Layout - 2]");
        Container c = getContentPane();
        c.setLayout(new CustomedLayout2(20, 5));
        c.add(new JLabel("Date:"));
        c.add(new JTextField());
        c.add(new JLabel("From:"));
        JComboBox cb1 = new JComboBox();
        cb1.addItem("New York");
        c.add(cb1);
        c.add(new JLabel("To:"));
        JComboBox cb2 = new JComboBox();
        cb2.addItem("London");
        c.add(cb2);
        c.add(new DialogSeparator("Available Flights"));
        JList list = new JList();
        JScrollPane ps = new JScrollPane(list);
        c.add(ps);
        c.add(new DialogSeparator("Options"));
        ButtonGroup group = new ButtonGroup();
        JRadioButton r1 = new JRadioButton("First class");
        group.add(r1);
        c.add(r1);
        JRadioButton r2 = new JRadioButton("Business");
        group.add(r2);
        c.add(r2);
        JRadioButton r3 = new JRadioButton("Coach");
        group.add(r3);
        c.add(r3);
        c.add(new DialogSeparator());
        JButton b1 = new JButton("Search");

        c.add(b1);
        JButton b2 = new JButton("Purchase");
        c.add(b2);
        JButton b3 = new JButton("Exit");
        c.add(b3);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String argv[]) {
        new CustomedLayoutDemo2();
    }
}
