package com.cg.bs.util;

import java.util.HashMap;
import java.util.Map;

import com.cg.bs.dto.AccountHolder;

public class DBUtil 
{
	public static Map<String, AccountHolder> map = new HashMap<String, AccountHolder>();
	/*static
	{
		map.put("vANI", new AccountHolder("vANI", "1230", "Shivani", "Lucknow", "7893457329", LocalDate.now()));
		map.put("kAVI", new AccountHolder("kAVI", "4560", "Kavita", "Kanpur", "7893457456", LocalDate.now()));
		map.put("sHIV", new AccountHolder("sHIV", "7890", "Shivam", "Raipur", "7890457329", LocalDate.now()));
		
		
	}*/
	public static void addAccount(AccountHolder c)
	{
		map.put(c.getUsername(), c);
		
	}

	public static Map<String, AccountHolder> getAllCus()
	{
		return map;
	}


}
