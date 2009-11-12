package demo.manning.textcomponent;

//see \Chapter11\4

/**
 * The following example demonstrates two JFormattedTextFields used 
 * for the input of a U.S. dollar amount and date. For the U.S. dollar 
 * amount field a locale-dependent currency format is used.
 */
import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import demo.manning.layout.CustomedLayout2;

class JFormattedTextFieldDemo extends JFrame {
	
	/*
	 *  panel{
	 *      layout: CustomedLayout2 ;
	 *      border: EmptyBorder(10, 10, 10, 10);
	 *      
	 *      label: Label("Dollar amount:"),
	 *      formatMoney: JFormattedTextField();
	 *      label: JLabel("Transaction date:");
	 *      ftfDate: JFormattedTextField();
	 *      btn: JButton("OK")
	 *  }
	 */
	public JFormattedTextFieldDemo() {
		super("Formatted TextField");
		JPanel panel = new JPanel(new CustomedLayout2());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		panel.add(new JLabel("Dollar amount:"));
		NumberFormat formatMoney = NumberFormat.getCurrencyInstance(Locale.US);
		JFormattedTextField ftMoney = new JFormattedTextField(formatMoney);
		ftMoney.setColumns(10);
		ftMoney.setValue(new Double(100));
		panel.add(ftMoney);
		
		panel.add(new JLabel("Transaction date:"));
		DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
		JFormattedTextField ftfDate = new JFormattedTextField(formatDate);
		ftfDate.setColumns(10);
		ftfDate.setValue(new Date());
		panel.add(ftfDate);
		
		JButton btn = new JButton("OK");
		panel.add(btn);
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
	}

	public static void main(String args[]) {
		JFormattedTextFieldDemo mainFrame = new JFormattedTextFieldDemo();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}
