package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlToOracleDBTransferRecordsOperation {
       private static final String ORACLE_STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(ORA_SNO_SEQ.NEXTVAL, ?, ?,?)";
	   private static final String MYSQL_GET_STUDENT_QUERY="SELECT SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		try {
			//register drivers
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			}//try
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//establish the connection
		try(Connection oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle9");
				Connection mysqlCon=DriverManager.getConnection("jdbc:mysql:///smrutidb1","root","Mysql@9k#");
				//create statement objs
				Statement mysqlSt=mysqlCon.createStatement();
				PreparedStatement oraPs=oraCon.prepareStatement(ORACLE_STUDENT_INSERT_QUERY);
				//execute query(on student db table of mysql)
				ResultSet rs=mysqlSt.executeQuery(MYSQL_GET_STUDENT_QUERY);
				){
			//process the resultset and insert each record into oracle db table
			if(rs!=null) {
				while(rs.next()) {
					//get each record from mysql db table through rs
					String name=rs.getString(1);
					String addrs=rs.getString(2);
					float avg=rs.getFloat(3);
					//set each received record values to INSERT SQL QUERY of oracle
					oraPs.setString(1, name);
					oraPs.setString(2,addrs);
					oraPs.setFloat(3, avg);
					oraPs.executeUpdate();
					
				}//while
				System.out.println("Records are transfered from mysql db table to oracle db table");
			}//if
		}//try all jdbc objs will be closed here automatically
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in transfering records ");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Problem in transfering records");
		}
	}//main

}//class
