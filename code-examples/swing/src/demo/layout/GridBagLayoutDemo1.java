package demo.layout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GridBagLayoutDemo1 extends JDialog {
    JPanel panel;
    GridBagConstraints constraints = new GridBagConstraints();

    // Define preferred sizes for input fields
    Dimension shortField = new Dimension(40, 20);
    Dimension mediumField = new Dimension(120, 20);
    Dimension longField = new Dimension(240, 20);
    Dimension hugeField = new Dimension(240, 80);

    // Spacing between label and field
    EmptyBorder border = new EmptyBorder(new Insets(0, 0, 0, 10));
    EmptyBorder border1 = new EmptyBorder(new Insets(0, 20, 0, 10));

    public GridBagLayoutDemo1(JFrame frame) {
        super(frame, true);
        setTitle("Simple Complaints Dialog");
        setSize(500, 300);

        // Creates a panel to hold all components
        panel = new JPanel(new BorderLayout());
        panel.setLayout(new GridBagLayout());

        // Give the panel a border gap of 5 pixels
        panel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        getContentPane().add(BorderLayout.CENTER, panel);

        // Add space around all components to avoid clutter
        constraints.insets = new Insets(2, 2, 2, 2);
        // Anchor all components WEST
        constraints.anchor = GridBagConstraints.WEST;

        addShortDescription();
        addDescription();
        addSeverity();
        addPriority();
        addName();
        addTelephone();
        addSex();
        addIDNumber();
        addButtons();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addShortDescription() {
        JLabel lbl1 = new JLabel("Short Description");
        lbl1.setBorder(border); // Add some space to the right
        panel.add(lbl1, constraints);

        JTextField txt1 = new JTextField();
        txt1.setPreferredSize(longField);
        constraints.gridx = 1;
        constraints.weightx = 1.0; // Use all available horizontal space
        constraints.gridwidth = 3; // Spans across 3 columns
        constraints.fill = GridBagConstraints.HORIZONTAL; // Fills the 3 columns
        panel.add(txt1, constraints);

    }

    private void addDescription() {
        JLabel lbl2 = new JLabel("Description");
        lbl2.setBorder(border);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.0; // Do not use any extra horizontal space
        panel.add(lbl2, constraints);

        JTextArea area1 = new JTextArea();
        JScrollPane scroll = new JScrollPane(area1);
        scroll.setPreferredSize(hugeField);
        constraints.gridx = 1;
        constraints.weightx = 1.0; // Use all available horizontal space
        constraints.weighty = 1.0; // Use all available vertical space
        constraints.gridwidth = 3; // Span across 3 columns
        constraints.gridheight = 2; // Span across 2 rows
        constraints.fill = GridBagConstraints.BOTH; // Fills the columns and rows
        panel.add(scroll, constraints);
    }

    private void addSeverity() {
        JLabel lbl3 = new JLabel("Severity");
        lbl3.setBorder(border);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(lbl3, constraints);

        JComboBox combo3 = new JComboBox();
        combo3.addItem("A");
        combo3.addItem("B");
        combo3.addItem("C");
        combo3.addItem("D");
        combo3.addItem("E");
        combo3.setPreferredSize(shortField);
        constraints.gridx = 1;
        panel.add(combo3, constraints);
    }

    private void addPriority() {
        JLabel lbl4 = new JLabel("Priority");
        lbl4.setBorder(border1);
        constraints.gridx = 2;
        panel.add(lbl4, constraints);

        JComboBox combo4 = new JComboBox();
        combo4.addItem("1");
        combo4.addItem("2");
        combo4.addItem("3");
        combo4.addItem("4");
        combo4.addItem("5");
        combo4.setPreferredSize(shortField);
        constraints.gridx = 3;
        panel.add(combo4, constraints);
    }

    private void addName() {
        JLabel lbl5 = new JLabel("Name");
        lbl5.setBorder(border);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(lbl5, constraints);

        JTextField txt5 = new JTextField();
        txt5.setPreferredSize(longField);
        constraints.gridx = 1;
        constraints.gridwidth = 3;
        panel.add(txt5, constraints);
    }

    private void addTelephone() {
        JLabel lbl6 = new JLabel("Telephone");
        lbl6.setBorder(border);
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(lbl6, constraints);

        JTextField txt6 = new JTextField();
        txt6.setPreferredSize(mediumField);
        constraints.gridx = 1;
        constraints.gridwidth = 3;
        panel.add(txt6, constraints);
    }

    private void addSex() {
        JLabel lbl7 = new JLabel("Sex");
        lbl7.setBorder(border);
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(lbl7, constraints);

        JPanel radioPanel = new JPanel();
        // Create a FlowLayout JPanel with 5 pixel horizontal gaps
        // and no vertical gaps
        radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        ButtonGroup group = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("Male");
        radio1.setSelected(true);
        group.add(radio1);
        JRadioButton radio2 = new JRadioButton("Female");
        group.add(radio2);
        radioPanel.add(radio1);
        radioPanel.add(radio2);
        constraints.gridx = 1;
        constraints.gridwidth = 3;
        panel.add(radioPanel, constraints);
    }

    private void addIDNumber() {
        JLabel lbl8 = new JLabel("ID Number");
        lbl8.setBorder(border);
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        panel.add(lbl8, constraints);
        JTextField txt8 = new JTextField();
        txt8.setPreferredSize(mediumField);
        constraints.gridx = 1;
        constraints.gridwidth = 3;
        panel.add(txt8, constraints);
    }

    private void addButtons() {
        JButton submitBtn = new JButton("Submit");
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(submitBtn, constraints);

        JButton cancelBtn = new JButton("Cancel");
        constraints.gridy = 1;
        panel.add(cancelBtn, constraints);

        JButton helpBtn = new JButton("Help");
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTH; // Anchor north
        panel.add(helpBtn, constraints);
    }


    public static void main(String[] args) {
        new GridBagLayoutDemo1(new JFrame());
    }
}
