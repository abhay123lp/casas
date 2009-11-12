package demo.manning.layout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GridBagLayoutDemo2 extends JDialog {
    public GridBagLayoutDemo2(JFrame frame) {
        super(frame, true);
        setTitle("Simple Complaints Dialog");
        setSize(500, 300);
        GriddedPanel panel = new GriddedPanel();
        panel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        getContentPane().add(BorderLayout.CENTER, panel);
        // Input field dimensions
        Dimension shortField = new Dimension(40, 20);
        Dimension mediumField = new Dimension(120, 20);
        Dimension longField = new Dimension(240, 20);
        Dimension hugeField = new Dimension(240, 80);
        
        // Spacing between labels and fields
        EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 10));
        EmptyBorder border1 = new EmptyBorder(new Insets(0, 20, 0, 10));
        
        JLabel lbl1 = new JLabel("Short Description");
        lbl1.setBorder(border);
        panel.addComponent(lbl1, 1, 1);
        
        JTextField txt1 = new JTextField();
        txt1.setPreferredSize(longField);
        panel.addFilledComponent(txt1, 1, 2, 3, 1,
                GridBagConstraints.HORIZONTAL);
        
        
        JLabel lbl2 = new JLabel("Description");
        lbl2.setBorder(border);
        panel.addComponent(lbl2, 2, 1);
        
        JTextArea area1 = new JTextArea();
        JScrollPane scroll = new JScrollPane(area1);
        scroll.setPreferredSize(hugeField);
        panel.addFilledComponent(scroll, 2, 2, 3, 2, GridBagConstraints.BOTH);
        
        
        JLabel lbl3 = new JLabel("Severity");
        lbl3.setBorder(border);
        panel.addComponent(lbl3, 4, 1);
        
        JComboBox combo3 = new JComboBox();
        combo3.addItem("A");
        combo3.addItem("B");
        combo3.addItem("C");
        combo3.addItem("D");
        combo3.addItem("E");
        combo3.setPreferredSize(shortField);
        panel.addComponent(combo3, 4, 2);
        
        
        JLabel lbl4 = new JLabel("Priority");
        lbl4.setBorder(border1);
        panel.addComponent(lbl4, 4, 3);
        
        JComboBox combo4 = new JComboBox();
        combo4.addItem("1");
        combo4.addItem("2");
        combo4.addItem("3");
        combo4.addItem("4");
        combo4.addItem("5");
        combo4.setPreferredSize(shortField);
        panel.addComponent(combo4, 4, 4);
        
        
        JLabel lbl5 = new JLabel("Name");
        lbl5.setBorder(border);
        panel.addComponent(lbl5, 5, 1);
        
        JTextField txt5 = new JTextField();
        txt5.setPreferredSize(longField);
        panel.addComponent(txt5, 5, 2, 3, 1);

        
        JLabel lbl6 = new JLabel("Telephone");
        lbl6.setBorder(border);
        panel.addComponent(lbl6, 6, 1);
        
        JTextField txt6 = new JTextField();
        txt6.setPreferredSize(mediumField);
        panel.addComponent(txt6, 6, 2, 3, 1);
        
        
        JLabel lbl7 = new JLabel("Sex");
        lbl7.setBorder(border);
        panel.addComponent(lbl7, 7, 1);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        ButtonGroup group = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("Male");
        radio1.setSelected(true);
        group.add(radio1);
        JRadioButton radio2 = new JRadioButton("Female");
        group.add(radio2);
        radioPanel.add(radio1);
        radioPanel.add(radio2);
        panel.addComponent(radioPanel, 7, 2, 3, 1);
        
        
        JLabel lbl8 = new JLabel("ID Number");
        lbl8.setBorder(border);
        panel.addComponent(lbl8, 8, 1);
        
        JTextField txt8 = new JTextField();
        txt8.setPreferredSize(mediumField);
        panel.addComponent(txt8, 8, 2, 3, 1);
        
        
        JButton submitBtn = new JButton("Submit");
        panel.addFilledComponent(submitBtn, 1, 5);
        JButton cancelBtn = new JButton("Cancel");
        panel.addFilledComponent(cancelBtn, 2, 5);
        JButton helpBtn = new JButton("Help");
        panel.addComponent(helpBtn, 3, 5, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridBagLayoutDemo2(new JFrame());
    }

}
