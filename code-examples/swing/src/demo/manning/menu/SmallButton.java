package demo.manning.menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class SmallButton extends JButton implements MouseListener {
	protected Border m_raised = new SoftBevelBorder(BevelBorder.RAISED);
	protected Border m_lowered = new SoftBevelBorder(BevelBorder.LOWERED);
	protected Border m_inactive = new EmptyBorder(3, 3, 3, 3);
	protected Border m_border = m_inactive;
	protected Insets m_ins = new Insets(4, 4, 4, 4);

	public SmallButton(Action act, String tip) {
		super((Icon) act.getValue(Action.SMALL_ICON));
		setBorder(m_inactive);
		setMargin(m_ins);
		setToolTipText(tip);
		setRequestFocusEnabled(false);
		addActionListener(act);
		addMouseListener(this);
	}

	public float getAlignmentY() {
		return 0.5f;
	}

	public Border getBorder() {
		return m_border;
	}

	public Insets getInsets() {
		return m_ins;
	}

	public void mousePressed(MouseEvent e) {
		m_border = m_lowered;
		setBorder(m_lowered);
	}

	public void mouseReleased(MouseEvent e) {
		m_border = m_inactive;
		setBorder(m_inactive);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		m_border = m_raised;
		setBorder(m_raised);
	}

	public void mouseExited(MouseEvent e) {
		m_border = m_inactive;
		setBorder(m_inactive);
	}
}
