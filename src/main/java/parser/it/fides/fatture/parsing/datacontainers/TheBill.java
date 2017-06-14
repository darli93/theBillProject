package it.fides.fatture.parsing.datacontainers;

import java.io.FileNotFoundException;
import it.fides.fatture.parsing.reading.TextReader;

public class TheBill {
	
	private Header head;
	private Body corpo;
	private Footer fine;
		
	public TheBill() {
		
		this.head = new Header();
		this.corpo = new Body();
		this.fine = new Footer();

	}
	
	public TheBill(Header head, Body corpo, Footer fine) {
	
		this.head = head;
		this.corpo = corpo;
		this.fine = fine;
		
	}
	
	public Header getHead() {
	
		return head;
	
	}
	
	public void setHead(Header head) {
	
		this.head = head;
	
	}
	
	public Body getCorpo() {
	
		return corpo;
	
	}
	
	public void setCorpo(Body corpo) {
	
		this.corpo = corpo;
	
	}
	
	public Footer getFine() {
		
		return fine;
	
	}
	
	public void setFine(Footer fine) {
		
		this.fine = fine;
	
	}
	
	public static TheBill readBill() throws FileNotFoundException {
		
		TextReader reader = new TextReader();
		return reader.read("src/main/java/parser/it/fides/fatture/parsing/reading/20170193.dat");
	
	}

}