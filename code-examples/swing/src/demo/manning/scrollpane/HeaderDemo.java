package demo.manning.scrollpane;

//see \Chapter7\2
import java.awt.*;
import javax.swing.*;

public class HeaderDemo extends JFrame {
    JLabel label;
    public HeaderDemo() {
        
        super("JScrollPane Demo");
        ImageIcon ii = new ImageIcon("earth.jpg");
        label = new JLabel(ii);
        JScrollPane jsp = new JScrollPane(new JLabel(ii));
        JLabel[] corners = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            corners[i] = new JLabel();
            corners[i].setBackground(Color.yellow);
            corners[i].setOpaque(true);
            corners[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(2, 2, 2, 2), BorderFactory
                            .createLineBorder(Color.red, 1)));
        }

        JLabel rowheader = new JLabel() {
            Font f = new Font("Serif", Font.ITALIC | Font.BOLD, 10);

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Rectangle r = g.getClipBounds();
                g.setFont(f);
                g.setColor(Color.red);
                for (int i = 30 - (r.y % 30); i < r.height; i += 30) {
                    g.drawLine(0, r.y + i, 3, r.y + i);
                    g.drawString("" + (r.y + i), 6, r.y + i + 3);
                }
            }
             public Dimension getPreferredSize() {
                return new Dimension(25, (int) (label.getPreferredSize()
                        .getHeight()));
            }
        };
        rowheader.setBackground(Color.yellow);
        rowheader.setOpaque(true);

        JLabel columnheader = new JLabel() {
            Font f = new Font("Serif", Font.ITALIC | Font.BOLD, 10);

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Rectangle r = g.getClipBounds();
                g.setFont(f);
                g.setColor(Color.red);
                for (int i = 30 - (r.x % 30); i < r.width; i += 30) {
                    g.drawLine(r.x + i, 0, r.x + i, 3);
                    g.drawString("" + (r.x + i), r.x + i - 10, 16);
                }
            }
             public Dimension getPreferredSize() {
                return new Dimension((int)label.getPreferredSize().getWidth(), 25);
            }
        };
        columnheader.setBackground(Color.yellow);
        columnheader.setOpaque(true);
        jsp.setRowHeaderView(rowheader);
        jsp.setColumnHeaderView(columnheader);
        jsp.setCorner(JScrollPane.LOWER_LEFT_CORNER, corners[0]);
        jsp.setCorner(JScrollPane.LOWER_RIGHT_CORNER, corners[1]);
        jsp.setCorner(JScrollPane.UPPER_LEFT_CORNER, corners[2]);
        jsp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corners[3]);

        getContentPane().add(jsp);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HeaderDemo();
    }
}