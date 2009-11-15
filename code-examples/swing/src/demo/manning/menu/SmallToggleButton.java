package demo.manning.menu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

class SmallToggleButton extends JToggleButton implements ItemListener {
	protected Border m_raised = new SoftBevelBorder(BevelBorder.RAISED);
	protected Border m_lowered = new SoftBevelBorder(BevelBorder.LOWERED);
	protected Insets m_ins = new Insets(4, 4, 4, 4);

	public SmallToggleButton(boolean selected, ImageIcon imgUnselected,
			ImageIcon imgSelected, String tip) {
		super(imgUnselected, selected);
		setHorizontalAlignment(CENTER);
		setBorder(selected ? m_lowered : m_raised);
		setMargin(m_ins);
		setToolTipText(tip);
		setRequestFocusEnabled(false);
		setSelectedIcon(imgSelected);
		addItemListener(this);
	}

	public float getAlignmentY() {
		return 0.5f;
	}

	public Insets getInsets() {
		return m_ins;
	}

	public Border getBorder() {
		return (isSelected() ? m_lowered : m_raised);
	}

	public void itemStateChanged(ItemEvent e) {
		setBorder(isSelected() ? m_lowered : m_raised);
	}
}
