package demo.tabbedpane;

class Person {
    public String m_firstName;
    public String m_lastName;
    public String m_phone;

    public Person(String firstName, String lastName, String phone) {
        m_firstName = firstName;
        m_lastName = lastName;
        m_phone = phone;
    }

    public String toString() {
        String str = m_firstName + " " + m_lastName;
        if (m_phone.length() > 0)
            str += "(" + m_phone + ")";
        return str.trim();
    }
}
