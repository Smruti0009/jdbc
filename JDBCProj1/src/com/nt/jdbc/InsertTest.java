package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read input
			sc= new Scanner(System.in);
			int no=0;
			String name=null,addrs=null;
			float avg=0.0f;
			if(sc!=null) {
			System.out.println("Enter Student number : ");
			no=sc.nextInt();//gives 1400
			System.out.println("Enter student name : ");
			name=sc.next();
			System.out.println("Enter student address : ");
			addrs=sc.next();
			System.out.println("Enter avg : ");
			avg=sc.nextFloat();
			}//if
			//load jdbc driver class optional
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			//Crate statement obj;
			if(con!=null) 
				st=con.createStatement();
			//prapare sql query
			String query="INSERT INTO STUDENT VALUES("+no+",'"+name+"','"+addrs+"',"+avg+")";
			System.out.println(query);
			//send and execute query
			if(st!=null) {
				int count=st.executeUpdate(query);
				if(count==0)
					System.out.println("Record not inserted");
				else
					System.out.println("Record inserted");
			}//if
		}//try
		
		catch( ClassNotFoundException cnf) { //known exception
			cnf.printStackTrace();
		}
	catch(SQLException se) {//known exception
		se.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
		finally {
			try {
				if(st!=null)
					st.close();
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
