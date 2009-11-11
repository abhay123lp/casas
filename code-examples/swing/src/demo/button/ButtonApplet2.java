package demo.button;

//see \Chapter5\5
/**
 * Example 5.5 will show how to develop transparent non-rectangular buttons.
 */
import java.awt.Polygon;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;

public class ButtonApplet2 extends JApplet {
    public ButtonApplet2() {
    }

    public synchronized void init() {
        String imageName = getParameter("image");
        if (imageName == null) {
            System.err.println("Need \"image\" parameter");
            return;
        }
        URL imageUrl = null;
        try {
            imageUrl = new URL(getDocumentBase(), imageName);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return;
        }
        ImageIcon bigImage = new ImageIcon(imageUrl);
        JLabel bigLabel = new JLabel(bigImage);
        bigLabel.setLayout(null);

        int index = 1;
        while (true) {
            String paramSize = getParameter("button" + index);
            String paramName = getParameter("name" + index);
            String paramUrl = getParameter("url" + index);
            if (paramSize == null || paramName == null || paramUrl == null)
                break;
            Polygon p = new Polygon();
            try {
                StringTokenizer tokenizer = new StringTokenizer(paramSize, ",");
                while (tokenizer.hasMoreTokens()) {
                    String str = tokenizer.nextToken().trim();
                    int x = Integer.parseInt(str);
                    str = tokenizer.nextToken().trim();
                    int y = Integer.parseInt(str);
                    p.addPoint(x, y);
                }
            } catch (Exception ex) {
                break;
            }
            PolygonButton btn = new PolygonButton(this, p, paramName, paramUrl);
            bigLabel.add(btn);
            index++;
        }
        getContentPane().setLayout(null);
        getContentPane().add(bigLabel);
        bigLabel.setBounds(0, 0, bigImage.getIconWidth(), bigImage
                .getIconHeight());
    }

    public String getAppletInfo() {
        return "Sample applet with PolygonButtons";
    }

    public String[][] getParameterInfo() {
        String pinfo[][] = { { "image", "string", "base image file name" },
                { "buttonX", "x1,y1, x2,y2, ...", "button's bounds" },
                { "nameX", "string", "tooltip text" },
                { "urlX", "url", "link URL" } };
        return pinfo;
    }
}
