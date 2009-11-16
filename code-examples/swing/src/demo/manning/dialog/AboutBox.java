package demo.manning.dialog;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

class AboutBox extends JDialog {
    public AboutBox(Frame owner) {
        super(owner, "About", true);
        
        /*
         * contentPane{
         *     JPanel{
         *         JLabel(new ImageIcon("icon.gif"))
         *     }
         * }
         */
        JLabel lbl = new JLabel(new ImageIcon("icon.gif"));
        JPanel p = new JPanel();
        Border b1 = new BevelBorder(BevelBorder.LOWERED);
        Border b2 = new EmptyBorder(5, 5, 5, 5);
        lbl.setBorder(new CompoundBorder(b1, b2));
        p.add(lbl);
        getContentPane().add(p, BorderLayout.WEST);
        
        String message = "Basic Text Editor sample application\n"
                + "(c) M.Robinson, P.Vorobiev 1998-2001";
        JTextArea txt = new JTextArea(message);
        txt.setBorder(new EmptyBorder(5, 10, 5, 10));
        txt.setFont(new Font("Helvetica", Font.BOLD, 12));
        txt.setEditable(false);
        txt.setBackground(getBackground());
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(txt);

        message = "JVM version " + System.getProperty("java.version") + "\n"
                + " by " + System.getProperty("java.vendor");
        txt = new JTextArea(message);
        txt.setBorder(new EmptyBorder(5, 10, 5, 10));
        txt.setFont(new Font("Arial", Font.PLAIN, 12));
        txt.setEditable(false);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setBackground(getBackground());
        p.add(txt);
        getContentPane().add(p, BorderLayout.CENTER);
        final JButton btOK = new JButton("OK");
        ActionListener lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        btOK.addActionListener(lst);
        p = new JPanel();
        p.add(btOK);
        getRootPane().setDefaultButton(btOK);
        getRootPane().registerKeyboardAction(lst,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        getContentPane().add(p, BorderLayout.SOUTH);
        WindowListener wl = new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                btOK.requestFocus();
            }
        };
        addWindowListener(wl);
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }
}