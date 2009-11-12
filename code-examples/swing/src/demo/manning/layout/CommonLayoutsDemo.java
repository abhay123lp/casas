package demo.manning.layout;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class CommonLayoutsDemo extends JFrame {
    public Integer LAYOUT_FRAME_LAYER = new Integer(1);
    JDesktopPane desktop;
    
    public CommonLayoutsDemo() {
        super("Common Layout Managers");
        setSize(500, 460);
        desktop = new JDesktopPane();
        getContentPane().add(desktop);
        
        showFlowLayout();
        showGridLayout();
        showBorderLayout();
        showBoxLayoutX();
        showBoxLayoutY();
        showSpringLayout();
        
    }
    public void showFlowLayout(){
        JInternalFrame fr1 = new JInternalFrame("FlowLayout", true, true);
        fr1.setBounds(10, 10, 150, 150);
        Container c = fr1.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(new JButton("1"));
        c.add(new JButton("2"));
        c.add(new JButton("3"));
        c.add(new JButton("4"));
        desktop.add(fr1, 0);
        fr1.show();
        
    }

    public void showGridLayout(){
        JInternalFrame fr2 = new JInternalFrame("GridLayout", true, true);
        fr2.setBounds(170, 10, 150, 150);
        Container c = fr2.getContentPane();
        c.setLayout(new GridLayout(2, 2));
        c.add(new JButton("1"));
        c.add(new JButton("2"));
        c.add(new JButton("3"));
        c.add(new JButton("4"));
        desktop.add(fr2, 0);
        fr2.show();
        
    }
    
    public void showBorderLayout(){
        JInternalFrame fr3 = new JInternalFrame("BorderLayout", true, true);
        fr3.setBounds(330, 10, 150, 150);
        Container c = fr3.getContentPane();
        c.add(new JButton("1"), BorderLayout.NORTH);
        c.add(new JButton("2"), BorderLayout.EAST);
        c.add(new JButton("3"), BorderLayout.SOUTH);
        c.add(new JButton("4"), BorderLayout.WEST);
        desktop.add(fr3, 0);
        fr3.show();
    }
    
    public void showBoxLayoutX(){
        JInternalFrame fr4 = new JInternalFrame("BoxLayout - X", true, true);
        fr4.setBounds(10, 170, 250, 80);
        Container c = fr4.getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
        c.add(new JButton("1"));
        c.add(Box.createHorizontalStrut(12));
        c.add(new JButton("2"));
        c.add(Box.createGlue());
        c.add(new JButton("3"));
        c.add(Box.createHorizontalGlue());
        c.add(new JButton("4"));
        desktop.add(fr4, 0);
        fr4.show();
    }

    public void showBoxLayoutY(){
        JInternalFrame fr5 = new JInternalFrame("BoxLayout - Y", true, true);
        fr5.setBounds(330, 170, 150, 200);
        Container c = fr5.getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.add(new JButton("1"));
        c.add(Box.createVerticalStrut(10));
        c.add(new JButton("2"));
        c.add(Box.createGlue());
        c.add(new JButton("3"));
        c.add(Box.createVerticalGlue());
        c.add(new JButton("4"));
        desktop.add(fr5, 0);
        fr5.show();
    }
    
    public void showSpringLayout(){
        JInternalFrame fr6 = new JInternalFrame("SpringLayout", true, true);
        fr6.setBounds(10, 260, 250, 170);
        Container c = fr6.getContentPane();
        c.setLayout(new SpringLayout());
        c.add(new JButton("1"), new SpringLayout.Constraints(Spring
                .constant(10), Spring.constant(10), Spring.constant(120),
                Spring.constant(70)));
        c.add(new JButton("2"), new SpringLayout.Constraints(Spring
                .constant(160), Spring.constant(10), Spring.constant(70),
                Spring.constant(30)));
        c.add(new JButton("3"), new SpringLayout.Constraints(Spring
                .constant(160), Spring.constant(50), Spring.constant(70),
                Spring.constant(30)));

        c.add(new JButton("4"), new SpringLayout.Constraints(Spring
                .constant(10), Spring.constant(90), Spring.constant(50), Spring
                .constant(40)));
        c.add(new JButton("5"), new SpringLayout.Constraints(Spring
                .constant(120), Spring.constant(90), Spring.constant(50),
                Spring.constant(40)));
        desktop.add(fr6, 0);
        fr6.show();
        desktop.setSelectedFrame(fr6);
    }

    public static void main(String argv[]) {
        CommonLayoutsDemo frame = new CommonLayoutsDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
