package demo.button;

//see \Chapter5\2

import java.awt.*;
import javax.swing.*;

class ToggleButtonDemo extends JFrame {
	
	public ToggleButtonDemo() {
		super("ToggleButton Demo");
		getContentPane().setLayout(new FlowLayout());
		ButtonGroup buttonGroup = new ButtonGroup();
		char ch = (char) ('1');
		for (int k = 0; k < 4; k++) {
			JToggleButton button = new JToggleButton("Button" + ch, k == 0);
			button.setMnemonic(ch);
			button.setEnabled(k < 3);
			button.setToolTipText("This is button" + ch);
			button.setIcon(new ImageIcon("ball_bw.gif"));
			button.setSelectedIcon(new ImageIcon("ball_red.gif"));
			button.setRolloverIcon(new ImageIcon("ball_blue.gif"));
			button.setRolloverSelectedIcon(new ImageIcon("ball_blue.gif"));
			getContentPane().add(button);
			buttonGroup.add(button);
		}
		pack();
	}

	public static void main(String args[]) {
		ToggleButtonDemo frame = new ToggleButtonDemo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}