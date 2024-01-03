package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int count=0;
		try {
			//input
			sc=new Scanner(System.in);
			float startAvg=0.0f, endAvg=0.0f;
			if(sc!=null) {
				System.out.println("Enter startAvg : ");
				startAvg=sc.nextFloat();
				System.out.println("Enter endAvg : ");
				endAvg=sc.nextFloat();
			}//sc
			//Load jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/smruti123db","root","Mysql@9k#");
			//con=DriverManager.getConnection("jdbc:mysql:///smruti123db","root","Mysql@9k#");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			String query="DELETE FROM STUDENT WHERE AVG>="+startAvg+"AND AVG<="+endAvg;
			System.out.println(query);
			//send and execute query
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("No record found for delete");
			else
				System.out.println("Records found and deleted");
		}//try
		catch(SQLException se) {
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
