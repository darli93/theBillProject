package it.fides.fatture.reporting.pdfreport;

import java.io.FileNotFoundException;

import it.fides.fatture.parsing.datacontainers.Body;
import it.fides.fatture.parsing.datacontainers.Footer;
import it.fides.fatture.parsing.datacontainers.Header;
import it.fides.fatture.parsing.datacontainers.TheBill;

public class MainProgram {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		TheBill fattura = new TheBill();
		
		fattura = loadBill();
		System.out.println(fattura.getCorpo().getList().get(0).getDescrizione());
	
	}
		
	private static TheBill loadBill() throws FileNotFoundException {
		TheBill fattura = new TheBill();
		Header head = new Header();	
		Body corpo = new Body();
		Footer foot = new Footer();
		head = TheBill.readBill().getHead();
		corpo = TheBill.readBill().getCorpo();
		foot = TheBill.readBill().getFine();
	
		fattura.setCorpo(corpo);
		fattura.setHead(head);
		fattura.setFine(foot);
	
		return fattura;
		
	}
	
	//private static void
	
}
