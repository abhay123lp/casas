package demo.manning.dialog;

//see \Chapter14\3
import java.awt.event.*;
import javax.swing.*;

public class DialogBoxes extends JFrame {
    static final String BOX_TITLE = "Shakespeare Boxes";

    public DialogBoxes() {
        super(BOX_TITLE);
        setSize(400, 300);
        setLayeredPane(new JDesktopPane());
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mFile = new JMenu("File");
        mFile.setMnemonic('f');
        JMenuItem mItem = new JMenuItem("Ask Question");
        mItem.setMnemonic('q');
        
        ActionListener lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = new JOptionPane(
                        "To be or not to be ?\nThat is the question.");
                pane.setIcon(new ImageIcon("Hamlet.gif"));
                Object[] options = new String[] { "To be", "Not to be" };
                pane.setOptions(options);

                JDialog dialog = pane.createDialog(DialogBoxes.this, BOX_TITLE);
                dialog.show();
                Object obj = pane.getValue();
                int result = -1;
                for (int k = 0; k < options.length; k++)
                    if (options[k].equals(obj))
                        result = k;
                System.out.println("User's choice: " + result);
            }
        };
        mItem.addActionListener(lst);
        mFile.add(mItem);
        
        
        
        mItem = new JMenuItem("Info Message");
        mItem.setMnemonic('i');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "William Shakespeare was born\n"
                        + "on April 23, 1564 in\n"
                        + "Stratford-on-Avon near London";
                JOptionPane pane = new JOptionPane(message);
                pane.setIcon(new ImageIcon("Shakespeare.gif"));
                JInternalFrame frame = pane.createInternalFrame(
                        (DialogBoxes.this).getLayeredPane(), BOX_TITLE);
                getLayeredPane().add(frame);
            }

        };
        mItem.addActionListener(lst);
        mFile.add(mItem);
        mItem = new JMenuItem("Error Message");
        mItem.setMnemonic('e');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "\"The Comedy of Errors\"\n"
                        + "is considered by many scholars to be\n"
                        + "the first play Shakespeare wrote";
                JOptionPane.showMessageDialog(DialogBoxes.this, message,
                        BOX_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        };
        mItem.addActionListener(lst);
        mFile.add(mItem);
        mFile.addSeparator();
        mItem = new JMenuItem("Text Input");
        mItem.setMnemonic('t');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = (String) JOptionPane.showInputDialog(
                        DialogBoxes.this,
                        "Please enter your favorite Shakespeare play",
                        BOX_TITLE, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("Plays.jpg"), null,

                        "Romeo and Juliet");
                System.out.println("User's input: " + input);
            }
        };
        mItem.addActionListener(lst);
        mFile.add(mItem);
        mItem = new JMenuItem("Combobox Input");
        mItem.setMnemonic('c');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] plays = new String[] { "Hamlet", "King Lear",
                        "Othello", "Romeo and Juliet" };
                String input = (String) JOptionPane.showInputDialog(
                        DialogBoxes.this,
                        "Please select your favorite Shakespeare play",
                        BOX_TITLE, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("Books.gif"), plays, "Romeo and Juliet");
                System.out.println("User's input: " + input);
            }
        };
        mItem.addActionListener(lst);
        mFile.add(mItem);
        mFile.addSeparator();
        mItem = new JMenuItem("Exit");
        mItem.setMnemonic('x');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(DialogBoxes.this,
                        "Do you want to quit this application ?", BOX_TITLE,
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        };
        mItem.addActionListener(lst);
        mFile.add(mItem);
        menuBar.add(mFile);
        return menuBar;
    }

    public static void main(String argv[]) {
        DialogBoxes frame = new DialogBoxes();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}