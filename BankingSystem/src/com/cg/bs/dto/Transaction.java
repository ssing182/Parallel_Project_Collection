package com.cg.bs.dto;

import java.time.LocalDate;

public class Transaction 
{
	private double transId;
	private double balance;
	private LocalDate date;
	
	public Transaction() {
		super();
	}
	public Transaction(double transId, double balance, LocalDate date) {
		super();
		this.transId = transId;
		this.balance = balance;
		this.date = date;
	}
	public double getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", balance=" + balance
				+ ", date=" + date + "]";
	}
	
	

}
