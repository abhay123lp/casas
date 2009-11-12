package demo.window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowClosingListener extends WindowAdapter {
    JFrame frame = null;

    public void windowClosing(WindowEvent e) {
        
        int confirm = JOptionPane.showOptionDialog(frame, 
                "Really Exit?",
                "Exit Confirmation", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, 
                null, null, null);
        
        if (confirm == 0) {
            frame.dispose();
            System.exit(0);
        }
    }
}
