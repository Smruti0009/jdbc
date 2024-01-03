package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ps_LoginApp {
      public static final String AUTH_QUERY="SELECT COUNT(*) FROM USERINFO WHERE USERNAME=? AND PASSWORD=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String user=null, pwd=null;
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter username : ");
				user=sc.nextLine();
				System.out.println("Enter password : ");
				pwd=sc.nextLine();
			}//if
			//Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			if(con!=null)
				ps=con.prepareStatement(AUTH_QUERY);
			if(ps!=null) {
			 ps.setString(1,user);
			 ps.setString(2,pwd);
			 rs=ps.executeQuery();
			}
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				if(count==0)
					System.out.println("Invalid credential");
				else
					System.out.println("Valid credential");
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
