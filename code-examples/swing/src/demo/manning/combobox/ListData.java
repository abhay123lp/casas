package demo.combobox;

import javax.swing.Icon;

class ListData {
    protected Icon m_icon;
    protected int m_index;
    protected boolean m_selectable;
    protected Object m_data;

    public ListData(Icon icon, int index, boolean selectable, Object data) {
        m_icon = icon;
        m_index = index;
        m_selectable = selectable;
        m_data = data;
    }

    public Icon getIcon() {
        return m_icon;
    }

    public int getIndex() {
        return m_index;
    }

    public boolean isSelectable() {
        return m_selectable;
    }

    public Object getObject() {
        return m_data;
    }

    public String toString() {
        return m_data.toString();
    }
}
