package com.nic.aishe.master.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	public static Integer getCollegeId(String aisheCode) {
		String split = aisheCode.split("-")[1];	
		return Integer.parseInt(split);
	}
	
	public static Date getFormateddate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date parse;
		try {
			parse = simpleDateFormat.parse(date);
			return parse;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
