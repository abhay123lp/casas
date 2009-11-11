package demo.button;

//see \Chapter5\3
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HtmlButtonDemo extends JFrame {
	public HtmlButtonDemo() {
		super("HTML Buttons and Labels");
		setSize(400, 300);
		getContentPane().setLayout(new FlowLayout());
		String htmlText = "<html><p><font color=\"#800080\" "
				+ "size=\"4\" face=\"Verdana\">JButton</font> </p>"
				+ "<address><font size=\"2\"><em>"
				+ "with HTML text</em></font>" + "</address>";
		JButton btn = new JButton(htmlText);
		getContentPane().add(btn);
		htmlText = "<html><p><font color=\"#800080\" "
				+ "size=\"4\" face=\"Verdana\">JLabel</font> </p>"
				+ "<address><font size=\"2\"><em>"
				+ "with HTML text</em></font>" + "</address>";
		JLabel lbl = new JLabel(htmlText);
		getContentPane().add(lbl);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String args[]) {
		new HtmlButtonDemo();
	}
}