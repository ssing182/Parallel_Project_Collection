package com.cg.bs.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.bs.dao.BankDAO;
import com.cg.bs.dao.BankDAOImpl;
import com.cg.bs.dto.Customer;
import com.cg.bs.exception.BankingException;


public class BankDAOImplTest 
{
	  static BankDAO bankDao = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        bankDao = new BankDAOImpl();
        System.out.println("...Start Class...");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("...End Class...");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("...Test Function Start...");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("...Test Function End...");
    }

 

    @Test
    public void testFetchAllCstmr() {
        Assert.assertNotNull(bankDao.fetchAll());
        System.out.println("Test Fetch All Customer Passes");
    }

  
}
