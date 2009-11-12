package demo.manning.scrollpane;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Scrollable;

class MyScrollableLabel extends JLabel implements Scrollable {
    public MyScrollableLabel(ImageIcon i) {
        super(i);
    }

    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public int getScrollableBlockIncrement(Rectangle r, int orientation,
            int direction) {
        return 10;
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public int getScrollableUnitIncrement(Rectangle r, int orientation,
            int direction) {
        return 10;
    }
}
