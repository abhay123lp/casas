package demo.manning.textcomponent;

// see \Chapter11\2

/**
 * Example 11.2 demonstrates embedded icons, components, and stylized text.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

public class JTextPaneDemo extends JFrame {
    // Best to reuse attribute sets as much as possible.
    static SimpleAttributeSet ITALIC_GRAY = new SimpleAttributeSet();
    static SimpleAttributeSet BOLD_BLACK = new SimpleAttributeSet();
    static SimpleAttributeSet BLACK = new SimpleAttributeSet();
    static {
        StyleConstants.setForeground(ITALIC_GRAY, Color.gray);
        StyleConstants.setItalic(ITALIC_GRAY, true);
        StyleConstants.setFontFamily(ITALIC_GRAY, "Helvetica");
        StyleConstants.setFontSize(ITALIC_GRAY, 14);
        StyleConstants.setForeground(BOLD_BLACK, Color.black);
        StyleConstants.setBold(BOLD_BLACK, true);
        StyleConstants.setFontFamily(BOLD_BLACK, "Helvetica");
        StyleConstants.setFontSize(BOLD_BLACK, 14);
        StyleConstants.setForeground(BLACK, Color.black);
        StyleConstants.setFontFamily(BLACK, "Helvetica");
        StyleConstants.setFontSize(BLACK, 14);
    }
    JTextPane m_editor = new JTextPane();

    public JTextPaneDemo() {
        super("JTextPane Demo");
        JScrollPane scrollPane = new JScrollPane(m_editor);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        setEndSelection();
        m_editor.insertIcon(new ImageIcon("manning.gif"));
        insertText("\nHistory: Distant\n\n", BOLD_BLACK);
        
        setEndSelection();
        m_editor.insertIcon(new ImageIcon("Lee_fade.jpg"));
        insertText(" ", BLACK);
        
        setEndSelection();
        m_editor.insertIcon(new ImageIcon("Bace_fade.jpg"));
        insertText("\n Lee Fitzpatrick " + " " + "Marjan Bace\n\n", ITALIC_GRAY);
        insertText("When we started doing business under "
                + "the Manning name, about 10 years ago, we were a very "
                + "different company. What we are now is the end result of "
                + "an evolutionary process in which accidental "
                + "events played as big a role, or bigger, as planning and "
                + "foresight.\n", BLACK);
        
        setEndSelection();
        JButton manningButton = new JButton("Visit Manning");
        manningButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m_editor.setEditable(false);
                try {
                    m_editor.setPage("http://www.manning.com");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
        m_editor.insertComponent(manningButton);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setVisible(true);
    }

    protected void insertText(String text, AttributeSet set) {
        try {
            m_editor.getDocument().insertString(
                    m_editor.getDocument().getLength(), text, set);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    protected void setEndSelection() {
        m_editor.setSelectionStart(m_editor.getDocument().getLength());
        m_editor.setSelectionEnd(m_editor.getDocument().getLength());
    }

    public static void main(String argv[]) {
        new JTextPaneDemo();
    }
}
