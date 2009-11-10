package demo.button;

//see \Chapter5\4

import java.awt.Dimension;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ButtonApplet extends JApplet {
	public ButtonApplet() {
	}

	public synchronized void init() {
		String imageName = getParameter("image");
		if (imageName == null) {
			System.err.println("Need \"image\" parameter");
			return;
		}
		URL imageUrl = null;
		try {
			imageUrl = new URL(getDocumentBase(), imageName);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			return;
		}
		ImageIcon bigImage = new ImageIcon(imageUrl);
		JLabel bigLabel = new JLabel(bigImage);
		bigLabel.setLayout(null);
		
		
		int index = 1;
		int[] q = new int[4];
		while (true) {
			String paramSize = getParameter("button" + index);
			String paramName = getParameter("name" + index);
			String paramUrl = getParameter("url" + index);
			if (paramSize == null || paramName == null || paramUrl == null)
				break;
			try {
				StringTokenizer tokenizer = new StringTokenizer(paramSize, ",");
				for (int k = 0; k < 4; k++) {
					String str = tokenizer.nextToken().trim();
					q[k] = Integer.parseInt(str);
				}
			} catch (Exception ex) {
				break;
			}
			NavigateButton btn = new NavigateButton(this, paramName, paramUrl);
			bigLabel.add(btn);
			btn.setBounds(q[0], q[1], q[2], q[3]);
			index++;
		}

		getContentPane().setLayout(null);
		getContentPane().add(bigLabel);
		bigLabel.setBounds(0, 0, bigImage.getIconWidth(), bigImage
				.getIconHeight());
		setSize(new Dimension( bigImage.getIconWidth(), bigImage
				.getIconHeight()));
	}

	public String getAppletInfo() {
		return "Sample applet with NavigateButtons";
	}

	public String[][] getParameterInfo() {
		String pinfo[][] = { { "image", "string", "base image file name" },
				{ "buttonX", "x,y,w,h", "button's bounds" },
				{ "nameX", "string", "tooltip text" },
				{ "urlX", "url", "link URL" } };
		return pinfo;
	}
}

