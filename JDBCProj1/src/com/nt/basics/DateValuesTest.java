package com.nt.basics;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValuesTest {

	public static void main(String[] args) throws ParseException {
	/*	Date d=new Date();
		System.out.println("Util date : "+d);
        System.out.println("................");
        java.sql.Date sqd =new java.sql.Date(d.getTime());
        System.out.println("sql date : "+sqd); */
		
		//converting string data value to java.util.Date class obj
		String d1="21-55-1980"; //dd-mm-yyyy
		SimpleDateFormat sdf= new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date ud1=sdf.parse(d1);
		System.out.println("String data value : "+d1);
		System.out.println("Util date value : "+ud1);
		//converting java.util.Date obj to java.sql.Date obj 
		System.out.println(".............");
		long ms=ud1.getTime();
		java.sql.Date sqd=new java.sql.Date(ms);
		System.out.println("Sql date : "+sqd);
		//if the given String value format is yy-mm-dd then it can be converted 
		//directly to java.sql.Date class obj by using  valueOf(_); method
		String d2="2024-11-24";//yyyy-mm-dd
		java.sql.Date sqd1=java.sql.Date.valueOf(d2);
		System.out.println("String date value :"+d2);
		System.out.println("Sql date value : "+sqd1);
		//converting java.sql.Date class object to java.util.Date class obj
		java.util.Date ud2=sqd1;
		System.out.println("util date :: "+ud2);
		// Converting java.util.Date class object to String
		SimpleDateFormat sdf2=new SimpleDateFormat("MMM-yyyy-dd");
		String sd3=sdf2.format(ud2);
		System.out.println("String date value :: "+sd3);
		
	}

}
