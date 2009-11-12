package demo.manning.button;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.border.*;

class TransparentButton extends JButton implements ActionListener {
	
	protected Border m_activeBorder;
	protected Border m_inactiveBorder;
	protected Applet m_parent;
	protected String m_text;
	protected String m_sUrl;
	protected URL m_url;

	public TransparentButton(Applet parent, String text, String sUrl) {
		m_parent = parent;
		setText(text);
		m_sUrl = sUrl;
		try {
			m_url = new URL(sUrl);
		} catch (Exception ex) {
			m_url = null;
		}
		setOpaque(false);
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		m_activeBorder = new MatteBorder(1, 1, 1, 1, Color.yellow);
		m_inactiveBorder = new EmptyBorder(1, 1, 1, 1);
		setBorder(m_inactiveBorder);
		addActionListener(this);
	}

	public void setText(String text) {
		m_text = text;
		setToolTipText(text);
	}

	public String getText() {
		return m_text;
	}

	protected void processMouseEvent(MouseEvent evt) {
		switch (evt.getID()) {
		case MouseEvent.MOUSE_ENTERED:
			setBorder(m_activeBorder);
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			m_parent.showStatus(m_sUrl);
			break;
		case MouseEvent.MOUSE_EXITED:
			setBorder(m_inactiveBorder);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			m_parent.showStatus("");
			break;
		}
		super.processMouseEvent(evt);
	}

	public void actionPerformed(ActionEvent e) {
		if (m_url != null) {
			AppletContext context = m_parent.getAppletContext();
			if (context != null)
				context.showDocument(m_url);
		}
	}

	public void paintComponent(Graphics g) {
		paintBorder(g);
	}
}
