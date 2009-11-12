package demo.manning.border;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class BorderTest extends JFrame {
    public BorderTest() {
        setTitle("Border Test");
        setSize(455, 450);

        JPanel content = (JPanel) getContentPane();
        content.setLayout(new GridLayout(6, 2, 5, 5));

        JPanel p = new JPanel();
        p.setBorder(new BevelBorder(BevelBorder.RAISED));
        p.add(new JLabel("RAISED BevelBorder"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new BevelBorder(BevelBorder.LOWERED));
        p.add(new JLabel("LOWERED BevelBorder"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new LineBorder(Color.black, 4, true));
        p.add(new JLabel("Black LineBorder, thickness = 4"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.add(new JLabel("EmptyBorder with thickness of 10"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        p.add(new JLabel("RAISED EtchedBorder"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        p.add(new JLabel("LOWERED EtchedBorder"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        p.add(new JLabel("RAISED SoftBevelBorder"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        p.add(new JLabel("LOWERED SoftBevelBorder"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new MatteBorder(new ImageIcon("ball.gif")));
        p.add(new JLabel("MatteBorder"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new TitledBorder(new LineBorder(Color.black, 5),
                "Title String"));
        p.add(new JLabel("TitledBorder using LineBorder"));
        content.add(p);

        p = new JPanel();
        p.setBorder(new TitledBorder("Title String"));
        p.add(new JLabel("TitledBorder using LineBorder"));
        content.add(p);

        Color c1 = new Color(86, 86, 86);
        Color c2 = new Color(192, 192, 192);
        Color c3 = new Color(204, 204, 204);
        Border b1 = new BevelBorder(EtchedBorder.RAISED, c3, c1);
        Border b2 = new MatteBorder(3, 3, 3, 3, c2);
        Border b3 = new BevelBorder(EtchedBorder.LOWERED, c3, c1);

        p = new JPanel();
        p.setBorder(new CompoundBorder(new CompoundBorder(b1, b2), b3));
        p.add(new JLabel("CompoundBorder"));
        content.add(p);
    }

    public static void main(String args[]) {
        BorderTest frame = new BorderTest();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}