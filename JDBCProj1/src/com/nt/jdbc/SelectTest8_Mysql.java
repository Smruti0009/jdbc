package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectTest8_Mysql {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//Load jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/smruti123db","root","Mysql@9k#");
			//con=DriverManager.getConnection("jdbc:mysql:///smruti123db","root","Mysql@9k#");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			String query= "SELECT * FROM STUDENT"; 
			if(st!=null)
			rs=st.executeQuery(query);
			if(rs!=null) {
				boolean isEmptied=true;
				while(rs.next()) {
					isEmptied=false;
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}//while
				if(isEmptied)
					System.out.println("No record found");
				else
					System.out.println("Record found and executed");
			}//if
			System.out.println("con obj class name::  "+con.getClass());
			System.out.println("st obj class name :: "+st.getClass());
			System.out.println("rs obj class name :: "+rs.getClass());
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
    	
    }//finally
	}//main

}//class
