package demo.combobox;

import java.util.Vector;

import javax.swing.*;

class Car {
    protected String m_name;
    protected String m_manufacturer;
    protected Icon m_img;
    protected Vector m_trims;

    public Car(String name, String manufacturer, Icon img) {
        m_name = name;
        m_manufacturer = manufacturer;
        m_img = img;
        m_trims = new Vector();
    }

    public void addTrim(String name, int MSRP, int invoice, String engine) {
        Trim trim = new Trim(this, name, MSRP, invoice, engine);
        m_trims.addElement(trim);
    }

    public String getName() {
        return m_name;
    }

    public String getManufacturer() {
        return m_manufacturer;
    }

    public Icon getIcon() {
        return m_img;
    }

    public Vector getTrims() {
        return m_trims;
    }

    public String toString() {
        return m_manufacturer + " " + m_name;
    }
}