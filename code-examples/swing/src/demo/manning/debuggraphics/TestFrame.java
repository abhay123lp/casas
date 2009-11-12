package demo.debuggraphics;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import java.io.*;

class TestFrame extends JFrame {
	public TestFrame() {
		super("Graphics demo");
		CustomJComponent jc = new CustomJComponent();
		
		RepaintManager.currentManager(jc).setDoubleBufferingEnabled(false);
		
		jc.setDebugGraphicsOptions(DebugGraphics.LOG_OPTION
				| DebugGraphics.FLASH_OPTION);
		DebugGraphics.setFlashTime(100);
		DebugGraphics.setFlashCount(2);
		DebugGraphics.setFlashColor(new Color(0, 0, 0, 0));
		getContentPane().add(jc);
	}

	public static void main(String args[]) {
		TestFrame mainFrame = new TestFrame();
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}
