package com.ingenico.dao;

import java.io.File;
import java.util.Map;

import com.ingenico.Main;
import com.ingenico.entities.Account;

public class TransactionDAO extends DataMemory{
	
	public void saveTransaction(String sourceAccountNumber, String destAccountNumber, String amount){
		File file = new File(Main.FILE_NAME);
		Map<String, Account> accountMap = getAccountMap(file);
		Account sourceAccount = getAccount(accountMap, sourceAccountNumber);
		Account destAccount = getAccount(accountMap, destAccountNumber);
		double amountToTransfer = 0.0;
		try {
			amountToTransfer= Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			return;
		}
		
		if (sourceAccount!= null && destAccount != null){
			updateMoneyTransfer(file, accountMap, sourceAccount, -1 * amountToTransfer);		// subtract from exist balance
			updateMoneyTransfer(file, accountMap, destAccount, amountToTransfer);
		}
		
	}
	
	private void updateMoneyTransfer(File file, Map<String, Account> accountMap, Account account , double amount){
		double newBalance = account.getBalance() + amount;
		if (newBalance < 0){
			System.out.println("Un valid balance");
		}else{
			account.setBalance(newBalance);
			accountMap.put(account.getNumber(), account);
			saveToFile(file,accountMap);
		}
		
		
	}
	
	private Account getAccount(Map<String, Account> accountMap, String accountNumber){
		if (accountMap != null){
			return accountMap.get(accountNumber);
		}
		return null;
	}

}
