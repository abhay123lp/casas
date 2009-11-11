package demo.button;

//see \Chapter5\5
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JToolTip;

class PolygonButton3 extends JComponent implements MouseListener,
        MouseMotionListener {
    static public Color ACTIVE_COLOR = Color.red;
    static public Color INACTIVE_COLOR = Color.darkGray;
    protected JApplet m_parent;

    protected String m_text;
    protected String m_sUrl;
    protected URL m_url;
    protected Polygon m_polygon;
    protected Rectangle m_rc;
    protected boolean m_active;
    protected static PolygonButton3 m_currentButton;
    public static JToolTip m_toolTip;

    public PolygonButton3(JApplet parent, Polygon p, String text, String sUrl) {
        m_parent = parent;
        m_polygon = p;
        setText(text);
        m_sUrl = sUrl;
        try {
            m_url = new URL(sUrl);
        } catch (Exception ex) {
            m_url = null;
        }
        setOpaque(false);
        m_parent.addMouseListener(this);
        m_parent.addMouseMotionListener(this);
        m_rc = new Rectangle(m_polygon.getBounds()); // Bug alert!
        m_rc.grow(1, 1);
        setBounds(m_rc);
        m_polygon.translate(-m_rc.x, -m_rc.y);
    }

    public void setText(String text) {
        m_text = text;
    }

    public String getText() {
        return m_text;
    }

    public void mouseMoved(MouseEvent e) {
        if (!m_rc.contains(e.getX(), e.getY()) || e.isConsumed()) {
            if (m_active)
                setState(false);
            return; // Quickly return, if outside our rectangle
        }
        int x = e.getX() - m_rc.x;
        int y = e.getY() - m_rc.y;
        boolean active = m_polygon.contains(x, y);
        if (m_active != active)
            setState(active);
        if (m_active)
            e.consume();
    }

    public void mouseDragged(MouseEvent e) {
    }

    protected void setState(boolean active) {
        m_active = active;
        repaint();

        if (m_active) {
            if (m_currentButton != null)
                m_currentButton.setState(false);
            m_currentButton = this;
            m_parent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            m_parent.showStatus(m_sUrl);
            if (m_toolTip != null)
                m_toolTip.setTipText(m_text);
        } else {
            m_currentButton = null;
            m_parent.setCursor(Cursor
                    .getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            m_parent.showStatus("");
            if (m_toolTip != null)
                m_toolTip.setTipText(null);
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (m_active && m_url != null && !e.isConsumed()) {
            AppletContext context = m_parent.getAppletContext();
            if (context != null)
                context.showDocument(m_url);
            e.consume();
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }

    public void paintComponent(Graphics g) {
        g.setColor(m_active ? ACTIVE_COLOR : INACTIVE_COLOR);
        g.drawPolygon(m_polygon);
    }
}