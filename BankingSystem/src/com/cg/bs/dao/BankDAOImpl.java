package com.cg.bs.dao;

import java.util.Map;

import com.cg.bs.dto.AccountHolder;
import com.cg.bs.util.DBUtil;

public class BankDAOImpl implements BankDAO 
{

	@Override
	public String addCustomer(AccountHolder c) {
		// TODO Auto-generated method stub
		DBUtil.addAccount(c);
		return c.getUsername();
	}

	@Override
	public Map<String, AccountHolder> fetchAllCustomer() {
		// TODO Auto-generated method stub
		return DBUtil.getAllCus();
	}

}
