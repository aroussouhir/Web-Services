package webservice;

import java.util.Currency;

public class Account {

	private int iban,bank_id,id;
	private double balance; 
	private String ownerFirstName, ownerLastName, ownerEmail,ownerCountry,currency;
	
	
	
	
	
	
	public Account() {
		super();
	}


	public Account(int id, int iban, int bank_id,float balance, String ownerFirstName, String ownerLastName, String ownerEmail,
			String ownerCountry) {
		super();
		this.id=id;
		this.iban = iban;
		this.bank_id = bank_id;
		this.balance = balance;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerCountry = ownerCountry;
	}

	

	public Account(int id,int iban, int bank_id, float balance, String ownerFirstName, String ownerLastName, String ownerEmail,
			String ownerCountry, String currency) {
		super();
		this.id=id;
		this.iban = iban;
		this.bank_id = bank_id;
		this.balance = balance;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerEmail = ownerEmail;
		this.ownerCountry = ownerCountry;
		this.currency = currency;
	}

	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIban() {
		return iban;
	}


	public void setIban(int iban) {
		this.iban = iban;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}






	public String getOwnerFirstName() {
		return ownerFirstName;
	}






	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}






	public String getOwnerLastName() {
		return ownerLastName;
	}






	public void setOwnerLastName(String ownerLastNaem) {
		this.ownerLastName = ownerLastNaem;
	}



	


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getOwnerEmail() {
		return ownerEmail;
	}






	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}






	public String getOwnerCountry() {
		return ownerCountry;
	}






	public void setOwnerCountry(String ownerCountry) {
		this.ownerCountry = ownerCountry;
	}


	public int getBank_id() {
		return bank_id;
	}


	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}


	@Override
	public String toString() {
		return "Account [iban=" + iban + ", bank_id=" + bank_id + ", balance=" + balance + ", ownerFirstName="
				+ ownerFirstName + ", ownerLastNaem=" + ownerLastName + ", ownerEmail=" + ownerEmail + ", ownerCountry="
				+ ownerCountry + "]";
	}
	
	
	
	
}
