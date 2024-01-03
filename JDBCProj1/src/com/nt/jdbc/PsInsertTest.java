package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {

	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter student count : ");
				count=sc.nextInt();
			}
			
			//Register JDBC driver s/w
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/smruti123db","root","Mysql@9k#");
			if(con!=null)
				ps=con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?)");
			for(int i=1;i<=count;i++) {
				System.out.println("Enter "+i+" student details : ");
				System.out.println("Enter sno : ");
				int sno=sc.nextInt();
				System.out.println("Enter sname : ");
				String name=sc.next();
				System.out.println("Enter address : ");
				String addrs=sc.next();
				System.out.println("Enter avg : ");
				float avg=sc.nextFloat();
				ps.setInt(1,sno);
				ps.setString(2, name);
				ps.setString(3, addrs);
				ps.setFloat(4, avg);
				
				//execute result
				int result=ps.executeUpdate();
				if(result==0)
					System.out.println(i+"  record not inserted");
				else
					System.out.println(i+" record inserted");
					}//for
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
