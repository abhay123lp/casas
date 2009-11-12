package demo.jlist;

//see \Chapter10\1
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ListDemo1 extends JFrame {
	protected JList m_statesList;

	public ListDemo1() {
		super("Swing List [Base]");
		setSize(500, 240);
		String[] states = { "AK\tAlaska\tJuneau", "AL\tAlabama\tMontgomery",
				"AR\tArkansas\tLittle Rock", "AZ\tArizona\tPhoenix",
				"CA\tCalifornia\tSacramento", "CO\tColorado\tDenver",
				"CT\tConnecticut\tHartford", "DE\tDelaware\tDover",
				"FL\tFlorida\tTallahassee", "GA\tGeorgia\tAtlanta",
				"HI\tHawaii\tHonolulu", "IA\tIowa\tDes Moines",
				"ID\tIdaho\tBoise", "IL\tIllinois\tSpringfield",
				"IN\tIndiana\tIndianapolis", "KS\tKansas\tTopeka",
				"KY\tKentucky\tFrankfort", "LA\tLouisiana\tBaton Rouge",
				"MA\tMassachusetts\tBoston", "MD\tMaryland\tAnnapolis",
				"ME\tMaine\tAugusta", "MI\tMichigan\tLansing",
				"MN\tMinnesota\tSt.Paul", "MO\tMissouri\tJefferson City",
				"MS\tMississippi\tJackson", "MT\tMontana\tHelena",
				"NC\tNorth Carolina\tRaleigh", "ND\tNorth Dakota\tBismarck",
				"NE\tNebraska\tLincoln", "NH\tNew Hampshire\tConcord",
				"NJ\tNew Jersey\tTrenton",
				"NM\tNew Mexico\tSantaFe", "NV\tNevada\tCarson City",
				"NY\tNew York\tAlbany", "OH\tOhio\tColumbus",
				"OK\tOklahoma\tOklahoma City", "OR\tOregon\tSalem",
				"PA\tPennsylvania\tHarrisburg", "RI\tRhode Island\tProvidence",
				"SC\tSouth Carolina\tColumbia", "SD\tSouth Dakota\tPierre",
				"TN\tTennessee\tNashville", "TX\tTexas\tAustin",
				"UT\tUtah\tSalt Lake City", "VA\tVirginia\tRichmond",
				"VT\tVermont\tMontpelier", "WA\tWashington\tOlympia",
				"WV\tWest Virginia\tCharleston", "WI\tWisconsin\tMadison",
				"WY\tWyoming\tCheyenne" };
		m_statesList = new JList(states);
		JScrollPane ps = new JScrollPane();
		ps.getViewport().add(m_statesList);
		getContentPane().add(ps, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String argv[]) {
		new ListDemo1();
	}
}