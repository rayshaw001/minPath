package minPath.test;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import minPath.Tools.Times;

public class dateTest {
	public static void main(String[] args) {
		GregorianCalendar g = new GregorianCalendar();
		Timestamp time = new Timestamp(108, 1, 8, 16, 10, 49, 0);
		System.out.println(Times.getHistoryTimes());
		System.out.println(g.get(GregorianCalendar.DAY_OF_WEEK));
	}
}
