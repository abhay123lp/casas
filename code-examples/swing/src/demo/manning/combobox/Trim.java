package demo.manning.combobox;

class Trim {
    protected Car m_parent;
    protected String m_name;
    protected int m_MSRP;

    protected int m_invoice;
    protected String m_engine;

    public Trim(Car parent, String name, int MSRP, int invoice, String engine) {
        m_parent = parent;
        m_name = name;
        m_MSRP = MSRP;
        m_invoice = invoice;
        m_engine = engine;
    }

    public Car getCar() {
        return m_parent;
    }

    public String getName() {
        return m_name;
    }

    public int getMSRP() {
        return m_MSRP;
    }

    public int getInvoice() {
        return m_invoice;
    }

    public String getEngine() {
        return m_engine;
    }

    public String toString() {
        return m_name;
    }
}
