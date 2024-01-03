package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_ID 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, DESG OUT VARCHAR2 
, SALARY OUT VARCHAR2 
) AS 
BEGIN
  SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMPLOYEE WHERE EMPNO=NO;
END P_GET_EMP_DETAILS_BY_ID;*/

public class CsForProcedureCallTest2 {
   private static final String CALL_PROCEDURE_QUERY="{ CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";
	
	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				) {
			int no=0;
			if(sc!=null) {
				System.out.println("Enter Employee number : ");
				no=sc.nextInt();
			}
			if(cs!=null) {
				//Register out parameter with jdbc types
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
				//Set value to IN paramscs
				cs.setInt(1,no);
				//call PL/SQL procedure
				cs.execute();
				//Gather results from out param
				System.out.println("Employee name :: "+cs.getString(2));
				System.out.println("Employee desg :: "+cs.getString(3));
				System.out.println("Employee salary :: "+cs.getFloat(4));
			}//if
		}//try
		catch(SQLException se) {
			System.out.println("Emolyee record not available.");
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
