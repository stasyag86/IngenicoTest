package com.ingenico.entities;

import java.io.Serializable;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sourceAccountNumber;
	private String destAccountNumber;
	private String amount;
	
	
	public Transaction(String sourceAccountNumber, String destAccountNumber, String amount) {
		super();
		this.sourceAccountNumber = sourceAccountNumber;
		this.destAccountNumber = destAccountNumber;
		this.amount = amount;
	}


	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}


	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}


	public String getDestAccountNumber() {
		return destAccountNumber;
	}


	public void setDestAccountNumber(String destAccountNumber) {
		this.destAccountNumber = destAccountNumber;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}

	
}
