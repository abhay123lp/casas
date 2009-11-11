package demo.combobox;

import java.util.*;
import javax.swing.*;

class CarComboBoxModel extends DefaultComboBoxModel {
    public static final ImageIcon ICON_CAR = new ImageIcon("car.gif");
    public static final ImageIcon ICON_TRIM = new ImageIcon("trim.gif");

    public CarComboBoxModel(Vector cars) {
        for (int k = 0; k < cars.size(); k++) {
            Car car = (Car) cars.elementAt(k);
            addElement(new ListData(ICON_CAR, 0, false, car));
            Vector v = car.getTrims();
            for (int i = 0; i < v.size(); i++) {
                Trim trim = (Trim) v.elementAt(i);
                addElement(new ListData(ICON_TRIM, 1, true, trim));
            }
        }
    }

    // This method only allows trims to be selected
    public void setSelectedItem(Object item) {
        if (item instanceof ListData) {
            ListData ldata = (ListData) item;
            if (!ldata.isSelectable()) {
                Object newItem = null;
                int index = getIndexOf(item);
                for (int k = index + 1; k < getSize(); k++) {
                    Object item1 = getElementAt(k);
                    if (item1 instanceof ListData) {
                        ListData ldata1 = (ListData) item1;
                        if (!ldata1.isSelectable())
                            continue;
                    }
                    newItem = item1;
                    break;
                }
                if (newItem == null)
                    return; // Selection failed
                item = newItem;
            }
        }
        super.setSelectedItem(item);
    }
}
