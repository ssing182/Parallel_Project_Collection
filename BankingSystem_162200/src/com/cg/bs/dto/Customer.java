package com.cg.bs.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;


@Entity
@NamedQueries(@NamedQuery(name = "getAllCustomers", query = "SELECT customer FROM Customer customer"))
public class Customer 
{
	@Id
	private String username;
	private String password;
	private String name;
	private String address;
	private String mobile;
	@OneToOne(cascade=CascadeType.ALL)
	private Account account1;
	
	
	 public Customer()
	{
		super();
	}
		
	
	 public Customer(String username, String password, String name,
			String address, String mobile) {
		super();
		this.account1 = new Account();
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		
		
	}

	
	
	public String getUsername() {
		return username;
	}
	public Account getAccount1() {
		return account1;
	}


	public void setAccount1(Account account1) {
		this.account1 = account1;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password
				+ ", name=" + name + ", address=" + address + ", mobile="
				+ mobile + ", account=" + account1 + "]";
	}
	

}
