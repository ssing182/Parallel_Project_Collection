package com.cg.bs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.cg.bs.dao.BankDAO;
import com.cg.bs.dao.BankDAOImpl;
import com.cg.bs.dto.Account;
import com.cg.bs.dto.AccountHolder;
import com.cg.bs.dto.Transaction;
import com.cg.bs.exception.BankingException;

public class BankServiceImpl implements BankService
{
	BankDAO dao = null;
	AccountHolder accHolder = null;
	AccountHolder accHolder1 = null;
	Account acc = null;
	List<Transaction> list = new ArrayList<Transaction>();
	List<Transaction> list1 = new ArrayList<Transaction>();
	
	public BankServiceImpl() 
	{
		dao = new BankDAOImpl();
		accHolder = new AccountHolder();
		//accHolder = new AccountHolder();
	}

	@Override
	public void addCustomer(AccountHolder c) {
		// TODO Auto-generated method stub
		dao.addCustomer(c);
	}

	@Override
	public Map<String, AccountHolder> fetchAllCustomer() {
		// TODO Auto-generated method stub
		return dao.fetchAllCustomer();
				
	}

	@Override
	public boolean validateName(String name) throws BankingException {
		// TODO Auto-generated method stub
		 String namePattern = "[A-Z][a-z]*" + " " + "[A-Z][a-z]*";
	        if (Pattern.matches(namePattern, name)) {
	            return true;
	        } else {
	            throw new BankingException(
	                    "Invalid Name as name only consist of alphabets only and first letter must be capital,example:Shivani Singh");
	        }
	}

	@Override
	public boolean validateContactNumber(String mob) throws BankingException {
		// TODO Auto-generated method stub
		 String mobilePattern = "^[6-9]{1}[0-9]{9}$";
	        if (Pattern.matches(mobilePattern, mob)) {
	            return true;
	        } else {
	            throw new BankingException("Invalid Mobile Number,example:(6/7/8/9)000000000");
	        }

	}

	@Override
	public boolean validateUsername(String username) throws BankingException {
		// TODO Auto-generated method stub
		 String namePattern = "[a-z][A-Z]+";
	        if (Pattern.matches(namePattern, username)) {
	            return true;
	        } else {
	            throw new BankingException(
	                    "Invalid UserName,set again: example:aSDF");
	        }
	}

	@Override
	public boolean validatePassword(String password) throws BankingException {
		// TODO Auto-generated method stub
		String namePattern = "[0-9]{4}";
        if (Pattern.matches(namePattern, password)) {
            return true;
        } else {
            throw new BankingException(
                    "Invalid UserName,set again: example:1111");
        }
	}

	@Override
	public double deposit(String username, String password, double amount)
	{
		Map<String, AccountHolder> account = dao.fetchAllCustomer();
		Iterator<AccountHolder> it = account.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUsername().equalsIgnoreCase(username)&& accHolder.getPassword().equalsIgnoreCase(password)){
				{
					accHolder.setBalance(accHolder.getBalance()+amount);
					Transaction tran = new Transaction((Math.random()*9999), accHolder.getBalance(), LocalDate.now());
					list.add(tran);
					accHolder.setTranscation(list);
					
				}
			}
		}
		return accHolder.getBalance();
		
	}

	@Override
	public double withdraw(String username, String password, double amount) 
	{
		Map<String, AccountHolder> account = dao.fetchAllCustomer();
		Iterator<AccountHolder> it = account.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUsername().equalsIgnoreCase(username)&& accHolder.getPassword().equalsIgnoreCase(password)){
				{
					double balance=accHolder.getBalance();
					if(balance>= amount)
					{
					accHolder.setBalance(accHolder.getBalance()-amount);
					Transaction tran = new Transaction((Math.random()*9999), accHolder.getBalance(), LocalDate.now());
					list.add(tran);
					accHolder.setTranscation(list);
					
				}
			}
		}
		}
		return accHolder.getBalance();
		
	}

	@Override
	public double showBalance(String username, String password) throws BankingException 
	{
		Map<String, AccountHolder> account = dao.fetchAllCustomer();
		Iterator<AccountHolder> it = account.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUsername().equalsIgnoreCase(username)&&accHolder.getPassword().equalsIgnoreCase(password))
			{
				return accHolder.getBalance();
			}
		}
		throw new BankingException("Sorry! Account not Found,try again");
		
	}

	@Override
	public List<Transaction> printTransaction(String username, String password) throws BankingException 
	{
		
		Map<String, AccountHolder> account = dao.fetchAllCustomer();
		Iterator<AccountHolder> it = account.values().iterator();
		while(it.hasNext())
		{
			accHolder = it.next();
			if(accHolder.getUsername().equalsIgnoreCase(username)&&accHolder.getPassword().equalsIgnoreCase(password))
			{
				return accHolder.getTranscation();
			}
		}
		throw new BankingException("Sorry! Account not Found,try again");
	}


	@Override
	public String fundTransfer(String username, String password,
			long targetAccNo, double amount) throws BankingException
	{
		Map<String, AccountHolder> acccount = dao.fetchAllCustomer();
		Iterator<AccountHolder> it = acccount.values().iterator();
		while(it.hasNext())
		{
			accHolder =it.next();
			if(accHolder.getUsername().equals(username)&&accHolder.getPassword().equalsIgnoreCase(password))
			{
				double balance=accHolder.getBalance();
				if(balance>=amount)
				{
					Iterator<AccountHolder> it1 = acccount.values().iterator();
					while(it1.hasNext())
					{
						accHolder1 = it1.next();
						System.out.println(accHolder1.getAccountNumber());
						if(accHolder1.getAccountNumber()==targetAccNo)
						{
							accHolder.setBalance(balance-amount);
							accHolder1.setBalance(accHolder1.getBalance()+amount);
							
							Transaction tran = new Transaction((Math.random()*9999), accHolder.getBalance(), LocalDate.now());
							Transaction tran1 = new Transaction((Math.random()*9999), accHolder1.getBalance(), LocalDate.now());
							list.add(tran);
							list1.add(tran1);
							accHolder.setTranscation(list);
							accHolder1.setTranscation(list1);
							return accHolder.getUsername();
							
						}
					}
				}
				else{
					throw new BankingException("Insufficient Balance");
				}
			}
			
		}
		throw new BankingException("Your Account not found: incorrect username or password!");
		
		
	}

}
