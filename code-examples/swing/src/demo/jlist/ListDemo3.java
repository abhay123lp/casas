package demo.jlist;

//see \Chapter10\3

/**
 * adding the ability to select an element whose text starts with 
 * a character corresponding to a key press.
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ListDemo3 extends JFrame {
	protected JList m_statesList;
	protected JRadioButton m_verticalRb;
	protected JRadioButton m_verticalWrapRb;
	protected JRadioButton m_horizontalWrapRb;
	protected JRadioButton m_longRb;
	protected JRadioButton m_shortRb;

	public static ArrayModel LONG_MODEL = new ArrayModel(new String[] {
			"AK\tAlaska\tJuneau", "AL\tAlabama\tMontgomery",
			"AR\tArkansas\tLittle Rock", "AZ\tArizona\tPhoenix",
			"CA\tCalifornia\tSacramento", "CO\tColorado\tDenver",
			"CT\tConnecticut\tHartford", "DE\tDelaware\tDover",
			"FL\tFlorida\tTallahassee", "GA\tGeorgia\tAtlanta",
			"HI\tHawaii\tHonolulu", "IA\tIowa\tDes Moines", "ID\tIdaho\tBoise",
			"IL\tIllinois\tSpringfield", "IN\tIndiana\tIndianapolis",
			"KS\tKansas\tTopeka", "KY\tKentucky\tFrankfort",
			"LA\tLouisiana\tBaton Rouge", "MA\tMassachusetts\tBoston",
			"MD\tMaryland\tAnnapolis", "ME\tMaine\tAugusta",
			"MI\tMichigan\tLansing", "MN\tMinnesota\tSt.Paul",
			"MO\tMissouri\tJefferson City", "MS\tMississippi\tJackson",
			"MT\tMontana\tHelena", "NC\tNorth Carolina\tRaleigh",
			"ND\tNorth Dakota\tBismarck", "NE\tNebraska\tLincoln",
			"NH\tNew Hampshire\tConcord", "NJ\tNew Jersey\tTrenton",
			"NM\tNew Mexico\tSantaFe", "NV\tNevada\tCarson City",
			"NY\tNew York\tAlbany", "OH\tOhio\tColumbus",
			"OK\tOklahoma\tOklahoma City", "OR\tOregon\tSalem",
			"PA\tPennsylvania\tHarrisburg", "RI\tRhode Island\tProvidence",
			"SC\tSouth Carolina\tColumbia", "SD\tSouth Dakota\tPierre",
			"TN\tTennessee\tNashville", "TX\tTexas\tAustin",
			"UT\tUtah\tSalt Lake City", "VA\tVirginia\tRichmond",
			"VT\tVermont\tMontpelier", "WA\tWashington\tOlympia",
			"WV\tWest Virginia\tCharleston", "WI\tWisconsin\tMadison",
			"WY\tWyoming\tCheyenne" });
	public static ArrayModel SHORT_MODEL = new ArrayModel(new String[] { "AK",
			"AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA",
			"ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN",
			"MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY",
			"OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA",
			"VT", "WA", "WV", "WI", "WY" });

	public ListDemo3() {
		super("States List");
		setSize(450, 250);

		// build the list
		m_statesList = new JList();
		m_statesList.setModel(LONG_MODEL);
		TabListCellRenderer renderer = new TabListCellRenderer();
		renderer.setTabs(new int[] { 50, 200, 300 });
		m_statesList.setCellRenderer(renderer);
		m_statesList.addKeyListener(new ListSearcher(m_statesList));
		
		// JList is always wrapped by a JScrollPane
		JScrollPane ps = new JScrollPane();
		ps.getViewport().add(m_statesList);

		getContentPane().add(ps, BorderLayout.CENTER);

		// build the buttons pane
		JPanel pp = new JPanel(new GridLayout(2, 3));
		ButtonGroup bg1 = new ButtonGroup();
		m_verticalRb = new JRadioButton("VERTICAL", true);
		pp.add(m_verticalRb);
		bg1.add(m_verticalRb);

		m_verticalWrapRb = new JRadioButton("VERTICAL_WRAP");
		pp.add(m_verticalWrapRb);
		bg1.add(m_verticalWrapRb);

		m_horizontalWrapRb = new JRadioButton("HORIZONTAL_WRAP");
		pp.add(m_horizontalWrapRb);
		bg1.add(m_horizontalWrapRb);

		ButtonGroup bg2 = new ButtonGroup();
		m_longRb = new JRadioButton("Long Model", true);
		pp.add(m_longRb);
		bg2.add(m_longRb);

		m_shortRb = new JRadioButton("Short Model");
		pp.add(m_shortRb);
		bg2.add(m_shortRb);
		getContentPane().add(pp, BorderLayout.NORTH);

		ActionListener modelListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (m_longRb.isSelected()) {
					m_statesList
							.setPrototypeCellValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					m_statesList.setModel(LONG_MODEL);
				} else {
					m_statesList.setPrototypeCellValue("xxxxxxxxxxxx");
					m_statesList.setModel(SHORT_MODEL);
				}
			}
		};
		m_longRb.addActionListener(modelListener);
		m_shortRb.addActionListener(modelListener);

		ActionListener layoutListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (m_verticalRb.isSelected()) {
					m_statesList.setLayoutOrientation(JList.VERTICAL);
				} else if (m_verticalWrapRb.isSelected()) {
					m_statesList.setLayoutOrientation(JList.VERTICAL_WRAP);
				}
			}
		};
		m_verticalRb.addActionListener(layoutListener);
		m_verticalWrapRb.addActionListener(layoutListener);
		m_horizontalWrapRb.addActionListener(layoutListener);
	}

	public static void main(String argv[]) {
		ListDemo2 frame = new ListDemo2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
