package com.ingenico.dao;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ingenico.Main;
import com.ingenico.entities.Account;

public class AccountDAO extends DataMemory{
	
	public void createAccount(String accountNumber,  String balance){
		double accountBalance = 0.0;
		File file = new File(Main.FILE_NAME);
		try {
			accountBalance= Double.parseDouble(balance);
			Account account = new Account(accountNumber, accountBalance);
			saveAccount(file, account);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
	}
	
	private void saveAccount(File file, Account account){
		Map<String,Account> accountMap = null;
		
		 if (file.exists()){
			 accountMap = getAccountMap(file);
			 updateAccountBalance(account, accountMap);
			 saveToFile(file,accountMap);
		 }else{
			 accountMap = new ConcurrentHashMap<String,Account>();
		     accountMap.put(account.getNumber(), account);
			 saveToFile(file, accountMap);
		 } 
	}
}
