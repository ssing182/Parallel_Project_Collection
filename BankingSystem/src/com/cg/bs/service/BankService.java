package com.cg.bs.service;

import java.util.List;
import java.util.Map;
import com.cg.bs.dto.AccountHolder;
import com.cg.bs.dto.Transaction;
import com.cg.bs.exception.BankingException;

public interface BankService
{
	public void addCustomer(AccountHolder c);
	public Map<String, AccountHolder> fetchAllCustomer();
	public double deposit(String username,String password,double amount);
	public double withdraw(String username,String password,double amount);
	public double showBalance(String username,String password)throws BankingException;
	public List<Transaction> printTransaction(String username,String password)throws BankingException;
	public String fundTransfer(String username,String password,long targetAccNo,double amount)throws BankingException;
	public boolean validateName(String name) throws BankingException;
	public boolean validateContactNumber(String name) throws BankingException;
	public boolean validateUsername(String username)throws BankingException;
	public boolean validatePassword(String password)throws BankingException;
	

}
