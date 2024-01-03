package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_AUTHENTICATION 
(
  UNAME IN VARCHAR2 
, PWD IN VARCHAR2 
, RESULT OUT VARCHAR2 
) AS 
   CNT NUMBER(2);
BEGIN
  SELECT COUNT(*) INTO CNT FROM USERINFO WHERE USERNAME=UNAME AND PASSWORD=PWD;
  IF(CNT<>0)THEN
     RESULT:='VALID CREDENTIALS.';
  ELSE
     RESULT:='INVALID CREDENTIALS';
  END IF;   
END P_AUTHENTICATION;
*/

public class CsForProcedureCallTest3 {
   private static final String CALL_PROCEDURE_QUERY="{ CALL P_AUTHENTICATION(?,?,?)}";
	
	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
			CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				) {
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter Username : ");
				user=sc.next();
				System.out.println("Enter password : ");
				pass=sc.next();
			}
			if(cs!=null) {
				//Register out parameter with jdbc types
				cs.registerOutParameter(3, Types.VARCHAR);
				//Set value to IN paramscs
				cs.setString(1,user);
				cs.setString(2, pass);
				//call PL/SQL procedure
				cs.execute();
				//Gather results from out param
				System.out.println("Employee desg :: "+cs.getString(3));
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
