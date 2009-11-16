package demo.manning.dialog;

//see \Chapter14\2

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class BasicTextEditor extends JFrame

{
    // Unchanged code from example 12.4
    public BasicTextEditor() {
        super("\"About\" BasicTextEditor");
        setSize(450, 350);
        // Unchanged code from example 12.4
    }

    protected JMenuBar createMenuBar() {
        // Unchanged code from example 12.4
        JMenu mHelp = new JMenu("Help");
        mHelp.setMnemonic('h');
        Action actionAbout = new AbstractAction("About", new ImageIcon(
                "About16.gif")) {
            public void actionPerformed(ActionEvent e) {
                AboutBox dlg = new AboutBox(BasicTextEditor.this);
                dlg.setVisible(true);
            }
        };
         item = mHelp.add(actionAbout);
         item.setMnemonic('a');
         menuBar.add(mHelp);
         getContentPane().add(m_toolBar, BorderLayout.NORTH);
         return menuBar;
    }
    // Unchanged code from example 12.4
}