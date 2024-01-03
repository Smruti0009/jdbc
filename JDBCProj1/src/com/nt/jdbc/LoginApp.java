package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			String user=null,pwd=null;
			if(sc!=null) {
				System.out.println("Enter username : ");
				user=sc.nextLine();
				System.out.println("Enter password: ");
				pwd=sc.nextLine();
			}//if
			//convert input values as required
			user="'"+user+"'";
			pwd="'"+pwd+"'";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost","system","oracle9");
			//create statement obj
			if(con!=null) 
				st=con.createStatement();
			//prepare sql query
			String query = "SELECT COUNT(*) FROM USERINFO WHERE USERNAME="+user+" AND PASSWORD="+pwd;
			System.out.println(query);
			if(st!=null)
				rs=st.executeQuery(query);
			//PROCESS THE RESULTSET OBJ
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				if(count==0)
					System.out.println("Invalid credential");
				else
					System.out.println("Valid Credentials");
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

	}

}
