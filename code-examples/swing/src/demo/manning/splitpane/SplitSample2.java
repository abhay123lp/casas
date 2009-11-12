package demo.manning.splitpane;

//see\Chapter8\2

/**
 * example 8.2 shows how to synchronize the left and right pane so that 
 * whenever the left divider is moved the right divider moves to an 
 * identical location and vice versa.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SplitSample2 extends JFrame {
    private boolean m_resizing = false;

    public SplitSample2() {

        super("SplitSample2 With Synchronization");
        setSize(400, 400);
        getContentPane().setLayout(new BorderLayout());
        Component c11 = new SimplePanel();
        Component c12 = new SimplePanel();
        final JSplitPane spLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                c11, c12);
        spLeft.setDividerSize(8);
        spLeft.setDividerLocation(150);
        spLeft.setContinuousLayout(true);
        Component c21 = new SimplePanel();
        Component c22 = new SimplePanel();
        final JSplitPane spRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                c21, c22);
        spRight.setDividerSize(8);
        spRight.setDividerLocation(150);
        spRight.setContinuousLayout(true);
        ComponentListener caLeft = new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if (!m_resizing) {
                    m_resizing = true;
                    spRight.setDividerLocation(spLeft.getDividerLocation());
                    m_resizing = false;
                }
            }
        };
        c11.addComponentListener(caLeft);
        ComponentListener caRight = new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if (!m_resizing) {
                    m_resizing = true;
                    spLeft.setDividerLocation(spRight.getDividerLocation());
                    m_resizing = false;
                }
            }
        };
        c21.addComponentListener(caRight);
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, spLeft,
                spRight);
        sp.setDividerSize(8);
        sp.setDividerLocation(200);
        sp.setResizeWeight(0.5);
        sp.setContinuousLayout(false);
        sp.setOneTouchExpandable(true);
        getContentPane().add(sp, BorderLayout.CENTER);
    }

    public static void main(String argv[]) {
        SplitSample2 frame = new SplitSample2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}