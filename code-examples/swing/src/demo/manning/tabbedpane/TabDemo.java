package demo.tabbedpane;

//see \Chapter6\2

/**
 * In example 6.2 we show how to programmatically invoke or deny a 
 * tab switch. 
 * 
 * The first tab contains a hypothetical list of employees. The second 
 * tab contains input fields displaying, and allowing modification to, 
 * a specific employee’s personal data. 
 * 
 * When the application starts the first tab is selected. If no employee 
 * is selected from the list the second tab cannot be selected. If an 
 * employee is selected from the list the second tab is selectable.
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import demo.layout.*;

public class TabDemo extends JFrame {
    public static final int LIST_TAB = 0;
    public static final int DATA_TAB = 1;
    protected Person[] m_employees = { new Person("John", "Smith", "111-1111"),
            new Person("Silvia", "Glenn", "222-2222"),
            new Person("Captain", "Kirk", "333-3333"),
            new Person("Duke", "Nukem", "444-4444"),
            new Person("James", "Bond", "000-7777") };
    protected JList m_list;
    protected JTextField m_firstTxt;
    protected JTextField m_lastTxt;

    protected JTextField m_phoneTxt;
    protected JTabbedPane m_tab;

    public TabDemo() {
        super("Tab Validation Demo");
        JPanel p1 = new JPanel(new BorderLayout());
        p1.setBorder(new EmptyBorder(10, 10, 10, 10));
        m_list = new JList(m_employees);
        m_list.setVisibleRowCount(4);
        JScrollPane sp = new JScrollPane(m_list);
        p1.add(sp, BorderLayout.CENTER);
        MouseListener mlst = new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2)
                    m_tab.setSelectedIndex(DATA_TAB);
            }
        };
        m_list.addMouseListener(mlst);
        JPanel p2 = new JPanel(new CustomedLayout1());
        p2.setBorder(new EmptyBorder(10, 10, 10, 10));
        p2.add(new JLabel("First name:"));
        m_firstTxt = new JTextField(20);
        p2.add(m_firstTxt);
        p2.add(new JLabel("Last name:"));
        m_lastTxt = new JTextField(20);
        p2.add(m_lastTxt);
        p2.add(new JLabel("Contact phone:"));
        m_phoneTxt = new JTextField(20);
        ;
        p2.add(m_phoneTxt);
        m_tab = new JTabbedPane();
        m_tab.addTab("Employees", p1);
        m_tab.addTab("Personal Data", p2);
        m_tab.addChangeListener(new TabChangeListener());
        JPanel p = new JPanel();
        p.add(m_tab);
        p.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(p);
        pack();
    }

    public Person getSelectedPerson() {
        return (Person) m_list.getSelectedValue();
    }

    public static void main(String[] args) {
        JFrame frame = new TabDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class TabChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            Person sp = getSelectedPerson();
            switch (m_tab.getSelectedIndex()) {
            case DATA_TAB:
                if (sp == null) {
                    m_tab.setSelectedIndex(LIST_TAB);
                    return;
                }
                m_firstTxt.setText(sp.m_firstName);
                m_lastTxt.setText(sp.m_lastName);
                m_phoneTxt.setText(sp.m_phone);
                break;
            case LIST_TAB:
                if (sp != null) {
                    sp.m_firstName = m_firstTxt.getText();
                    sp.m_lastName = m_lastTxt.getText();
                    sp.m_phone = m_phoneTxt.getText();
                    m_list.repaint();
                }
                break;
            }
        }
    }
}