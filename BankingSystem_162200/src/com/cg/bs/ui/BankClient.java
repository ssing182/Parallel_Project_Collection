package com.cg.bs.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.cg.bs.dto.Account;
import com.cg.bs.dto.Customer;
import com.cg.bs.dto.Transaction;
import com.cg.bs.exception.BankingException;
import com.cg.bs.service.BankService;
import com.cg.bs.service.BankServiceImpl;

public class BankClient 
{
	static Scanner sc = null;
	static Customer customer = null;
	static Account account = null;
	static int choice;
	static String add;

	static BankService service = new BankServiceImpl();

	public static void main(String[] args)
	{
		sc = new Scanner(System.in);
		service = new BankServiceImpl();
		try {
			menuDriven();
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*System.out.println("adding");
		service.addCustomer();
		System.out.println("added");*/

	}
	public static void menuDriven() throws BankingException, IOException
	{
		while(true)
		{
			System.out.println("\n------------------------");
			System.out.println("BANK OF XYZ");
			System.out.println("--------------------------\n");
			System.out.println("1: Register Account");
			System.out.println("2: Login");
			System.out.println("3: Exit");
			choice = sc.nextInt();
			switch (choice) 
			{
			case 1:
				createAccount();
				break;
			case 2:
				accountHolder();
				break;
			case 3:
				System.out.println("THANK YOU FOR USING OUR SERVICES!");
				System.exit(0);

			default:
				System.out.println("INVALID CHOICE!"+"\n CHOOSE THE CORRECT CHOICE");
				break;
			}


		}

	}
	private static void accountHolder()
	{
		String user_name;
		String password;
		System.out.println("WELCOME TO LOGIN OPTION");
		System.out.println("Enter the UserName:");
		user_name = sc.next();
		System.out.println("Enter the Password:");
		password = sc.next();
		List<Customer> customerList = service.fetchAllCustomer();
		Iterator<Customer> it = customerList.iterator();
		while(it.hasNext())
		{
			customer = it.next();
			String user = customer.getUsername();
			String pw = customer.getPassword();
			if(user.equals(user_name)&& pw.equals(password))
			{
				while(true)
				{

					System.out.println("\n------------------------");
					System.out.println("WELCOME");
					System.out.println("--------------------------\n");
					System.out.println("1: Deposit");
					System.out.println("2: Withdraw");
					System.out.println("3: Show Balance");
					System.out.println("4: Fund Transfer");
					System.out.println("5: Print Transaction");
					System.out.println("6: View");
					System.out.println("7: LogOut");
					System.out.println(" Enter your choice:");
					choice = sc.nextInt();
					switch (choice) 
					{
					case 1:
						deposit();
						break;
					case 2:
						withdraw();
						break;
					case 3:
						showBal();
						break;
					case 4:
						transfer();
						break;
					case 5:
						printTransaction();
						break;
					case 6:
						view();
						break;
					case 7:
						System.out.println("THANK YOU FOR CHOOSING OUR SERVICES!");
						try {
							menuDriven();
						} catch (BankingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					default:
						System.out.println("INVALID CHOICE!"+"\n CHOOSE THE CORRECT CHOICE");
						break;
					}

				}
			}
		}
		
	}
	private static void view() 
	{
		String username = null;
		String password = null;
		boolean f1=true,f2=true;
		while(f1)
		{
			System.out.println("Enter the username:");
			username = sc.next();
			try{
				if(service.validateUsername(username))
				{
					f1=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(f2)
		{
			System.out.println("Enter the password");
			password = sc.next();
			try{
				if(service.validatePassword(password))
				{
					f2=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		List<Customer> list = service.fetchAllCustomer();
		Iterator<Customer> it = list.iterator();
		while(it.hasNext())
		{
			customer = it.next();
			System.out.println("Name:"+customer.getName());
			System.out.println("Address:"+customer.getAddress());
			System.out.println("Mobile Number:"+customer.getMobile());
			System.out.println("Username:"+customer.getUsername());
			System.out.println("Account Number:"+customer.getAccount1().getAccountNumber());
			//System.out.println("Transaction:"+customer.getAccount1().getTransaction());
		}
		
	}
	private static void printTransaction() 
	{
		String username = null;
		String password = null;
		boolean f1=true,f2=true;
		while(f1)
		{
			System.out.println("Enter the username:");
			username = sc.next();
			try{
				if(service.validateUsername(username))
				{
					f1=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(f2)
		{
			System.out.println("Enter the password");
			password = sc.next();
			try{
				if(service.validatePassword(password))
				{
					f2=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		List<Transaction> transaction = new ArrayList<Transaction>();
		try {
			transaction = service.printTransaction(username, password);
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Transaction> it = transaction.iterator();
		if(!it.hasNext())
		{
			System.out.println("No Transcation made yet!");
		}
		else
		{
			while(it.hasNext())
			{
				System.out.println(" "+it.next());
			}
		}
		
		
	}
	private static void transfer() 
	{
		boolean f1=true,
				f2=true,
				f3=true,
				f4=true;
		String username = null;
		String password=null;
		long  accNo=0;
		while(f1)
		{
			System.out.println("Enter the username:");
			username = sc.next();
			try{
				if(service.validateUsername(username))
				{
					f1=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(f2)
		{
			System.out.println("Enter the password");
			password = sc.next();
			try{
				if(service.validatePassword(password))
				{
					f2=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Enter the amount:");
		double amount = sc.nextDouble();
		double balance;
		System.out.println("Enter the Target AccountNumber");
		accNo = sc.nextLong();
		try {
			String sourceAcc = service.fundTransfer
					(username, password, accNo, amount);
			System.out.println(" Debited/Credited Successfully: "+ sourceAcc);
			balance = service.showBalance(username, password);
			System.out.println("Your Balance is:"+balance);
		} catch (BankingException e) {
			// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		}
		
		
		
	}
	private static void showBal() 
	{
		String username = null;
		String password = null;
		boolean f1=true,f2=true;
		while(f1)
		{
			System.out.println("Enter the username:example:bAS");
			username = sc.next();
			try{
				if(service.validateUsername(username))
				{
					f1=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(f2)
		{
			System.out.println("Enter the password:example:2222");
			password = sc.next();
			try{
				if(service.validatePassword(password))
				{
					f2=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		double balance;
		try {
			balance = service.showBalance(username, password);
			System.out.println("Your Balance is:"+balance);
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	private static void withdraw() 
	{
		String username = null;
		String password = null;
		double amount=0,balance=0;
		boolean f1=true,f2=true;
		while(f1)
		{
			System.out.println("Enter the username:example:bAS");
			username = sc.next();
			try{
				if(service.validateUsername(username))
				{
					f1=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(f2)
		{
			System.out.println("Enter the password:example:2222");
			password = sc.next();
			try{
				if(service.validatePassword(password))
				{
					f2=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Enter the amount to be deposited:example:1000"+amount);
		amount = sc.nextDouble();
		try {
			balance = service.withdraw(username, password, amount);
			System.out.println("Deposited!"+amount);
			System.out.println("Your Balance is:"+balance);
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static void deposit() 
	{
		String username = null;
		String password = null;
		double amount=0,balance=0;
		boolean f1=true,f2=true;
		while(f1)
		{
			System.out.println("Enter the username:example:bAS");
			username = sc.next();
			try{
				if(service.validateUsername(username))
				{
					f1=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(f2)
		{
			System.out.println("Enter the password:example:2222");
			password = sc.next();
			try{
				if(service.validatePassword(password))
				{
					f2=false;
				}
			}catch(BankingException e)
			{
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Enter the amount to be deposited: example :1000"+amount);
		amount = sc.nextDouble();
		try {
			balance = service.deposit(username, password, amount);
			System.out.println("Deposited!"+amount);
		System.out.println("Your Balance is:"+balance);
		} catch (BankingException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	private static void createAccount() 
	{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			System.out.println("Enter Name:example:Shivani Singh");
			String name,mob;
			try{
			name = buffer.readLine();
			try {
				if (service.validateName(name)) {
					while (true) {
						System.out.println("Enter Address: example:Lucknow");
						add = buffer.readLine();
						System.out
						.println("Enter Mobile Number: example:9804567893");
						mob = sc.next();
						try{
							
						if (service.validateContactNumber(mob)) {
							while (true) {
								System.out
								.println("Set Username:example:bAS");
								String user_name = sc.next();
								try{
									if (service
											.validateUsername(user_name)) {
										while(true)
										{
											System.out.println("Set Password:example:2222");
											String password = sc.next();
											try{
												if(service.validatePassword(password)){
													while(true)
													{
														Customer cus= new Customer(user_name, password, name, add, mob);
														service.addCustomer(cus);
														System.out.println("Congratulations! Your account has been created");
														return;
													}

												}
											}catch(BankingException e)
											{
												System.out.println(e.getMessage());
											}


										}
									}break;
								}catch(BankingException e)
								{
									System.out.println(e.getMessage());
								}

							}
						}break;

						}catch(BankingException e)
						{
							System.out.println(e.getMessage());
						}
					}
					break;
				}
			} catch (BankingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}catch(IOException e)
			{
				System.out.println(e.getMessage());
			}

		}

	}


}
