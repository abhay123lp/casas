package demo.manning.combobox;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class IconComboRenderer extends JLabel implements ListCellRenderer {
    public static final int OFFSET = 16;
    protected Color m_textSelectionColor = Color.white;
    protected Color m_textNonSelectionColor = Color.black;
    protected Color m_textNonselectableColor = Color.gray;
    protected Color m_bkSelectionColor = new Color(0, 0, 128);
    protected Color m_bkNonSelectionColor = Color.white;

    protected Color m_borderSelectionColor = Color.yellow;
    protected Color m_textColor;
    protected Color m_bkColor;
    protected boolean m_hasFocus;
    protected Border[] m_borders;

    public IconComboRenderer() {
        super();
        m_textColor = m_textNonSelectionColor;
        m_bkColor = m_bkNonSelectionColor;
        m_borders = new Border[20];
        for (int k = 0; k < m_borders.length; k++)
            m_borders[k] = new EmptyBorder(0, OFFSET * k, 0, 0);
        setOpaque(false);
    }

    public Component getListCellRendererComponent(JList list, Object obj,
            int row, boolean sel, boolean hasFocus) {
        if (obj == null)
            return this;
        setText(obj.toString());
        boolean selectable = true;
        if (obj instanceof ListData) {
            ListData ldata = (ListData) obj;
            selectable = ldata.isSelectable();
            setIcon(ldata.getIcon());
            int index = 0;
            if (row >= 0) // No offset for editor (row=-1)
                index = ldata.getIndex();
            Border b = (index < m_borders.length ? m_borders[index]
                    : new EmptyBorder(0, OFFSET * index, 0, 0));
            setBorder(b);
        } else
            setIcon(null);
        setFont(list.getFont());
        m_textColor = (sel ? m_textSelectionColor
                : (selectable ? m_textNonSelectionColor
                        : m_textNonselectableColor));
        m_bkColor = (sel ? m_bkSelectionColor : m_bkNonSelectionColor);
        m_hasFocus = hasFocus;
        return this;
    }

    public void paint(Graphics g) {
        Icon icon = getIcon();
        Border b = getBorder();
        g.setColor(m_bkNonSelectionColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(m_bkColor);
        int offset = 0;

        if (icon != null && getText() != null) {
            Insets ins = getInsets();
            offset = ins.left + icon.getIconWidth() + getIconTextGap();
        }
        g.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);
        if (m_hasFocus) {
            g.setColor(m_borderSelectionColor);
            g.drawRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);
        }
        setForeground(m_textColor);
        setBackground(m_bkColor);
        super.paint(g);
    }
}
