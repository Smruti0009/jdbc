package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class PersonAgeCalculatorAppJava {
	private static final String GET_DOB_QUERY="SELECT DOB FROM JDBC_PERSON_DATE WHERE PID=?";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			int pid=0;
			if(sc!=null) {
					System.out.println("Enter person id : ");
				    pid=sc.nextInt();
				    }
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
		/*	Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///smruti123db","root","Mysql@9k#");*/
			if(con!=null) 
				ps=con.prepareStatement(GET_DOB_QUERY);
			if(ps!=null) {
				ps.setInt(1, pid);
			}
			if(ps!=null) {
			   rs=ps.executeQuery();
			}
			java.sql.Date sqdob=null;
			if(rs.next()) {
				sqdob =rs.getDate(1);
				java.util.Date sysDate=new Date();
				long dobMS=sqdob.getTime();
				long sysDateMS=sysDate.getTime();
				float age=(sysDateMS-dobMS)/(1000.0f*60.0f*60.0f*24.0f*365.25f);
						System.out.println("Person age :: "+age);
			}
			else {
				System.out.println("Record not found.");
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

	}

}
