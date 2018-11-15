package com.cg.bs.dao;

import java.util.Map;

import com.cg.bs.dto.AccountHolder;

public interface BankDAO 
{
	public String addCustomer(AccountHolder c);
	public Map<String, AccountHolder> fetchAllCustomer();

}
