package br.com.batch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateFormat {

	public static Date parseStringToDate(String dateInString) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date data = null;
		try {
			data = formato.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static Date addHour(Date date, DurationEnum duration){
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		cal.add(Calendar.MINUTE, duration.valor);
		return cal.getTime();
	}
	
	public static Date addDay(Date date, DurationEnum duration){
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		cal.add(Calendar.DATE, duration.valor);
		return cal.getTime();
	}
}
