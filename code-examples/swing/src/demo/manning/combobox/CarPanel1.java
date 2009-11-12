package demo.combobox;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

class CarPanel1 extends JPanel {
    protected JComboBox m_cbCars;
    protected JComboBox m_cbTrims;
    protected JLabel m_lblImg;
    protected JLabel m_lblMSRP;
    protected JLabel m_lblInvoice;
    protected JLabel m_lblEngine;

    public CarPanel1(String title, Vector cars) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new TitledBorder(new EtchedBorder(), title));
        
        JPanel p = new JPanel();
        p.add(new JLabel("Model:"));
        
        m_cbCars = new JComboBox(cars);
        ActionListener lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Car car = (Car) m_cbCars.getSelectedItem();
                if (car != null)
                    showCar(car);
            }
        };
        m_cbCars.addActionListener(lst);
        p.add(m_cbCars);
        add(p);
        
        
        p = new JPanel();
        p.add(new JLabel("Trim:"));
        m_cbTrims = new JComboBox();
        lst = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Trim trim = (Trim) m_cbTrims.getSelectedItem();
                if (trim != null)
                    showTrim(trim);
            }
        };
        m_cbTrims.addActionListener(lst);
        p.add(m_cbTrims);
        add(p);
        
        
        p = new JPanel();
        m_lblImg = new JLabel();
        m_lblImg.setHorizontalAlignment(JLabel.CENTER);
        m_lblImg.setPreferredSize(new Dimension(140, 80));
        m_lblImg.setBorder(new BevelBorder(BevelBorder.LOWERED));
        p.add(m_lblImg);
        add(p);
        
        
        p = new JPanel();
        p.setLayout(new GridLayout(3, 2, 10, 5));
        p.add(new JLabel("MSRP:"));
        m_lblMSRP = new JLabel();
        p.add(m_lblMSRP);
        p.add(new JLabel("Invoice:"));
        m_lblInvoice = new JLabel();
        p.add(m_lblInvoice);
        p.add(new JLabel("Engine:"));
        m_lblEngine = new JLabel();
        p.add(m_lblEngine);
        add(p);
    }

    public void selectCar(Car car) {
        m_cbCars.setSelectedItem(car);
    }

    public void showCar(Car car) {
        m_lblImg.setIcon(car.getIcon());
        if (m_cbTrims.getItemCount() > 0)
            m_cbTrims.removeAllItems();
        Vector v = car.getTrims();
        for (int k = 0; k < v.size(); k++)
            m_cbTrims.addItem(v.elementAt(k));
        m_cbTrims.grabFocus();
    }

    public void showTrim(Trim trim) {
        m_lblMSRP.setText("$" + trim.getMSRP());
        m_lblInvoice.setText("$" + trim.getInvoice());
        m_lblEngine.setText(trim.getEngine());
    }
}