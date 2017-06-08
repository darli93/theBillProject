package it.fides.fatture.parsing.datacontainers;

public class TheBill {
	
	private Header head = new Header();
	private Body corpo = new Body();
	private Footer fine = new Footer();
	
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

}
