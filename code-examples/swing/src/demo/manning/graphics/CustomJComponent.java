package demo.manning.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

class CustomJComponent extends JComponent {
	private static Color m_tRed = new Color(255, 0, 0, 150);
	private static Color m_tGreen = new Color(0, 255, 0, 150);
	private static Color m_tBlue = new Color(0, 0, 255, 150);
	private static Font m_biFont = new Font("Monospaced", Font.BOLD
			| Font.ITALIC, 36);
	private static Font m_pFont = new Font("SansSerif", Font.PLAIN, 12);
	private static Font m_bFont = new Font("Serif", Font.BOLD, 24);
	private static ImageIcon m_flight = new ImageIcon("flight.gif");

	public CustomJComponent() {
		setDoubleBuffered(true);
		setOpaque(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Fill the entire component with white
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Filled yellow circle
		g.setColor(Color.yellow);
		g.fillOval(0, 0, 240, 240);
		
		// Filled magenta circle
		g.setColor(Color.magenta);
		g.fillOval(160, 160, 240, 240);
		
		// Paint the icon below the blue square
		int w = m_flight.getIconWidth();
		int h = m_flight.getIconHeight();
		m_flight.paintIcon(this, g, 280 - (w / 2), 120 - (h / 2));
		
		// Paint the icon below the red square
		m_flight.paintIcon(this, g, 120 - (w / 2), 280 - (h / 2));
		
		// Filled transparent red square
		g.setColor(m_tRed);
		g.fillRect(60, 220, 120, 120);
		
		// Filled transparent green circle
		g.setColor(m_tGreen);
		g.fillOval(140, 140, 120, 120);
		
		// Filled transparent blue square
		g.setColor(m_tBlue);
		g.fillRect(220, 60, 120, 120);
		g.setColor(Color.black);
		
		// Bold, Italic, 36-point "Swing"
		g.setFont(m_biFont);
		FontMetrics fm = g.getFontMetrics();
		w = fm.stringWidth("Swing");
		h = fm.getAscent();
		g.drawString("Swing", 120 - (w / 2), 120 + (h / 4));// Plain, 12-point
															// "is"
		g.setFont(m_pFont);
		fm = g.getFontMetrics();
		w = fm.stringWidth("is");
		h = fm.getAscent();
		g.drawString("is", 200 - (w / 2), 200 + (h / 4));
		
		// Bold, 24-point "powerful!!"
		g.setFont(m_bFont);
		fm = g.getFontMetrics();
		w = fm.stringWidth("powerful!!");
		h = fm.getAscent();
		g.drawString("powerful!!", 280 - (w / 2), 280 + (h / 4));
	}

	// Most layout managers need this information
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
