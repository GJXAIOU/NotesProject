package chapter3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Account
{
	private String bankName;	
	public Account(String bankName)
	{
		this.bankName = bankName;
	}
}



public class GenericDemo {

	public static void main(String[] args) {
		ArrayList<String> strList = new ArrayList<String>();
		strList.add("abc");
		//strList.add(123);
		
		LinkedList<Account> accList = new LinkedList<Account>();
		Account a1 = new Account("Citi");
		Account a2 = new Account("ICBC");
		
		accList.add(a1);
		accList.add(a2);
		//accList.add("ICBC");
		
		Set set = new HashSet();
		set.add("123");
		set.add(123);

	}

}
