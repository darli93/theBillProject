package it.fides.fatture.parsing.datacontainers;

public class TableData {
	
	private int quantita;
	private String descrizione = new String();
	private double price;
	private String sc = new String();
	int iva = 22;
	private double imponibile;
	
	public int getQuantita() {
	
		return quantita;
	
	}
	
	public void setQuantita(int quantita) {
	
		this.quantita = quantita;
	
	}
	
	public String getDescrizione() {
	
		return descrizione;
	
	}
	
	public void setDescrizione(String descrizione) {
	
		this.descrizione = descrizione;
	
	}
	
	public double getPrice() {
	
		return price;
	
	}
	
	public void setPrice(double price) {
	
		this.price = price;
	
	}
	
	public String getSc() {
	
		return sc;
	
	}

	public void setSc(String sc) {
	
		this.sc = sc;
	
	}
	
	public double getImponibile() {
	
		return imponibile;
	
	}
	
	public void setImponibile(double imponibile) {
	
		this.imponibile = imponibile;
	
	}

	

}
