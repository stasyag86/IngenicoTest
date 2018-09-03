package com.ingenico.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.ingenico.entities.Account;

public class DataMemory {
	
	protected void saveToFile(File file, Map<String,Account> accountMap){
		try { 
	         FileOutputStream fos;  
	         fos = new FileOutputStream(file); 
	         ObjectOutputStream oos = new ObjectOutputStream(fos); 
	         oos.writeObject(accountMap); 
	         oos.close(); 
	         
	      } catch (FileNotFoundException e) { 
	         e.printStackTrace(); 
	      } catch (IOException e) { 
	         e.printStackTrace(); 
	      } 
	}
	
	protected Map<String,Account> getAccountMap(File file){
		Map<String,Account> accountMap = null;
		try { 
			FileInputStream fis = new FileInputStream(file); 
			ObjectInputStream ois = new ObjectInputStream(fis); 
			accountMap = (Map<String, Account>) ois.readObject(); 
			ois.close();
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		}
		
		return accountMap;
	}
	
	/**
	 * if account already exist update its balance, else insert to map
	 * @param account
	 * @param accountMap
	 */
	protected void updateAccountBalance(Account account,Map<String,Account> accountMap){
		String accountNumber = account.getNumber();
		if (accountMap.containsKey(accountNumber)){
			Account accountFromDB = accountMap.get(accountNumber);
			Double newBalance = account.getBalance() + accountFromDB.getBalance();
			accountFromDB.setBalance(newBalance);
		}else{
			accountMap.put(accountNumber, account);
		}
	}

}
