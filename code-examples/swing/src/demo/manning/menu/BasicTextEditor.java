package demo.manning.menu;

//see \Chapter12\2

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

    public BasicTextEditor() {
        super(APP_NAME + ": Part I - Menus");
        setSize(450, 350);
        
        /* MenuBar{
         *     
         * }
         * 
         * contentPane{
         *     ps: ScrollPane{
         *         m_editor: JTextArea;
         *     }
         * }
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
         * MenuBar{
         *     mFile: JMenu("File"){
         *         item: JMenuItem("New");
         *         item: JMenuItem("Open...");
         *         item: JMenuItem("Save");
         *         item: JMenuItem("Save As...");
         *         item: JMenuItem("Exit");
         *     }
         *     mFont: JMenu("Font"){
         *         m_fontMenu: JRadioButtonMenuItem(FONTS[k]);
         *         m_bold: JCheckBoxMenuItem("Bold");
         *         m_italic: JCheckBoxMenuItem("Italic");
         *     }
         * } 
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
        ActionListener lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!promptToSave())
                    return;
                newDocument();
            }
        };
        item.addActionListener(lst);
        mFile.add(item);
        
        
        item = new JMenuItem("Open...");
        item.setIcon(new ImageIcon("Open16.gif"));
        item.setMnemonic('o');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!promptToSave())
                    return;
                openDocument();
            }
        };
        item.addActionListener(lst);
        mFile.add(item);
        
        item = new JMenuItem("Save");
        item.setIcon(new ImageIcon("Save16.gif"));
        item.setMnemonic('s');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!m_textChanged)
                    return;
                saveFile(false);
            }
        };
        item.addActionListener(lst);
        mFile.add(item);
        
        item = new JMenuItem("Save As...");
        item.setIcon(new ImageIcon("SaveAs16.gif"));
        item.setMnemonic('a');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile(true);
            }
        };
        item.addActionListener(lst);
        mFile.add(item);
        
        mFile.addSeparator();

        item = new JMenuItem("Exit");
        item.setMnemonic('x');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        item.addActionListener(lst);
        mFile.add(item);
        
        menuBar.add(mFile);
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
        if (index == 2) { // Courier
            m_bold.setSelected(false);
            m_bold.setEnabled(false);
            m_italic.setSelected(false);
            m_italic.setEnabled(false);
        } else {
            m_bold.setEnabled(true);
            m_italic.setEnabled(true);
        }
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
