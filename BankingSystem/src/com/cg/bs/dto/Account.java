package com.cg.bs.dto;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class Account 
{
	private long accountNumber;
	 private double balance;
	private List<Transaction> transactions;
	private static Random rand = new Random();
	
	public Account() {
		super();
		this.accountNumber = rand.nextInt(50) + 1;
		this.balance = 0;
	}
	public Account(long accountNumber, double balance
			) {
		super();
		
		this.accountNumber = rand.nextInt(999999999) + 1;
		this.balance = 0;
		//this.transaction = transaction;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) 
	{
		 this.accountNumber=accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public List<Transaction> getTranscation() {
		return transactions;
	}
	public void setTranscation(List<Transaction> transaction) {
		this.transactions = transaction;
	}
	@Override
	public String toString() {
		return "accountNumber=" + accountNumber + "\n balance="
				+ balance ;
	}
	
	
	
}
