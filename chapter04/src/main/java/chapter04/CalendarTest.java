package chapter04;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarTest {

	public static void main(String[] args) {
//		Locale locale = Locale.getDefault(Locale.Category.FORMAT);
//		TimeZone tz = TimeZone.getDefault();
//		System.out.println(locale + ":" + tz);
		
		// Calendar는 new 불가능!! (Singleton 패턴 ? Factory 패턴??)
		Calendar cal = Calendar.getInstance();
//		System.out.println(cal);
		printDate(cal);
		
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 11); // 12월 - 1
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(1999, 9, 16);
		cal.add(Calendar.DATE, 10000);
		printDate(cal);
	}
	
	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"};
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); // +1, 0 ~ 11
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK); // 1(일요일) ~ 7(토요일)
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		System.out.println(
				year + "-" + 
				(month+1) + "-" + 
				date + " " + 
				DAYS[day-1] + "요일" + 
				day + " " + 
				hour + ":" + 
				minute + ":" + 
				second);
	}

}
