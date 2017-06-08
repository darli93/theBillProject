package it.fides.fatture.parsing.datacontainers;

public class Footer {
	
	private String methodOfPayment = new String();
	private String bankId = new String();
	
	public String getMethodOfPayment() {
		return methodOfPayment;
	}
	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	

	
}
