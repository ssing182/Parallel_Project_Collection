package com.cg.bs.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.bs.dao.BankDAO;
import com.cg.bs.dao.BankDAOImpl;
import com.cg.bs.dto.Account;
import com.cg.bs.dto.Customer;
import com.cg.bs.dto.Transaction;
import com.cg.bs.exception.BankingException;
import com.cg.bs.util.JPAUtil;

public class BankServiceImpl implements BankService
{
	BankDAO dao;
	
	EntityManager em = JPAUtil.getEntityManager();
   public BankServiceImpl() 
   {
	   
	   dao = new BankDAOImpl();
	}

	@Override
	public boolean addCustomer(Customer c) 
	{
		dao.addCustomer(c);
		return true;

	}

	@Override
	public List<Customer> fetchAllCustomer() 
	{
		return dao.fetchAll();
	}

	@Override
	public double deposit(String username, String password, double amount) throws BankingException 
	{
		
		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> customerList = query.getResultList();
		Customer cus= null;
		Iterator<Customer> it = customerList.iterator();
		
		while(it.hasNext())
		{
			cus = it.next();
			if(cus.getUsername().equals(username)&& cus.getPassword().equals(password))
			{
					
				em.getTransaction().begin();
				double bal =cus.getAccount1().getBalance();
				cus.getAccount1().setBalance(bal+amount);
				Transaction tran = new Transaction((Math.random()*9999), bal,cus.getAccount1().getAccountNumber());
				cus.getAccount1().setTransaction(tran);
				em.getTransaction().commit();
				
				return bal;
				
				
			}
		}
		throw new BankingException("Account not found!");

		
		
	}

	@Override
	public double withdraw(String username, String password, double amount) throws BankingException
	{

		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> customerList = query.getResultList();
		Customer cus= null;
		Iterator<Customer> it = customerList.iterator();
		List<Transaction> list = new ArrayList<Transaction>();
		while(it.hasNext())
		{
			cus = it.next();
			if(cus.getUsername().equals(username)&& cus.getPassword().equals(password))
			{
				    em.getTransaction().begin();
				    double bal=cus.getAccount1().getBalance();
					cus.getAccount1().setBalance(bal-amount);
					Transaction tran = new Transaction((Math.random()*9999),bal, cus.getAccount1().getAccountNumber());
					cus.getAccount1().setTransaction(tran);
					em.getTransaction().commit();
					return bal;
			}
		}throw new BankingException("Account not found!");

		
	}

	@Override
	public double showBalance(String username, String password)
			throws BankingException 
	{
		
		List<Customer> cusList = dao.fetchAll();
		Iterator<Customer> it = cusList.iterator();
		Customer cus= null;
		while(it.hasNext())
		{
			cus = it.next();
			if(cus.getUsername().equals(username)&&cus.getPassword().equals(password))
			{
				return cus.getAccount1().getBalance();
			}
		}
		throw new BankingException("Sorry! Account not Found,try again");	
		
	//	return cus.getUsername();
		
		
	}

	@Override
	public String fundTransfer(String username, String password,
			long targetAccNo, double amount) throws BankingException
	{
		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> customerList = query.getResultList();
		Customer cus= null;
		Customer cus1=null;
		Iterator<Customer> it = customerList.iterator();
		while(it.hasNext())
		{
			cus =it.next();
			if(cus.getUsername().equals(username)&&cus.getPassword().equals(password))
			{
				double balance=cus.getAccount1().getBalance();
				if(balance>=amount)
				{
					Iterator<Customer> it1 = customerList.iterator();
					while(it1.hasNext())
					{
						cus1 = it1.next();
						System.out.println(cus1.getAccount1().getAccountNumber());
						if(cus1.getAccount1().getAccountNumber()==targetAccNo)
						{
							
							em.getTransaction().begin();
							cus.getAccount1().setBalance(balance-amount);
							cus1.getAccount1().setBalance(cus1.getAccount1().getBalance()+amount);
							Transaction tran = new Transaction((Math.random()*9999),
									balance,cus1.getAccount1().getAccountNumber());
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Transaction tran1 = new Transaction((Math.random()*9999), cus1.getAccount1().getBalance(), cus.getAccount1().getAccountNumber());
							cus.getAccount1().setTransaction(tran);
							cus1.getAccount1().setTransaction(tran1);
							em.getTransaction().commit();
							return cus.getUsername();
							
							/*Transaction tran = new Transaction((Math.random()*9999), accHolder.getAccount().getBalance(), LocalDate.now());
							Transaction tran1 = new Transaction((Math.random()*9999), accHolder1.getAccount().getBalance(), LocalDate.now());
							list.add(tran);
							list1.add(tran1);
							accHolder.getAccount().setTranscation(list);
							accHolder1.getAccount().setTranscation(list1);*/
							
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
	
	@Override
	public List<Transaction> printTransaction(String username, String password)
			throws BankingException 
			{
		em.getTransaction().begin();
		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> customerList = query.getResultList();
		Customer cus= null;
		Iterator<Customer> it = customerList.iterator();
		while(it.hasNext())
		{
			cus= it.next();
			if(cus.getUsername().equals(username)&&cus.getPassword().equals(password))
			{
				em.getTransaction().commit();
				return cus.getAccount1().getTransaction();
			}
		}em.getTransaction().commit();
		throw new BankingException("Sorry! Account not Found,try again");
	}

	@Override
	public boolean validateName(String name) throws BankingException
	{
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
		 String mobilePattern = "^[6-9]{1}[0-9]{9}$";
	        if (Pattern.matches(mobilePattern, mob)) {
	            return true;
	        } else {
	            throw new BankingException("Invalid Mobile Number,example:(6/7/8/9)000000000");
	        }
	}

	@Override
	public boolean validateUsername(String username) throws BankingException {
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
		String namePattern = "[0-9]{4}";
        if (Pattern.matches(namePattern, password)) {
            return true;
        } else {
            throw new BankingException(
                    "Invalid UserName,set again: example:1111");
        }
	}

	
	

}
