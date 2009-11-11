package demo.splitpane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

class SimplePanel extends JPanel {
    public Dimension getPreferredSize() {

        return new Dimension(200, 200);
    }

    public Dimension getMinimumSize() {
        return new Dimension(40, 40);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        Dimension sz = getSize();
        g.drawLine(0, 0, sz.width, sz.height);
        g.drawLine(sz.width, 0, 0, sz.height);
    }

}