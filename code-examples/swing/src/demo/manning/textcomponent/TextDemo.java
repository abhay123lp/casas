package demo.manning.textcomponent;

//see \Chapter11\5

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import demo.manning.layout.CustomedLayout2;

public class TextDemo extends JFrame {
    

    protected JFormattedTextField m_firstTxt;
    protected JFormattedTextField m_lastTxt;
    protected JFormattedTextField m_phoneTxt;
    protected JFormattedTextField m_faxTxt;
    protected JPasswordField m_passwordTxt;
    protected JTextArea m_commentsTxt;
    protected JLabel m_status;
    public static final String PHONE_PATTERN = "(###) ###-####";

    public TextDemo() {
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
        JPanel p = new JPanel(new CustomedLayout2());
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        p.add(new JLabel("First name:"));
        m_firstTxt = new JFormattedTextField(new NameFormat());
        m_firstTxt.setInputVerifier(new TextVerifier(
                "First name cannot be empty"));
        m_firstTxt.setColumns(12);
        p.add(m_firstTxt);

        p.add(new JLabel("Last name:"));
        m_lastTxt = new JFormattedTextField(new NameFormat());
        m_lastTxt.setColumns(12);
        p.add(m_lastTxt);
        p.add(new JLabel("Phone number:"));
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(PHONE_PATTERN);
        } catch (ParseException pex) {
            pex.printStackTrace();
        }
        m_phoneTxt = new JFormattedTextField(formatter);
        m_phoneTxt.setColumns(12);
        m_phoneTxt.setInputVerifier(new FTFVerifier("Phone format is "
                + PHONE_PATTERN));
        p.add(m_phoneTxt);
        p.add(new JLabel("Fax number:"));
        m_faxTxt = new JFormattedTextField(new PhoneFormat());
        m_faxTxt.setColumns(12);
        m_faxTxt.setInputVerifier(new FTFVerifier("Fax format is "
                + PHONE_PATTERN));
        p.add(m_faxTxt);
        p.add(new JLabel("Login password:"));
        m_passwordTxt = new JPasswordField(20);
        m_passwordTxt.setFont(monospaced);
        m_passwordTxt.setInputVerifier(new TextVerifier(
                "Login password cannot be empty"));
        p.add(m_passwordTxt);
        p.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(),
                "Personal Data"), new EmptyBorder(1, 5, 3, 5)));
        bigPanel.add(p, BorderLayout.NORTH);
        m_commentsTxt = new JTextArea("", 4, 30);
        m_commentsTxt.setFont(monospaced);
        m_commentsTxt.setLineWrap(true);
        m_commentsTxt.setWrapStyleWord(true);
        p = new JPanel(new BorderLayout());
        p.add(new JScrollPane(m_commentsTxt));
        p.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(),
                "Comments"), new EmptyBorder(3, 5, 3, 5)));
        bigPanel.add(p, BorderLayout.CENTER);
        m_status = new JLabel("Input data");
        m_status.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2),
                new SoftBevelBorder(SoftBevelBorder.LOWERED)));
        bigPanel.add(m_status, BorderLayout.SOUTH);
        Dimension d = m_status.getPreferredSize();
        m_status.setPreferredSize(new Dimension(150, d.height));
        bigPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(bigPanel);
        pack();
    }

    public static void main(String[] args) {

        JFrame frame = new TextDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Format to capitalize all words
     */
    class NameFormat extends Format {
        
        public StringBuffer format(Object obj, StringBuffer toAppendTo,
                FieldPosition fieldPosition) {
            fieldPosition.setBeginIndex(toAppendTo.length());
            String str = obj.toString();
            char prevCh = ' ';
            for (int k = 0; k < str.length(); k++) {
                char nextCh = str.charAt(k);
                if (Character.isLetter(nextCh) && prevCh == ' ')
                    nextCh = Character.toTitleCase(nextCh);
                toAppendTo.append(nextCh);
                prevCh = nextCh;
            }
            fieldPosition.setEndIndex(toAppendTo.length());
            return toAppendTo;
        }

        @Override
        public Object parseObject(String source, ParsePosition pos) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    /**
     * Format phone numbers
     */
    class PhoneFormat extends Format {
        public StringBuffer format(Object obj, StringBuffer toAppendTo,
                FieldPosition fieldPosition) {
            fieldPosition.setBeginIndex(toAppendTo.length());
            // Get digits of the number
            String str = obj.toString();
            StringBuffer number = new StringBuffer();
            
            for (int k = 0; k < str.length(); k++) {
                char nextCh = str.charAt(k);
                if (Character.isDigit(nextCh)) {
                    number.append(nextCh);
                } else if (Character.isLetter(nextCh)) {
                    nextCh = Character.toUpperCase(nextCh);
                    switch (nextCh) {
                    case 'A':
                    case 'B':
                    case 'C':
                        number.append('2');
                        break;
                    case 'D':
                    case 'E':
                    case 'F':
                        number.append('3');
                        break;
                    case 'G':
                    case 'H':
                    case 'I':
                        number.append('4');
                        break;
                    case 'J':
                    case 'K':
                    case 'L':
                        number.append('5');
                        break;
                    case 'M':
                    case 'N':
                    case 'O':
                        number.append('6');
                        break;
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                        number.append('7');
                        break;
                    case 'T':
                    case 'U':
                    case 'V':
                        number.append('8');
                        break;
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                        number.append('9');
                        break;
                    }
                }
            }
            // Format digits according to the pattern
            int index = 0;
            for (int k = 0; k < PHONE_PATTERN.length(); k++) {
                char ch = PHONE_PATTERN.charAt(k);
                if (ch == '#') {
                    if (index >= number.length())
                        break;
                    toAppendTo.append(number.charAt(index++));
                } else
                    toAppendTo.append(ch);
            }
            fieldPosition.setEndIndex(toAppendTo.length());
            return toAppendTo;
        }

        public Object parseObject(String text, ParsePosition pos) {
            pos.setIndex(pos.getIndex() + text.length());
            return text;
        }
    }

    /**
     * Verify input to JTextField
     */
    class TextVerifier extends InputVerifier {
        private String m_errMsg;

        public TextVerifier(String errMsg) {
            m_errMsg = errMsg;
        }

        public boolean verify(JComponent input) {
            m_status.setText("");
            if (!(input instanceof JTextField))
                return true;
            JTextField txt = (JTextField) input;
            String str = txt.getText();
            if (str.length() == 0) {
                m_status.setText(m_errMsg);
                return false;
            }
            return true;
        }
    }

    /**
     * Verify input to JFormattedTextField
     */
    class FTFVerifier extends InputVerifier {
        private String m_errMsg;

        public FTFVerifier(String errMsg) {
            m_errMsg = errMsg;
        }

        public boolean verify(JComponent input) {
            m_status.setText("");
            if (!(input instanceof JFormattedTextField))
                return true;
            JFormattedTextField ftf = (JFormattedTextField) input;
            JFormattedTextField.AbstractFormatter formatter = ftf
                    .getFormatter();
            if (formatter == null)
                return true;
            try {
                formatter.stringToValue(ftf.getText());
                return true;
            } catch (ParseException pe) {
                m_status.setText(m_errMsg);

                return false;
            }
        }
    }

}