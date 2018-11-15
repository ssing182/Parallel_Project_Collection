package com.cg.bs.dto;


public class AccountHolder extends Account
{
	private String username;
	private String password;
	private String name;
	private String address;
	private String phone;
	private Account account;
	
	public AccountHolder() {
		super();
	}
	public AccountHolder(String username, String password, String name,
			String address, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		account = new Account();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "AccountHolder username=" + username + "\n password=" + password
				+ "\n name=" + name + "\n address=" + address + "\n phone="
				+ phone + "\n account=" + account + "11";
	}
	
	
}