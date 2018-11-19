package com.cg.bs.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Transaction
{
	
	private double transId;
	private double balance;
	@Id
	private String dateTime;
	private long transWith;
	
	public Transaction(double transId, double balance, long transWith) {
		super();
		this.transId = transId;
		this.balance = balance;
		this.dateTime = LocalDateTime.now().toString();
		this.transWith = transWith;
	}
	
	public LocalDateTime getDate() {
		return LocalDateTime.parse(dateTime);
	}
	public void setDate(LocalDateTime dateTime) {
		this.dateTime = dateTime.toString();
	}
	public long getTransWith() {
		return transWith;
	}
	public void setTransWith(long transWith) {
		this.transWith = transWith;
	}
	public void setTransId(double transId) {
		this.transId = transId;
	}
	public Transaction() {
		super();
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
	/*public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}*/

	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", balance=" + balance
				+ ", dateTime=" + dateTime + ", transWith=" + transWith + "]";
	}
	
	

}
