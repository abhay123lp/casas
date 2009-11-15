package demo.manning.menu;

//see \Chapter12\2

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class BasicTextEditor extends JFrame {

	public static final String APP_NAME = "Basic Text Editor";
	public static final String FONTS[] = { "Serif", "SansSerif", "Courier" };
	protected Font m_fonts[];
	protected JTextArea m_editor;
	protected JMenuItem[] m_fontMenus;
	protected JCheckBoxMenuItem m_bold;
	protected JCheckBoxMenuItem m_italic;
	protected JFileChooser m_chooser;
	protected File m_currentFile;
	protected boolean m_textChanged = false;
	protected JToolBar m_toolBar;

	protected JComboBox m_cbFonts;
	protected SmallToggleButton m_bBold;
	protected SmallToggleButton m_bItalic;

	public BasicTextEditor() {
		super(APP_NAME + ": Part I - Menus");
		setSize(450, 350);

		/*
		 * MenuBar{ }
		 * 
		 * contentPane{ ps: ScrollPane{ m_editor: JTextArea; } }
		 */
		m_fonts = new Font[FONTS.length];
		for (int k = 0; k < FONTS.length; k++)
			m_fonts[k] = new Font(FONTS[k], Font.PLAIN, 12);

		m_editor = new JTextArea();
		JScrollPane ps = new JScrollPane(m_editor);
		getContentPane().add(ps, BorderLayout.CENTER);
		JMenuBar menuBar = createMenuBar();
		setJMenuBar(menuBar);

		m_chooser = new JFileChooser();
		try {
			File dir = (new File(".")).getCanonicalFile();
			m_chooser.setCurrentDirectory(dir);
		} catch (IOException ex) {
		}
		updateEditor();
		newDocument();
		WindowListener wndCloser = new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				if (!promptToSave())
					return;
				System.exit(0);
			}
		};
		addWindowListener(wndCloser);
	}

	protected JMenuBar createMenuBar() {
		/*
		 * MenuBar{ mFile: JMenu("File"){ item: JMenuItem("New"); item:
		 * JMenuItem("Open..."); item: JMenuItem("Save"); item: JMenuItem("Save
		 * As..."); item: JMenuItem("Exit"); } mFont: JMenu("Font"){ m_fontMenu:
		 * JRadioButtonMenuItem(FONTS[k]); m_bold: JCheckBoxMenuItem("Bold");
		 * m_italic: JCheckBoxMenuItem("Italic"); } }
		 */
		final JMenuBar menuBar = new JMenuBar();

		JMenu mFile = new JMenu("File");
		mFile.setMnemonic('f');

		ImageIcon iconNew = new ImageIcon("New16.gif");
		Action actionNew = new AbstractAction("New", iconNew) {
			public void actionPerformed(ActionEvent e) {
				if (!promptToSave())
					return;
				newDocument();
			}
		};

		JMenuItem item = new JMenuItem(actionNew);
		item.setMnemonic('n');
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mFile.add(item);

		ImageIcon iconOpen = new ImageIcon("Open16.gif");
		Action actionOpen = new AbstractAction("Open...", iconOpen) {
			public void actionPerformed(ActionEvent e) {
				if (!promptToSave())
					return;
				openDocument();
			}
		};
		item = new JMenuItem(actionOpen);
		item.setMnemonic('o');
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mFile.add(item);

		ImageIcon iconSave = new ImageIcon("Save16.gif");
		Action actionSave = new AbstractAction("Save", iconSave) {
			public void actionPerformed(ActionEvent e) {
				if (!m_textChanged)
					return;
				saveFile(false);
			}
		};
		item.setMnemonic('s');
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mFile.add(item);

		ImageIcon iconSaveAs = new ImageIcon("SaveAs16.gif");
		Action actionSaveAs = new AbstractAction("Save As...", iconSaveAs) {
			public void actionPerformed(ActionEvent e) {
				saveFile(true);
			}
		};
		item = new JMenuItem(actionSaveAs);
		item.setMnemonic('a');
		mFile.add(item);

		mFile.addSeparator();

		Action actionExit = new AbstractAction("Exit") {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		item = mFile.add(actionExit);
		item.setMnemonic('x');
		mFile.add(item);

		menuBar.add(mFile);

		m_toolBar = new JToolBar("Commands");
		// JButton btn1 = m_toolBar.add(actionNew);
		// btn1.setToolTipText("New text");
		// JButton btn2 = m_toolBar.add(actionOpen);
		// btn2.setToolTipText("Open text file");
		// JButton btn3 = m_toolBar.add(actionSave);
		// btn3.setToolTipText("Save text file");

		JButton bNew = new SmallButton(actionNew, "New text");
		m_toolBar.add(bNew);
		JButton bOpen = new SmallButton(actionOpen, "Open text file");
		m_toolBar.add(bOpen);
		JButton bSave = new SmallButton(actionSave, "Save text file");
		m_toolBar.add(bSave);

		ActionListener fontListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateEditor();
			}
		};

		JMenu mFont = new JMenu("Font");
		mFont.setMnemonic('o');
		ButtonGroup group = new ButtonGroup();
		m_fontMenus = new JMenuItem[FONTS.length];
		for (int k = 0; k < FONTS.length; k++) {
			int m = k + 1;
			m_fontMenus[k] = new JRadioButtonMenuItem(m + " " + FONTS[k]);
			m_fontMenus[k].setSelected(k == 0);
			m_fontMenus[k].setMnemonic('1' + k);
			m_fontMenus[k].setFont(m_fonts[k]);
			m_fontMenus[k].addActionListener(fontListener);
			group.add(m_fontMenus[k]);
			mFont.add(m_fontMenus[k]);
		}
		mFont.addSeparator();

		m_toolBar.addSeparator();
		m_cbFonts = new JComboBox(FONTS);
		m_cbFonts.setMaximumSize(m_cbFonts.getPreferredSize());
		m_cbFonts.setToolTipText("Available fonts");
		ActionListener lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = m_cbFonts.getSelectedIndex();
				if (index < 0)
					return;
				m_fontMenus[index].setSelected(true);
				updateEditor();
			}
		};

		m_cbFonts.addActionListener(lst);
		m_toolBar.add(m_cbFonts);
		m_bold = new JCheckBoxMenuItem("Bold");
		m_bold.setMnemonic('b');
		Font fn = m_fonts[1].deriveFont(Font.BOLD);
		m_bold.setFont(fn);
		m_bold.setSelected(false);
		m_bold.addActionListener(fontListener);
		mFont.add(m_bold);

		m_italic = new JCheckBoxMenuItem("Italic");
		m_italic.setMnemonic('i');
		fn = m_fonts[1].deriveFont(Font.ITALIC);
		m_italic.setFont(fn);
		m_italic.setSelected(false);
		m_italic.addActionListener(fontListener);
		mFont.add(m_italic);

		menuBar.add(mFont);

		m_toolBar.addSeparator();
		ImageIcon img1 = new ImageIcon("Bold16.gif");
		m_bBold = new SmallToggleButton(false, img1, img1, "Bold font");
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_bold.setSelected(m_bBold.isSelected());
				updateEditor();
			}
		};
		m_bBold.addActionListener(lst);
		m_toolBar.add(m_bBold);
		img1 = new ImageIcon("Italic16.gif");
		m_bItalic = new SmallToggleButton(false, img1, img1, "Italic font");
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_italic.setSelected(m_bItalic.isSelected());
				updateEditor();
			}
		};
		m_bItalic.addActionListener(lst);
		m_toolBar.add(m_bItalic);
		getContentPane().add(m_toolBar, BorderLayout.NORTH);

		return menuBar;
	}

	protected String getDocumentName() {
		return m_currentFile == null ? "Untitled" : m_currentFile.getName();
	}

	protected void newDocument() {
		m_editor.setText("");
		m_currentFile = null;
		setTitle(APP_NAME + " [" + getDocumentName() + "]");
		m_textChanged = false;
		m_editor.getDocument().addDocumentListener(new UpdateListener());
	}

	protected void openDocument() {
		if (m_chooser.showOpenDialog(BasicTextEditor.this) != JFileChooser.APPROVE_OPTION)
			return;
		File f = m_chooser.getSelectedFile();
		if (f == null || !f.isFile())
			return;
		m_currentFile = f;
		try {
			FileReader in = new FileReader(m_currentFile);
			m_editor.read(in, null);
			in.close();
			setTitle(APP_NAME + " [" + getDocumentName() + "]");
		} catch (IOException ex) {
			showError(ex, "Error reading file " + m_currentFile);
		}
		m_textChanged = false;
		m_editor.getDocument().addDocumentListener(new UpdateListener());
	}

	protected boolean saveFile(boolean saveAs) {
		if (saveAs || m_currentFile == null) {
			if (m_chooser.showSaveDialog(BasicTextEditor.this) != JFileChooser.APPROVE_OPTION)
				return false;
			File f = m_chooser.getSelectedFile();
			if (f == null)
				return false;
			m_currentFile = f;
			setTitle(APP_NAME + " [" + getDocumentName() + "]");
		}
		try {
			FileWriter out = new FileWriter(m_currentFile);
			m_editor.write(out);
			out.close();
		} catch (IOException ex) {
			showError(ex, "Error saving file " + m_currentFile);
			return false;
		}
		m_textChanged = false;
		return true;
	}

	protected boolean promptToSave() {
		if (!m_textChanged)
			return true;
		int result = JOptionPane.showConfirmDialog(this, "Save changes to "
				+ getDocumentName() + "?", APP_NAME,
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		switch (result) {
		case JOptionPane.YES_OPTION:
			if (!saveFile(false))
				return false;
			return true;
		case JOptionPane.NO_OPTION:
			return true;
		case JOptionPane.CANCEL_OPTION:
			return false;
		}
		return true;
	}

	protected void updateEditor() {
		int index = -1;
		for (int k = 0; k < m_fontMenus.length; k++) {
			if (m_fontMenus[k].isSelected()) {
				index = k;
				break;
			}

		}
		if (index == -1)
			return;

		boolean isBold = m_bold.isSelected();
		boolean isItalic = m_italic.isSelected();
		m_cbFonts.setSelectedIndex(index);

		if (index == 2) { // Courier
			m_bold.setSelected(false);
			m_bold.setEnabled(false);
			m_italic.setSelected(false);
			m_italic.setEnabled(false);
			m_bBold.setSelected(false);
			m_bBold.setEnabled(false);
			m_bItalic.setSelected(false);
			m_bItalic.setEnabled(false);
		} else {
			m_bold.setEnabled(true);
			m_italic.setEnabled(true);
			m_bBold.setEnabled(true);
			m_bItalic.setEnabled(true);
		}

		if (m_bBold.isSelected() != isBold)
			m_bBold.setSelected(isBold);
		if (m_bItalic.isSelected() != isItalic)
			m_bItalic.setSelected(isItalic);

		int style = Font.PLAIN;
		if (m_bold.isSelected())
			style |= Font.BOLD;
		if (m_italic.isSelected())
			style |= Font.ITALIC;
		Font fn = m_fonts[index].deriveFont(style);
		m_editor.setFont(fn);
		m_editor.repaint();
	}

	public void showError(Exception ex, String message) {
		ex.printStackTrace();
		JOptionPane.showMessageDialog(this, message, APP_NAME,
				JOptionPane.WARNING_MESSAGE);
	}

	public static void main(String argv[]) {
		BasicTextEditor frame = new BasicTextEditor();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}

	class UpdateListener implements DocumentListener {
		public void insertUpdate(DocumentEvent e) {
			m_textChanged = true;
		}

		public void removeUpdate(DocumentEvent e) {
			m_textChanged = true;
		}

		public void changedUpdate(DocumentEvent e) {
			m_textChanged = true;
		}
	}
}
