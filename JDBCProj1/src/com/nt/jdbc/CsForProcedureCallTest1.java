package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsForProcedureCallTest1 {
   private static final String CALL_PROCEDURE="{ call first_pro(?,?)}";
	
	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
				) {
			int no=0;
			if(sc!=null) {
				System.out.println("Enter number : ");
				no=sc.nextInt();
			}
			if(cs!=null) {
				//Register out parameter jdbc types
				cs.registerOutParameter(2, Types.INTEGER);
				//Set value to IN paramscs
				cs.setInt(1,no);
				//call PL/SQL procedure
				cs.execute();
				//Gather results from out param
				int result= cs.getInt(2);
				System.out.println("Square value :: "+result);
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		//catch(ClassNotFoundException cnf) {
		//	cnf.printStackTrace();
	//	}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
