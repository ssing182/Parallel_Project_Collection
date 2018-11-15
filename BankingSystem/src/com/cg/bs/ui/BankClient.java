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
import com.cg.bs.dto.AccountHolder;
import com.cg.bs.dto.Transaction;
import com.cg.bs.exception.BankingException;
import com.cg.bs.service.BankService;
import com.cg.bs.service.BankServiceImpl;

public class BankClient
{
	static Scanner sc = null;
	static AccountHolder customer = null;
	static Account account = null;
	static BankService service = null;
	static String username,password;
	static int choice;
	static String add;


	public static void main(String[] args) throws BankingException, IOException
	{
		sc = new Scanner(System.in);
		service = new BankServiceImpl();
		menuDriven();
		

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


	private static void accountHolder() throws BankingException, IOException 
	{
		String user_name;
		String password;
		System.out.println("WELCOME TO LOGIN OPTION");
		System.out.println("Enter the UserName:");
		user_name = sc.next();
		System.out.println("Enter the Password:");
		password = sc.next();
		Map<String, AccountHolder> map = service.fetchAllCustomer();
		Set<Map.Entry<String, AccountHolder>> set = map.entrySet();
		Iterator<Map.Entry<String, AccountHolder>> it = set.iterator();
		while(it.hasNext())
		{
			Map.Entry<String, AccountHolder> cus = it.next();
			AccountHolder customer = cus.getValue();
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
						menuDriven();

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
		Map<String, AccountHolder> map = service.fetchAllCustomer();
		Iterator<AccountHolder> it = map.values().iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next();
			System.out.println("Name:"+account.getName());
			System.out.println("Address:"+account.getAddress());
			System.out.println("Mobile Number:"+account.getPhone());
			System.out.println("Username:"+account.getUsername());
			System.out.println("Account Number:"+account.getAccountNumber());
		}
	}


	private static void printTransaction() throws BankingException 
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
		transaction = service.printTransaction(username, password);
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
		System.out.println("Enter the Target AccountNumber");
		accNo = sc.nextLong();
		try {
			String sourceAcc = service.fundTransfer
					(username, password, accNo, amount);
			System.out.println("Done Successfully"+sourceAcc);
		} catch (BankingException e) {
			// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		}
		
		
		
	}


	private static void showBal() throws BankingException 
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
		double balance;
		balance = service.showBalance(username, password);
		System.out.println("Your Balance is:"+balance);


	}


	private static void withdraw() 
	{
		String username = null;
		String password = null;
		double amount=0,balance=0;
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
		System.out.println("Enter the amount to be withdrawn:"+amount);
		amount = sc.nextDouble();
		balance = service.withdraw(username, password, amount);
		System.out.println("Withdrawn!"+amount);
		System.out.println("Your Balance is:"+balance);
		
		
		
	}


	private static void deposit()
	{
		String username = null;
		String password = null;
		double amount=0,balance=0;
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
		System.out.println("Enter the amount to be deposited:"+amount);
		amount = sc.nextDouble();
		balance = service.deposit(username, password, amount);
		System.out.println("Deposited!"+amount);
		System.out.println("Your Balance is:"+balance);
		
	}


	private static void createAccount() throws BankingException, IOException 
	{
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			System.out.println("Enter Name:");
			String name,mob;
			try{
			name = buffer.readLine();
			if (service.validateName(name)) {
				while (true) {
					System.out.println("Enter Address:");
					add = buffer.readLine();
					System.out
					.println("Enter Mobile Number");
					mob = sc.next();
					try{
						
					if (service.validateContactNumber(mob)) {
						while (true) {
							System.out
							.println("Set Username:");
							String user_name = sc.next();
							try{
								if (service
										.validateUsername(user_name)) {
									while(true)
									{
										System.out
										.println("Set Password:");
										String password = sc.next();
										try{
											if(service.validatePassword(password)){
												while(true)
												{
													AccountHolder cus= new AccountHolder(user_name, password, name, add, mob);
													service.addCustomer(cus);
													System.out
													.println("Congratulations! Your account has been created");
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
			}catch(IOException e)
			{
				System.out.println(e.getMessage());
			}

		}
	}
}

