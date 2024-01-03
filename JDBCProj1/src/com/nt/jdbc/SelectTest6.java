package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//select empno,ename,job,sal from employee where empno=1400;
public class SelectTest6 {

	public static void main(String[] args) {
		//read input
		Scanner sc=null;
		int no=0;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			sc= new Scanner(System.in);
			System.out.println("Enter empno : ");
			no=sc.nextInt();//gives 1400
			//load jdbc driver class optional
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			//Crate statement obj;
			if(con!=null) 
				st=con.createStatement();
			//prapare sql query
			String query="SELECT  EMPNO,ENAME,JOB,SAL FROM EMPLOYEE WHERE EMPNO="+no;
			System.out.println(query);
			//preapre resultset obj
			if(st!=null) 
			rs=st.executeQuery(query);
			if(rs!=null) {
	        if(rs.next()) {
	         System.out.println("Record found and executed");
	    	 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4));
	    	}
	    	else {
	    		System.out.println("No record found");
	    	}
	  
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
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
