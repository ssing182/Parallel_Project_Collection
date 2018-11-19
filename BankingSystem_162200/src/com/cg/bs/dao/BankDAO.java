package com.cg.bs.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.cg.bs.dto.Customer;

public interface BankDAO 
{
	public boolean addCustomer(Customer c);
	public List<Customer> fetchAll();
	

}
