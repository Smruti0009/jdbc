package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TimeStamp_SurrogateKeyTestForMysql {
	private static final String INSERT_CUSTOMER_QUERY="INSERT INTO JDBC_CUSTOMER(CNAME,CADDRS,BILLAMT,DTOP) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		//read input values
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			sc=new Scanner(System.in);
			String cname=null,caddrs=null;
			float billAmt=0.0f;
			if(sc!=null) {
				System.out.println("Enter customer name :: ");
				cname=sc.next();
				System.out.println("Enter customer addrs :: ");
				caddrs=sc.next();
				System.out.println("Enter customer billAmount :: ");
				billAmt=sc.nextFloat();
				
			}//if
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///smrutidb1","root","Mysql@9k#");
			if(con!=null)
				ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
			if(ps!=null) {
				ps.setString(1, cname);
				ps.setString(2,caddrs);
				ps.setFloat(3, billAmt);
				ps.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
			}
			int count=0;
			if(ps!=null)
				count=ps.executeUpdate();
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
				
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
		}

	}

}
