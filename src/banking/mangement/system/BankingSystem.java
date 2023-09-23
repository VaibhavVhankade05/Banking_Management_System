package banking.mangement.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Timer;

import org.w3c.dom.ls.LSOutput;

public class BankingSystem
{
	
	static Scanner sc = new Scanner(System.in);
	
	private static final String ifsc = "VKCB0307";
	
	private static final String ba = "KHARADI";
	
	private static final int bc = 375;
	
	static final String bn = "VAISHMA CO-OPERATIVE BANK LTD";
	
	
	
	public static void main(String[] args) throws Exception
	{
		BankingSystem bs = new BankingSystem();
		
		do
		{
			System.out.println("*** Enter Your Choice *** \n \t 1.Create Account \n \t 2.View Passbook Details \n \t 3.Cash Deposite \n \t 4. Withdraw Cash");
			int c = sc.nextInt();
			System.out.println(" ");
			
			switch(c)
			{
			case 1:
				
				System.out.println("***** Enter Customer Details *****");
				System.out.println("  ");
				bs.createAccount();
				break;
				
			case 2:
				
				System.out.println(" ");
				bs.viewPassbookDetails();
				break;
				
			case 3:
				
				System.out.println(" ");
				bs.cashDeposite();
				break;
				
			case 4:
				
				System.out.println(" ");
				bs.withdrawCash();
				break;
			}
			
				
			
			
		}
		while(true);
	}
	
//	<---------- CREATE ACCOUNT ---------->
	void createAccount() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem","root","Vaibhav@27");
		Statement stmt = con.createStatement();
		
		try
		{
			
//			System.out.println("Enter Customer Account Number:");
//			String ac = sc.next(); 
			
			System.out.println("Enter Customer Name:");
			String name = sc.next(); 
			
			System.out.println("Enter Customer Address:");
			String add = sc.next();
			
			stmt.executeUpdate("INSERT INTO Accounts(NAME , ADDRESS, BRANCH_NAME , BRANCH_CODE , IFSC_CODE , PIN_CODE, BRANCH_ADDRESS, CONTACT_NO) VALUES(' "+name+" ', ' "+add+" ', ' "+bn+" ', "+bc+", ' "+ifsc+" ', "+411014+", ' "+ba+" ', "+270140261+" )");
	}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}
	
	
	
//	<---------- VIEW PASSBOOK DETAILS ---------->
	void viewPassbookDetails() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem" , "root" , "Vaibhav@27");
		Statement stmt = con.createStatement();
		
		try
		{
			System.out.println("Enrer Your Account No:");
			int ac = sc.nextInt();
			System.out.println(" ");
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts WHERE ACCOUNT_NO="+ac);
			rs.next();
			System.out.println("***** ACCOUNTS DETAILS *****");
			System.out.println(" \n \t"+"ACCOUNT NO: "+ rs.getInt("ACCOUNT_NO") +" \n \t \n \t"+ "NAME: "+rs.getString("NAME") +" \n \t \n \t"+ "ADDRESS: "+rs.getString("ADDRESS") +" \n \t \n \t"+ "BRANCH NAME: "+rs.getString("BRANCH_NAME") +" \n \t \n \t"+ "BRANCH CODE: "+rs.getString("BRANCH_CODE") +" \n \t \n \t"+ "IFSC CODE: "+rs.getString("IFSC_CODE") +" \n \t \n \t" + "PIN CODE: "+rs.getInt("PIN_CODE") +" \n \t \n \t"+ "BRANCH ADDRESS: "+rs.getString("BRANCH_ADDRESS") +" \n \t \n \t"+ "CONTACT NO: "+rs.getInt("CONTACT_NO"));
			System.out.println(" ");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}
	
	
//	<---------- CASH DEPOSITE METHOD ---------->
	void cashDeposite() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem" , "root" , "Vaibhav@27");
		Statement stmt = con.createStatement();
		
		int a,b,t;
		
		try
		{
			System.out.println("Enter Account Number: ");
			int ac = sc.nextInt();
			
			System.out.println("Enter Deposite Amount: ");
			int amt = sc.nextInt();
			
			PreparedStatement cd, b1= null;
			cd = con.prepareStatement("INSERT INTO Transactions(ACCOUNT_NO,DEPOSITE) VALUES ("+ac+",?)" );
			cd.setInt(1,amt);
			cd.executeUpdate();
			System.out.println(" ");
			
		
			b1 = con.prepareStatement("UPDATE Transactions SET BALANCE=? WHERE ACCOUNT_NO=?");
			b1.setInt(1, amt);
			b1.setInt(2, ac);
			b1.executeUpdate();
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
//	<---------- CASH WITHDRAWAL METHOD ---------->
	void withdrawCash() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem", "root", "Vaibhav@27");
		Statement stmt = con.createStatement();
		
		try
		{
			System.out.println("Enter Account Number: ");
			int ac = sc.nextInt();
			
			System.out.println("Enter Deposite Amount: ");
			int amt = sc.nextInt();
			
			PreparedStatement wc, b2= null;
			wc = con.prepareStatement("INSERT INTO Transactions(ACCOUNT_NO,WITHDRAW) VALUES ("+ac+",?)" );
			wc.setInt(1,amt);
			wc.executeUpdate();
			System.out.println(" ");
			
			
			b2 = con.prepareStatement("UPDATE Transactions SET BALANCE=? WHERE ACCOUNT_NO=?");
			b2.setInt(1, amt);
			b2.setInt(2, ac);
			b2.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}