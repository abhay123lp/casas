package demo.jlist;

//see \Chapter 10\4

/**
 * Example 10.4 shows how to create a list of check boxes that 
 * represent imaginary optional program constituents.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CheckBoxListDemo extends JFrame {
	protected JList m_list;
	protected JLabel m_total;

	public CheckBoxListDemo() {
		super("Swing List [Check boxes]");
		setSize(280, 250);
		getContentPane().setLayout(new FlowLayout());
		InstallData[] options = { new InstallData("Program executable", 118),
				new InstallData("Help files", 52),
				new InstallData("Tools and converters", 83),
				new InstallData("Source code", 133) };
		m_list = new JList(options);
		CheckListCellRenderer renderer = new CheckListCellRenderer();
		m_list.setCellRenderer(renderer);
		m_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CheckListener lst = new CheckListener(this);
		m_list.addMouseListener(lst);
		m_list.addKeyListener(lst);
		JScrollPane ps = new JScrollPane();
		ps.getViewport().add(m_list);
		m_total = new JLabel("Space required: 0K");
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(ps, BorderLayout.CENTER);
		p.add(m_total, BorderLayout.SOUTH);
		p.setBorder(new TitledBorder(new EtchedBorder(),
				"Please select options:"));
		getContentPane().add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		recalcTotal();
	}

	public void recalcTotal() {
		ListModel model = m_list.getModel();
		int total = 0;
		for (int k = 0; k < model.getSize(); k++) {
			InstallData data = (InstallData) model.getElementAt(k);
			if (data.isSelected())
				total += data.getSize();
		}
		m_total.setText("Space required: " + total + "K");
	}

	public static void main(String argv[]) {
		new CheckBoxListDemo();
	}
}