package demo.button;

//see \Chapter5\6

/**
 * Example 5.6 construct our own version of a tooltip manager to 
 * display a tooltip window if the mouse cursor rests over some 
 * point inside a button’s polygonal area longer than a specified 
 * time interval.
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

import javax.swing.border.*;
import javax.swing.event.*;

public class ButtonApplet3 extends JApplet {
    
    protected CustomToolTipManager m_manager;
    
    public ButtonApplet3() {
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
        
        m_manager = new CustomToolTipManager(this);
        PolygonButton3.m_toolTip = m_manager.m_toolTip;
        
        getContentPane().setLayout(null);
        getContentPane().add(bigLabel);
        bigLabel.setBounds(0, 0, bigImage.getIconWidth(), bigImage
                .getIconHeight());
    }
}