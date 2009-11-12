package demo.manning.button;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JApplet;
import javax.swing.JToolTip;
import javax.swing.Timer;

class CustomToolTipManager extends MouseMotionAdapter implements ActionListener {
    protected Timer m_timer;
    protected int m_lastX = -1;
    protected int m_lastY = -1;
    protected boolean m_moved = false;
    protected int m_counter = 0;
    public JToolTip m_toolTip = new JToolTip();

    CustomToolTipManager(JApplet parent) {
        parent.addMouseMotionListener(this);
        m_toolTip.setTipText(null);
        parent.getContentPane().add(m_toolTip);
        m_toolTip.setVisible(false);
        m_timer = new Timer(1000, this);
        m_timer.start();
    }

    public void mouseMoved(MouseEvent e) {
        m_moved = true;
        m_counter = -1;
        m_lastX = e.getX();
        m_lastY = e.getY();
        if (m_toolTip.isVisible()) {
            m_toolTip.setVisible(false);
            m_toolTip.getParent().repaint();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (m_moved || m_counter == 0 || m_toolTip.getTipText() == null) {
            if (m_toolTip.isVisible())
                m_toolTip.setVisible(false);
            m_moved = false;
            return;
        }
        if (m_counter < 0) {
            m_counter = 4;
            m_toolTip.setVisible(true);
            Dimension d = m_toolTip.getPreferredSize();
            m_toolTip.setBounds(m_lastX, m_lastY + 20, d.width, d.height);
        }
        m_counter--;
    }
}