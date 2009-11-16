package demo.manning.slider;

//see \Chapter13\3

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class DateSlider extends JFrame {
    public final static Dimension RIGID_DIMENSION = new Dimension(1, 3);
    protected JLabel m_lbDate;
    protected JSlider m_slYear;
    protected JSlider m_slMonth;
    protected JSlider m_slDay;
    protected Hashtable m_labels;
    protected GregorianCalendar m_calendar;
    protected SimpleDateFormat m_dateFormat;

    public DateSlider() {
        super("Date Slider");
        setSize(500, 340);
        m_calendar = new GregorianCalendar();
        Date currDate = new Date();
        m_calendar.setTime(currDate);
        m_dateFormat = new SimpleDateFormat("EEE, MMM d, yyyyy");
        
        /*
         * p1: JPanel{
         *     GridLayout(4,1);
         *     p: JPanel{
         *         m_lbDate:JLable;
         *     }
         *     p: JPanel{
         *         m_slYear:JSlider(HORIZONTAL, 1990, 2010, m_calendar.get(YEAR)){
         *             paintLabels: true;
         *             majorTickSpacing:5
         *             minorTickSpacing:1
         *             paintTicks: true
         *             changeListener:lst
         *         }
         *     }
         * } 
         */
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 1));
        // panel 1
        JPanel p = new JPanel();
        p.setBorder(new TitledBorder(new EtchedBorder(), "Selected Date"));
        m_lbDate = new JLabel(m_dateFormat.format(currDate) + " ");
        m_lbDate.setFont(new Font("Arial", Font.BOLD, 24));
        p.add(m_lbDate);
        p1.add(p);
        
        // panel 2 + Year Slider
        m_slYear = new JSlider(JSlider.HORIZONTAL, 1990, 2010, m_calendar
                .get(Calendar.YEAR));
        m_slYear.setPaintLabels(true);
        m_slYear.setMajorTickSpacing(5);
        m_slYear.setMinorTickSpacing(1);
        m_slYear.setPaintTicks(true);
        ChangeListener lst = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                showDate();
            }
        };
        m_slYear.addChangeListener(lst);
        p = new JPanel();
        p.setBorder(new TitledBorder(new EtchedBorder(), "Year"));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Box.createRigidArea(RIGID_DIMENSION));
        p.add(m_slYear);
        p.add(Box.createRigidArea(RIGID_DIMENSION));
        p1.add(p);

        // panel 3 + Month Slider
        m_slMonth = new JSlider(JSlider.HORIZONTAL, 1, 12, m_calendar
                .get(Calendar.MONTH) + 1);
        String[] months = (new DateFormatSymbols()).getShortMonths();
        m_labels = new Hashtable(12);
        for (int k = 0; k < 12; k++)
            m_labels.put(new Integer(k + 1), new JLabel(months[k],
                    JLabel.CENTER));
        m_slMonth.setLabelTable(m_labels);
        m_slMonth.setPaintLabels(true);
        m_slMonth.setMajorTickSpacing(1);
        m_slMonth.setPaintTicks(true);
        m_slMonth.addChangeListener(lst);
        p = new JPanel();
        p.setBorder(new TitledBorder(new EtchedBorder(), "Month"));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Box.createRigidArea(RIGID_DIMENSION));
        p.add(m_slMonth);
        p.add(Box.createRigidArea(RIGID_DIMENSION));
        p1.add(p);
        
        
        // panel 4 + Day Slider
        int maxDays = m_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        m_slDay = new JSlider(JSlider.HORIZONTAL, 1, maxDays, m_calendar
                .get(Calendar.DAY_OF_MONTH));
        m_slDay.setPaintLabels(true);
        m_slDay.setMajorTickSpacing(5);
        m_slDay.setMinorTickSpacing(1);
        m_slDay.setPaintTicks(true);
        m_slDay.addChangeListener(lst);
        p = new JPanel();
        p.setBorder(new TitledBorder(new EtchedBorder(), "Day"));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Box.createRigidArea(RIGID_DIMENSION));
        p.add(m_slDay);
        p.add(Box.createRigidArea(RIGID_DIMENSION));
        p1.add(p);
        
        getContentPane().add(p1, BorderLayout.CENTER);
        enableEvents(ComponentEvent.COMPONENT_RESIZED);
    }

    protected void processComponentEvent(ComponentEvent e) {
        if (e.getID() == ComponentEvent.COMPONENT_RESIZED) {
            int w = getSize().width;
            m_slYear.setLabelTable(null);
            if (w > 200)
                m_slYear.setMajorTickSpacing(5);
            else
                m_slYear.setMajorTickSpacing(10);

            m_slYear.setPaintLabels(w > 100);
            m_slMonth.setLabelTable(w > 300 ? m_labels : null);
            if (w <= 300 && w >= 200)
                m_slMonth.setMajorTickSpacing(1);
            else
                m_slMonth.setMajorTickSpacing(2);
            m_slMonth.setPaintLabels(w > 100);
            m_slDay.setLabelTable(null);
            if (w > 200)
                m_slDay.setMajorTickSpacing(5);
            else
                m_slDay.setMajorTickSpacing(10);
            m_slDay.setPaintLabels(w > 100);
        }
    }

    public void showDate() {
        m_calendar.set(m_slYear.getValue(), m_slMonth.getValue() - 1, 1);
        int maxDays = m_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (m_slDay.getMaximum() != maxDays) {
            m_slDay.setValue(Math.min(m_slDay.getValue(), maxDays));
            m_slDay.setMaximum(maxDays);
            m_slDay.repaint();
        }
        m_calendar.set(m_slYear.getValue(), m_slMonth.getValue() - 1, m_slDay
                .getValue());
        Date date = m_calendar.getTime();
        m_lbDate.setText(m_dateFormat.format(date));
    }

    public static void main(String argv[]) {
        DateSlider frame = new DateSlider();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
