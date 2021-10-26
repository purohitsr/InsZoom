package com.inszoomapp.practise;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateConversion {

    public static void main(String[] args) throws ParseException {
	String string = "May 24 2019 3:40 PM";
	DateFormat sdf  = new SimpleDateFormat("MMMM d yyyy hh:mm aa");
	Date date = sdf.parse(string);
	System.out.println(date);
	
	System.out.println(new Date());
	
	long mills =new Date().getTime()-date.getTime();
	
	long minuites = (mills / 1000)  / 60;
	
	
	
	
	

    }

}
