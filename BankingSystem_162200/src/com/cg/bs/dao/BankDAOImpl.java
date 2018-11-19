package com.cg.bs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.bs.dto.Customer;
import com.cg.bs.util.JPAUtil;

public class BankDAOImpl implements BankDAO 
{
	EntityManager em = JPAUtil.getEntityManager();

	@Override
	public boolean addCustomer(Customer c) 
	{
	
	
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		//em.close();
		//JPAUtil.factory.close();
		return true;

	}

	@Override
	public List<Customer> fetchAll() 
	{
		em.getTransaction().begin();
		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> customerList = query.getResultList();
		em.getTransaction().commit();
		//em.close();
		//JPAUtil.factory.close();
		return customerList;
		
	
	}

	

}
