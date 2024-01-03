package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest5 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//Register JDBC driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			//Create Statement object
			if(con!=null)
			st=con.createStatement();
			//Prepare SQL Query
			String query="SELECT * FROM STUDENT WHERE AVG=(SELECT MAX(AVG) FROM STUDENT)";
			// Send and executeQuery
			if(st!=null)
				rs=st.executeQuery(query);
			//Process the resultSet object
			if(rs!=null) {
				boolean isRSEmpty=true;
				while(rs.next()) {
					isRSEmpty=false;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}//while
				if(isRSEmpty)
					System.out.println("No records found");
				else
					System.out.println("Records found and displayed");
			}//if
				
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
			}
		 
	finally {
			//close jdbc obj
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
			catch(SQLException cnf) {
				cnf.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		}//finally

	}//main

}//class
