package demo.scrollpane;

//see \Chapter7\1

import javax.swing.*;

public class ScrollPaneDemo extends JFrame {
    public ScrollPaneDemo() {
        super("JScrollPane Demo");
        ImageIcon ii = new ImageIcon("earth.jpg");
        JScrollPane jsp = new JScrollPane(new JLabel(ii));
        getContentPane().add(jsp);
        setSize(300, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ScrollPaneDemo();
    }
}