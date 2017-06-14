package it.fides.fatture.reporting.pdfreport;

import java.io.FileNotFoundException;

import it.fides.fatture.parsing.datacontainers.Body;
import it.fides.fatture.parsing.datacontainers.Footer;
import it.fides.fatture.parsing.datacontainers.Header;
import it.fides.fatture.parsing.datacontainers.TableData;
import it.fides.fatture.parsing.datacontainers.TheBill;

public class MainProgram {

	static TheBill fattura = new TheBill();
	static Header head = new Header();	
	static Body corpo = new Body();
	static Footer foot = new Footer();
	static TableData tabella = new TableData();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		fattura = loadBill();
		System.out.println(fattura.getCorpo().getList().get(2).getQuantita());
	
	}
		
	private static TheBill loadBill() throws FileNotFoundException {
		
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
