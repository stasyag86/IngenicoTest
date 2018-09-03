package com.ingenico.threads;

import com.ingenico.dao.AccountDAO;

public class HandleAccount implements Runnable{
	
	private AccountDAO accountDao;
	private String accountNumber;
	private String balance;
	private Object syncObject;
	
	public HandleAccount(String accountNumber, String balance, Object syncObject) {
		super();
		this.accountDao = new AccountDAO();
		this.syncObject = syncObject;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}


	@Override
	public void run() {
		synchronized (syncObject) {
			accountDao.createAccount(accountNumber, balance);
		}
		
	}

}
