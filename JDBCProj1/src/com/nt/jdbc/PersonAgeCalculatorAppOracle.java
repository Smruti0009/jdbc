package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculatorAppOracle {
			private static final String AGE_CALCULATOR_QUERY="SELECT  (SYSDATE-DOB)/365.25 FROM JDBC_PERSON_DATE WHERE PID=?";
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
			if(con!=null) 
				ps=con.prepareStatement(AGE_CALCULATOR_QUERY);
			if(ps!=null) {
				rs=ps.executeQuery();
				ps.setInt(1, pid);
			   
			}
		if(rs.next())
			{
				System.out.println("Person age is : "+rs.getFloat(1));
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
