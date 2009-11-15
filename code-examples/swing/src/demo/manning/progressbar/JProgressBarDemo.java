package demo.manning.progressbar;

//see \Chapter13\1

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JProgressBarDemo extends JFrame {
	protected int m_min = 0;
	protected int m_max = 100;
	protected int m_counter = 0;
	protected JProgressBar m_progress;
	protected JButton m_start;
	protected boolean m_stringPainted = false;

	public JProgressBarDemo() {
		super("JProgressBar Demo");
		setSize(300, 50);
		UIManager.put("ProgressBar.selectionBackground", Color.black);
		UIManager.put("ProgressBar.selectionForeground", Color.white);
		UIManager.put("ProgressBar.foreground", new Color(8, 32, 128));
		UIManager.put("ProgressBar.cellLength", new Integer(5));
		UIManager.put("ProgressBar.cellSpacing", new Integer(1));
		
		
		m_progress = new JProgressBar();
		m_progress.setMinimum(m_min);
		m_progress.setMaximum(m_max);
		m_progress.setStringPainted(m_stringPainted);
		
		
		m_start = new JButton("Start");
		
		ActionListener lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_stringPainted = !m_stringPainted;
				m_progress.setStringPainted(m_stringPainted);
				Thread runner = new Thread() {
					int m_counter;

					public void run() {
						m_start.setEnabled(false);
						// Pretend we're doing phase 1
						m_progress.setIndeterminate(true);
						try {
							Thread.sleep(5000);
						} catch (InterruptedException ex) {
						}
						m_progress.setIndeterminate(false);
						// Pretend we're doing phase 2
						for (m_counter = m_min; m_counter <= m_max; m_counter++) {
							Runnable runme = new Runnable() {
								public void run() {
									m_progress.setValue(m_counter);
								}
							};
							SwingUtilities.invokeLater(runme);
							try {
								Thread.sleep(100);
							}

							catch (InterruptedException ex) {
							}
						}
						m_start.setEnabled(true);
					}
				};
				runner.start();
			}
		};
		m_start.addActionListener(lst);
		getContentPane().setLayout(new BorderLayout(10, 10));
		getContentPane().add(m_progress, BorderLayout.CENTER);
		getContentPane().add(m_start, BorderLayout.WEST);
	}

	public static void main(String[] args) {
		JProgressBarDemo frame = new JProgressBarDemo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
