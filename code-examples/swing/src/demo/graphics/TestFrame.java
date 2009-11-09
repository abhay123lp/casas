package demo.graphics;

import javax.swing.*;

class TestFrame extends JFrame {
	public TestFrame() {
		super("Graphics demo");
		getContentPane().add(new CustomJComponent());
	}

	public static void main(String args[]) {
		TestFrame mainFrame = new TestFrame();
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}


