package com.dg3.forum.forum.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertStringToDate {
	static Date convertStringtoDate(String startDate )  {
		
		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		Date dateconvert = null;
		try {
			dateconvert = df.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateconvert;
	}
}
