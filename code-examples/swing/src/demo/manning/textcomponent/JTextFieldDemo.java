package demo.manning.textcomponent;

//see \Chapter11\1

import javax.swing.*;
import java.awt.*;

public class JTextFieldDemo extends JFrame {
    public JTextFieldDemo() {
        super("JTextField Test");
        getContentPane().setLayout(new FlowLayout());
        int column = 2;
        JTextField textField1 = new JTextField("m", column++);
        JTextField textField2 = new JTextField("mm", column++);
        JTextField textField3 = new JTextField("mmm", column++);
        JTextField textField4 = new JTextField("mmmm", column++);
        JTextField textField5 = new JTextField("mmmmm", column++);
        JTextField textField6 = new JTextField("mmmmmm", column++);
        JTextField textField7 = new JTextField("mmmmmmm", column++);
        JTextField textField8 = new JTextField("mmmmmmmm", column++);
        JTextField textField9 = new JTextField("mmmmmmmmm", column++);
        JTextField textField10 = new JTextField("mmmmmmmmmm", column++);
        JTextField textField11 = new JTextField("mmmmmmmmmmm", column++);
        JTextField textField12 = new JTextField("mmmmmmmmmmmm", column++);
        JTextField textField13 = new JTextField("mmmmmmmmmmmmm", column++);
        JTextField textField14 = new JTextField("mmmmmmmmmmmmmm", column++);
        
        getContentPane().add(textField1);
        getContentPane().add(textField2);
        getContentPane().add(textField3);
        getContentPane().add(textField4);
        getContentPane().add(textField5);
        getContentPane().add(textField6);
        getContentPane().add(textField7);
        getContentPane().add(textField8);
        getContentPane().add(textField9);
        getContentPane().add(textField10);
        getContentPane().add(textField11);
        getContentPane().add(textField12);
        getContentPane().add(textField13);
        getContentPane().add(textField14);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 170);
        setVisible(true);
    }

    public static void main(String argv[]) {
        new JTextFieldDemo();
    }
}
