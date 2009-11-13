package demo.manning.textcomponent;

//see \Chapter11\3

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import demo.manning.layout.CustomedLayout1;

public class BasicTextComponentDemo extends JFrame {
	protected JTextField m_firstTxt;
	protected JTextField m_lastTxt;
	protected JPasswordField m_passwordTxt;
	protected JTextArea m_commentsTxt;

	public BasicTextComponentDemo() {
		
		/*
		 * This is the structure of the GUI.
		 * bigPanel{
		 *     layout;
		 *     border;
		 *     upperPanel{
		 *         layout;
		 *         border;
		 *         label--textField;
		 *         label--textField;
		 *         label--passwordField;
		 *     }
		 *     lowerPanel{
		 *         layout;
		 *         border;
		 *         scrollPanel{
		 *             textArea;
		 *         }
		 *     }
		 * }
		 */
		super("Text Components Demo");
		
		Font monospaced = new Font("Monospaced", Font.PLAIN, 12);
		JPanel bigPanel = new JPanel(new BorderLayout());
		JPanel upperPanel = new JPanel(new CustomedLayout1());
		// p.setBorder(new JLabel("First name:"));

		upperPanel.add(new JLabel("First name:"));
		m_firstTxt = new JTextField(20);
		upperPanel.add(m_firstTxt);
		
		upperPanel.add(new JLabel("Last name:"));
		m_lastTxt = new JTextField(20);
		upperPanel.add(m_lastTxt);
		
		upperPanel.add(new JLabel("Login password:"));
		m_passwordTxt = new JPasswordField(20);
		m_passwordTxt.setFont(monospaced);
		upperPanel.add(m_passwordTxt);
		
		upperPanel.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(),
				"personal Data"), new EmptyBorder(1, 5, 3, 5)));
		bigPanel.add(upperPanel, BorderLayout.NORTH);
		
		m_commentsTxt = new JTextArea("", 4, 30);
		m_commentsTxt.setFont(monospaced);
		m_commentsTxt.setLineWrap(true);
		m_commentsTxt.setWrapStyleWord(true);
		JPanel lowerPanel = new JPanel(new BorderLayout());
		lowerPanel.add(new JScrollPane(m_commentsTxt));
		lowerPanel.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(),
				"comments"), new EmptyBorder(3, 5, 3, 5)));
		bigPanel.add(lowerPanel, BorderLayout.CENTER);
		
		bigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(bigPanel);
		pack();
	}

	public static void main(String[] args) {
		JFrame frame = new BasicTextComponentDemo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
