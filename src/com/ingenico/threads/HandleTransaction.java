package com.ingenico.threads;

import com.ingenico.dao.TransactionDAO;

public class HandleTransaction implements Runnable{
	
	private TransactionDAO transactionDao;
	private String sourceAccountNumber;
	private String destAccountNumber;
	private String amount;
	private Object syncObject;
	
	public HandleTransaction(String sourceAccountNumber, String destAccountNumber, String amount , Object syncObject) {
		super();
		this.transactionDao = new TransactionDAO();
		this.syncObject = syncObject;
		this.sourceAccountNumber = sourceAccountNumber;
		this.destAccountNumber = destAccountNumber;
		this.amount = amount;
	}

	@Override
	public void run() {
		synchronized (syncObject) {
			transactionDao.saveTransaction(sourceAccountNumber, destAccountNumber, amount);
		}
		
	}

}
