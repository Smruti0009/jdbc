package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CsCursorFunctionCallTest5 {
	/*
	 CREATE OR REPLACE FUNCTION FX_GET_STUDENT_DETAILS_BY_NO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, ADDRS OUT VARCHAR2 
) RETURN FLOAT AS 
     aggregation float;
BEGIN
  SELECT SNAME,SADD,AVG INTO NAME,ADDRS,aggregation FROM STUDENT WHERE SNO=NO;
  
  RETURN aggregation;
END FX_GET_STUDENT_DETAILS_BY_NO;
	 */
	private static final String CALL_FUNCTION="{?=call FX_GET_STUDENT_DETAILS_BY_NO (?,?,?)}";
	public static void main(String[] args) {
       try(Scanner sc=new Scanner(System.in);
    		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
    		  CallableStatement cs=con.prepareCall(CALL_FUNCTION);
    		   ) {
    	   //Read input values
    	   int no =0;
    	   if(sc!=null) {
    		   System.out.println("Enter student number ::");
    		   no=sc.nextInt();
    	   }
    	   if(cs!=null) {
    		   //register out,return parameters
    		   cs.registerOutParameter(1,OracleTypes.FLOAT);//return param
    		   cs.registerOutParameter(3,OracleTypes.VARCHAR);// out param
    		   cs.registerOutParameter(4,OracleTypes.VARCHAR);//out pram
    		   //set values to in params
    		   cs.setInt(2,no);
    		   //call pl/sql procedure
    		   cs.execute();
    		   //Gather result from out param,return params
    		 System.out.println("Student name :: "+cs.getString(3));
    		 System.out.println("Student addrs ::"+cs.getString(4));
    		 System.out.println("Student  avg :: "+cs.getFloat(1));
    	   }//if cs
       }//try
       catch(SQLException se) {
    	   System.out.println("Record not found");
    	   se.printStackTrace();
       }
       catch(Exception e) {
    	   e.printStackTrace();
       }

	}//main

}//class
