package demo.manning.dialog;

//see \Chapter14\1
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import demo.manning.layout.CustomedLayout2;

public class LoginDialog extends JDialog {
    private boolean m_succeeded = false;
    private JTextField m_loginNameBox;
    private JPasswordField m_passwordBox;
    private String m_loginName;
    private String m_password;
    private int m_errCounter = 0;

    public LoginDialog(Frame parent) {
        super(parent, "Login", true);
        
        JPanel pp = new JPanel(new CustomedLayout2());
        pp.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED),
                new EmptyBorder(5, 5, 5, 5)));
        pp.add(new JLabel("User name:"));
        m_loginNameBox = new JTextField(16);
        pp.add(m_loginNameBox);
        pp.add(new JLabel("Password:"));
        m_passwordBox = new JPasswordField(16);

        pp.add(m_passwordBox);
        JPanel p = new JPanel(new CustomedLayout2());
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.add(pp);
        
        ActionListener lst = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                m_loginName = m_loginNameBox.getText();
                m_password = new String(m_passwordBox.getPassword());
                if (!LoginModule.login(m_loginName, m_password)) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "System cannot login", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                    if (++m_errCounter >= LoginModule.MAX_LOGIN_ATTEMPTS) {
                        System.out.println("All login attempts failed");
                        System.exit(1);
                    } else {
                        m_passwordBox.setText("");
                        return;// Try one more time
                    }
                }
                // If we get here, login was successful
                m_succeeded = true;
                dispose();
            }
        };
        JButton saveButton = new JButton("Login");
        saveButton.addActionListener(lst);
        getRootPane().setDefaultButton(saveButton);
        getRootPane().registerKeyboardAction(lst,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        p.add(saveButton);
        JButton cancelButton = new JButton("Cancel");
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        };
        cancelButton.addActionListener(lst);
        getRootPane().registerKeyboardAction(lst,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        p.add(cancelButton);
        getContentPane().add(p, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

    }

    public boolean succeeded() {
        return m_succeeded;
    }

    public String getLoginName() {
        return m_loginName;
    }

    public String getPassword() {
        return m_password;
    }

    public static void main(String args[]) {
        LoginDialog dlg = new LoginDialog(null);
        dlg.show();
        if (!dlg.succeeded()) {
            System.out.println("User cancelled login");
            System.exit(1);
        }
        System.out.println("User " + dlg.getLoginName() + " has logged in");
        System.exit(0);
    }
}

class LoginModule {
    public static final int MAX_LOGIN_ATTEMPTS = 3;

    public static boolean login(String userName, String password) {
        return userName.equalsIgnoreCase("user")
                && password.equalsIgnoreCase("welcome");
    }
}
