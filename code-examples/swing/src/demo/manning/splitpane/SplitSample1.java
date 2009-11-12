package demo.splitpane;

//see \Chapter8\1

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SplitSample1 extends JFrame {
    public SplitSample1() {
        super("Simple SplitSample1 Example");
        setSize(400, 400);
        Component c11 = new SimplePanel();
        Component c12 = new SimplePanel();
        JSplitPane spLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, c11, c12);
        spLeft.setDividerSize(8);
        spLeft.setDividerLocation(150);
        spLeft.setContinuousLayout(true);
        Component c21 = new SimplePanel();
        Component c22 = new SimplePanel();
        JSplitPane spRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, c21, c22);
        spRight.setDividerSize(8);
        spRight.setDividerLocation(150);
        spRight.setContinuousLayout(true);
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, spLeft,
                spRight);
        sp.setDividerSize(8);
        sp.setDividerLocation(200);
        sp.setResizeWeight(0.5);
        sp.setContinuousLayout(false);
        sp.setOneTouchExpandable(true);
        getContentPane().add(sp, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String argv[]) {
        new SplitSample1();
    }
}