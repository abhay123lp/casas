package demo;

import java.awt.*;
import java.awt.print.*;

public class HelloNurse {
	public static void main(String[] args) {
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(new HelloNursePrintable());
		if (pj.printDialog()) {
			try {
				pj.print();
			} catch (PrinterException e) {
				System.out.println(e);
			}
		}
	}
}

class HelloNursePrintable implements Printable {
	public int print(Graphics g, PageFormat pf, int pageIndex) {
		if (pageIndex != 0)
			return NO_SUCH_PAGE;
		Graphics2D g2 = (Graphics2D) g;
		
		return PAGE_EXISTS;
	}
}
