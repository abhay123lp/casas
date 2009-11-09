package demo.debuggraphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

public class EmptyUI extends ComponentUI {
	private static final EmptyUI sharedInstance = new EmptyUI();

	public static ComponentUI createUI(JComponent c) {
		return sharedInstance;
	}
}
