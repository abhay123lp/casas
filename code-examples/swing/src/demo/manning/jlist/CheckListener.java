package demo.manning.jlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CheckListener implements MouseListener, KeyListener {
	protected CheckBoxListDemo m_parent;
	protected JList m_list;

	public CheckListener(CheckBoxListDemo parent) {
		m_parent = parent;
		m_list = parent.m_list;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getX() < 20)
			doCheck();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == ' ')
			doCheck();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	protected void doCheck() {
		int index = m_list.getSelectedIndex();
		if (index < 0)
			return;
		InstallData data = (InstallData) m_list.getModel().getElementAt(index);
		data.invertSelected();
		m_list.repaint();
		m_parent.recalcTotal();
	}
}
