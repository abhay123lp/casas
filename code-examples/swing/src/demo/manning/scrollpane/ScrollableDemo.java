package demo.manning.scrollpane;

//see \Chapter7\3
import java.awt.*;
import javax.swing.*;

public class ScrollableDemo extends JFrame {
    public ScrollableDemo() {
        super("JScrollPane Demo");
        ImageIcon ii = new ImageIcon("earth.jpg");
        JScrollPane jsp = new JScrollPane(new MyScrollableLabel(ii));
        getContentPane().add(jsp);
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ScrollableDemo();
    }
}
