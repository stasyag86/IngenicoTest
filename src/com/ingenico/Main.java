package com.ingenico;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ingenico.entities.Account;
import com.ingenico.threads.HandleAccount;
import com.ingenico.threads.HandleTransaction;


@Path("/main")
public class Main {
	
	public static final String FILE_NAME = "accountsAndBalance.dat";
	private Object syncObject = new Object();
	
	@GET
	@Path("/getaccounts") 
	@Produces(MediaType.TEXT_PLAIN)
	public String getAccounts(){
		StringBuilder builder = new StringBuilder();
		List<Account> accList = getAllAccounts();
		for(Account account : accList){
			builder.append("account number: ").append(account.getNumber()).append("   ");
			builder.append("balance: " ).append(account.getBalance()).append("\n");
		}
		
		return builder.toString();
	}
	
	@GET
	@Path("/moneytransfer") 
	@Produces(MediaType.TEXT_PLAIN)
	public String createTransaction(@QueryParam("source") String sourceAccountNumber,
			@QueryParam("destination") String destAccountNumber,
			@QueryParam("amount") String amount){
		HandleTransaction trxThread = new HandleTransaction(sourceAccountNumber, destAccountNumber, amount ,syncObject);
		Thread thread = new Thread(trxThread, "trxThread");
		thread.start();

		return amount + " from source account: " + sourceAccountNumber +
				" was transfered to destination account: " + destAccountNumber ;
	}
	
	@GET
	@Path("/createaccount")
	@Produces(MediaType.TEXT_PLAIN)
	public String createAccount(@QueryParam("accountNumber") String accountNumber,
			@QueryParam("balance") String balance){
		HandleAccount accThread = new HandleAccount(accountNumber, balance, syncObject);
		Thread thread = new Thread(accThread, "accThread");
		thread.start();
		return "account " + accountNumber + " created";
	}
	
	public List<Account> getAllAccounts(){
		Map<String, Account> accountMap = null; 
		List<Account> accountList = new ArrayList<Account>();
		try { 
			File file = new File(FILE_NAME); 
			FileInputStream fis = new FileInputStream(file); 
			ObjectInputStream ois = new ObjectInputStream(fis); 
			accountMap = (Map<String,Account>) ois.readObject(); 
			ois.close(); 
			if (accountMap != null){
				Set<String> keySet = accountMap.keySet();
				for(String accNumber : keySet){
					accountList.add(accountMap.get(accNumber));
				}
			}
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		}   
      return accountList; 
   } 
	
	
}
