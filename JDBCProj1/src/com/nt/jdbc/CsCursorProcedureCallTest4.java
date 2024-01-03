package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CsCursorProcedureCallTest4 {
	/*
	 * create or replace PROCEDURE P_GET_EMPS_BY_INITIAL_CHARS ( 
	 * INITIALCHARS IN VARCHAR2 
	 * , DETAILS OUT SYS_REFCURSOR 
	 * ) AS 
	 * BEGIN 
	 * OPEN DETAILS FOR 
	 * SELECT EMPNO,ENAME,JOB,SAL FROM EMPLOYEE WHERE ENAME LIKE INITIALCHARS; 
	 * END P_GET_EMPS_BY_INITIAL_CHARS;
	 */
	private static final String CALL_PROCEDURE="{CALL P_GET_EMPS_BY_INITIAL_CHARS(?,?)}";
	public static void main(String[] args) {
       try(Scanner sc=new Scanner(System.in);
    		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
    		  CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
    		   ) {
    	   //Read input values
    	   String initialChars=null;
    	   if(sc!=null) {
    		   System.out.println("Enter initial chars of employee name ::");
    		   initialChars=sc.next();
    	   }
    	   if(cs!=null) {
    		   //register out parameters
    		   cs.registerOutParameter(2,OracleTypes.CURSOR);
    		   //set values to in params
    		   cs.setString(1, initialChars+"%");
    		   //call pl/sql procedure
    		   cs.execute();
    		   //Gather result from out param
    		   try(ResultSet rs=(ResultSet)cs.getObject(2)){
    			   if(rs!=null) {
    				   System.out.println("Employee details whose name starts with "+initialChars);
    				   while(rs.next()) {
    				   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
    				   }//while
    			   }//if rs
    		   }//try rs
    	   }//if cs
       }//try
       catch(SQLException se) {
    	   se.printStackTrace();
       }
       catch(Exception e) {
    	   e.printStackTrace();
       }

	}//main

}//class
