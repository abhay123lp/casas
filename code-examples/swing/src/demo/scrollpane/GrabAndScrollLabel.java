package demo.scrollpane;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.*;

class GrabAndScrollLabel extends JLabel {
    public GrabAndScrollLabel(ImageIcon i) {
        super(i);
        MouseInputAdapter mia = new MouseInputAdapter() {
            int m_XDifference, m_YDifference;
            Container c;

            public void mouseDragged(MouseEvent e) {
                c = GrabAndScrollLabel.this.getParent();
                if (c instanceof JViewport) {
                    JViewport jv = (JViewport) c;
                    Point p = jv.getViewPosition();

                    int newX = p.x - (e.getX() - m_XDifference);
                    int newY = p.y - (e.getY() - m_YDifference);
                    int maxX = GrabAndScrollLabel.this.getWidth()
                            - jv.getWidth();
                    int maxY = GrabAndScrollLabel.this.getHeight()
                            - jv.getHeight();
                    if (newX < 0)    newX = 0;
                    if (newX > maxX) newX = maxY;
                    if (newY < 0)    newY = 0;
                    if (newY > maxY) newY = maxY;
                    jv.setViewPosition(new Point(maxX, maxY));
                }
            }

            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                m_XDifference = e.getX();
                m_YDifference = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        };
        addMouseMotionListener(mia);
        addMouseListener(mia);
    }
}
