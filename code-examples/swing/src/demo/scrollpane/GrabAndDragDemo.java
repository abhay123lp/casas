package demo.scrollpane;

//see \Chapter7\4
import javax.swing.*;

public class GrabAndDragDemo extends JFrame {
    public GrabAndDragDemo() {
        super("Grab-and-drag Demo");
        ImageIcon ii = new ImageIcon("earth.jpg");
        JScrollPane jsp = new JScrollPane(new GrabAndScrollLabel(ii));
        getContentPane().add(jsp);
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GrabAndDragDemo();
    }
}