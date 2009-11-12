package demo.tabbedpane;

//see \Chapter6\1

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class TabbedPaneDemo1 extends JApplet implements ActionListener {
    private ImageIcon m_tabimage;
    private ImageIcon m_utsguy;
    private ImageIcon m_jfcgirl;
    private ImageIcon m_sbeguy;
    private ImageIcon m_tiger;
    private JTabbedPane m_tabbedPane;

    private JRadioButton m_topButton;
    private JRadioButton m_bottomButton;
    private JRadioButton m_leftButton;
    private JRadioButton m_rightButton;
    private JRadioButton m_wrapButton;
    private JRadioButton m_scrollButton;
    private JButton m_addButton;
    private JButton m_removeButton;
    private JLabel m_status;
    private JLabel m_loading;
    private AudioClip m_layoutsound;
    private AudioClip m_tabsound;

    public void init() {
        m_loading = new JLabel("Initializing applet...", SwingConstants.CENTER);
        getContentPane().add(m_loading);
        Thread initialize = new Thread() {
            public void run() {
                try {
                    m_tabimage = new ImageIcon(getClass().getResource(
                            "ball.gif"));
                    m_utsguy = new ImageIcon(getClass().getResource(
                            "utsguy.gif"));
                    m_jfcgirl = new ImageIcon(getClass().getResource(
                            "jfcgirl.gif"));

                    m_sbeguy = new ImageIcon(getClass().getResource(
                            "sbeguy.gif"));
                    m_tiger = new ImageIcon(getClass().getResource("tiger.gif"));

                    m_tabbedPane = new JTabbedPane(SwingConstants.TOP);
                    m_topButton = new JRadioButton("TOP");
                    m_bottomButton = new JRadioButton("BOTTOM");
                    m_leftButton = new JRadioButton("LEFT");
                    m_rightButton = new JRadioButton("RIGHT");
                    m_addButton = new JButton("Add");
                    m_removeButton = new JButton("Remove");
                    m_wrapButton = new JRadioButton("WRAP TABS");
                    m_scrollButton = new JRadioButton("SCROLL TABS");
                    m_topButton.setSelected(true);
                    ButtonGroup bgAlignment = new ButtonGroup();
                    bgAlignment.add(m_topButton);
                    bgAlignment.add(m_bottomButton);
                    bgAlignment.add(m_leftButton);
                    bgAlignment.add(m_rightButton);
                    m_wrapButton.setSelected(true);
                    ButtonGroup bgScrollMode = new ButtonGroup();
                    bgScrollMode.add(m_wrapButton);
                    bgScrollMode.add(m_scrollButton);

                    m_topButton.addActionListener(TabbedPaneDemo1.this);
                    m_bottomButton.addActionListener(TabbedPaneDemo1.this);
                    m_leftButton.addActionListener(TabbedPaneDemo1.this);
                    m_rightButton.addActionListener(TabbedPaneDemo1.this);
                    m_addButton.addActionListener(TabbedPaneDemo1.this);
                    m_removeButton.addActionListener(TabbedPaneDemo1.this);
                    m_wrapButton.addActionListener(TabbedPaneDemo1.this);
                    m_scrollButton.addActionListener(TabbedPaneDemo1.this);
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(new GridLayout(2, 4));
                    buttonPanel.add(m_topButton);
                    buttonPanel.add(m_bottomButton);
                    buttonPanel.add(m_leftButton);
                    buttonPanel.add(m_rightButton);
                    buttonPanel.add(m_wrapButton);
                    buttonPanel.add(m_scrollButton);
                    buttonPanel.add(m_addButton);
                    buttonPanel.add(m_removeButton);
                    m_status = new JLabel();
                    m_status.setForeground(Color.black);
                    m_status
                            .setBorder(new CompoundBorder(new EmptyBorder(2, 5,
                                    2, 5), new SoftBevelBorder(
                                    SoftBevelBorder.LOWERED)));
                    JPanel lowerPanel = new JPanel();
                    lowerPanel.setLayout(new BorderLayout());

                    lowerPanel.add(buttonPanel, BorderLayout.CENTER);
                    lowerPanel.add(m_status, BorderLayout.SOUTH);
                    for (int i = 0; i < 20; i++) {
                        createTab();
                    }
                    getContentPane().removeAll();
                    getContentPane().setLayout(new BorderLayout());
                    getContentPane().add(m_tabbedPane, BorderLayout.CENTER);
                    getContentPane().add(lowerPanel, BorderLayout.SOUTH);
                    m_tabbedPane.addChangeListener(new TabChangeListener());
                    m_layoutsound = getAudioClip(getCodeBase(), "switch.wav");
                    m_tabsound = getAudioClip(getCodeBase(), "tab.wav");
                    getContentPane().remove(m_loading);
                    getRootPane().revalidate();
                    getRootPane().repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        initialize.start();
    }

    public void createTab() {
        JLabel label = null;
        switch (m_tabbedPane.getTabCount() % 4) {
        case 0:
            label = new JLabel("Tab #" + m_tabbedPane.getTabCount(), m_utsguy,
                    SwingConstants.CENTER);
            break;
        case 1:
            label = new JLabel("Tab #" + m_tabbedPane.getTabCount(), m_jfcgirl,
                    SwingConstants.CENTER);
            break;
        case 2:
            label = new JLabel("Tab #" + m_tabbedPane.getTabCount(), m_sbeguy,
                    SwingConstants.CENTER);
            break;
        case 3:
            label = new JLabel("Tab #" + m_tabbedPane.getTabCount(), m_tiger,
                    SwingConstants.CENTER);
            break;
        }
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.white);
        m_tabbedPane.addTab("Tab #" + m_tabbedPane.getTabCount(), m_tabimage,
                label);

        m_tabbedPane.setSelectedIndex(m_tabbedPane.getTabCount() - 1);
        setStatus(m_tabbedPane.getSelectedIndex());
    }

    public void killTab() {
        if (m_tabbedPane.getTabCount() > 0) {
            m_tabbedPane.removeTabAt(m_tabbedPane.getTabCount() - 1);
            setStatus(m_tabbedPane.getSelectedIndex());
        } else
            setStatus(-1);
    }

    public void setStatus(int index) {
        if (index > -1)
            m_status.setText(" Selected Tab: " + index);
        else
            m_status.setText(" No Tab Selected");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == m_topButton) {
            m_tabbedPane.setTabPlacement(SwingConstants.TOP);
            m_layoutsound.play();
        } else if (e.getSource() == m_bottomButton) {
            m_tabbedPane.setTabPlacement(SwingConstants.BOTTOM);
            m_layoutsound.play();
        } else if (e.getSource() == m_leftButton) {
            m_tabbedPane.setTabPlacement(SwingConstants.LEFT);
            m_layoutsound.play();
        } else if (e.getSource() == m_rightButton) {
            m_tabbedPane.setTabPlacement(SwingConstants.RIGHT);
            m_layoutsound.play();
        } else if (e.getSource() == m_wrapButton) {
            m_tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
            m_layoutsound.play();
        } else if (e.getSource() == m_scrollButton) {
            m_tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            m_layoutsound.play();
        } else if (e.getSource() == m_addButton)
            createTab();
        else if (e.getSource() == m_removeButton)
            killTab();
        m_tabbedPane.revalidate();

        m_tabbedPane.repaint();
    }

    class TabChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            setStatus(((JTabbedPane) e.getSource()).getSelectedIndex());
            m_tabsound.play();
        }
    }
}