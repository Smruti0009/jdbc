package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTest {
				private static final String INSERT_PERSON_DATES="INSERT INTO JDBC_PERSON_DATE VALUES(?,?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		String sdob=null,sdoj=null,sdom=null,pname=null;
		int pid=0;
		try {
		sc=new Scanner(System.in);
		if(sc!=null) {
			System.out.println("Enter person id : ");
			pid=sc.nextInt();
			System.out.println("Enter person name : ");
			pname=sc.next();
			System.out.println("Enter DOB (dd-MM-yyyy)");
			sdob=sc.next();
			System.out.println("Enter DOJ(MM-dd-yyyy)");
			sdoj=sc.next();
			System.out.println("Enter DOM(yyyy-MM-dd)");
			sdom=sc.next();
		}//if
		//Convert string data value to java.sql.Date class obj
		//for DOB
		//to java.util.Date class obj
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date udob=sdf.parse(sdob);
		//to java.util.Date class obj to java.sql.Date
		long ms=udob.getTime();
		java.sql.Date sqdob=new java.sql.Date(ms);
		//for doj
		SimpleDateFormat sdf1=new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date udoj=sdf1.parse(sdoj);
		long ms1=udoj.getTime();
		java.sql.Date sqdoj=new java.sql.Date(ms1);
		//for dom
		java.sql.Date sqdom=java.sql.Date.valueOf(sdom);
		
		//Register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Connection to jdbc 
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
		if(con!=null)
			ps=con.prepareStatement(INSERT_PERSON_DATES);
		int count=0;
		if(ps!=null) {
			ps.setInt(1, pid);
			ps.setString(2, pname);
			ps.setDate(3, sqdob);
			ps.setDate(4, sqdoj);
			ps.setDate(5, sqdom);
		  count=ps.executeUpdate();
		  //process the result
		  if(count==0) 
			  System.out.println("Record not inserted.");
		  else
			  System.out.println("Record inserted.");
		}
			
		}//try
		
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
