package demo.manning.jlist;

import javax.swing.AbstractListModel;

class ArrayModel extends AbstractListModel {
	Object[] m_data;

	public ArrayModel(Object[] data) {
		m_data = data;
	}

	public int getSize() {
		return m_data.length;
	}

	public Object getElementAt(int index) {
		if (index < 0 || index >= getSize())
			return null;
		return m_data[index];
	}
}
