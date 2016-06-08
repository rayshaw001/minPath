package minPath.Tools;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class Times {
	@SuppressWarnings("deprecation")
	static public Timestamp getHistoryTimes()
	{
		Timestamp pre = new Timestamp(0);
		GregorianCalendar g = new GregorianCalendar();
		System.out.println(g);
		while(!isContain(g))
		{
			g.add(GregorianCalendar.DATE, -7);
		}
		g.add(GregorianCalendar.DATE, 1);
		pre.setYear(g.get(GregorianCalendar.YEAR) - 1900);
		pre.setMonth(g.get(GregorianCalendar.MONTH)-1);
		pre.setDate(g.get(GregorianCalendar.DATE));
		pre.setHours(g.get(GregorianCalendar.HOUR_OF_DAY));
		pre.setMinutes(g.get(GregorianCalendar.MINUTE));
		pre.setSeconds(g.get(GregorianCalendar.SECOND));
		return pre;
	}
	
	static protected boolean isContain(GregorianCalendar f)
	{
		boolean flag = false;
		GregorianCalendar g1 = new GregorianCalendar(2008, 2, 2, 13, 30, 44); 
		GregorianCalendar g2 = new GregorianCalendar(2008,2,8,17,39,19); 
		if(f.compareTo(g1)>=0&&f.compareTo(g2)<=0)
		{
			flag = true;
		}
		return flag;
	}
	@SuppressWarnings("deprecation")
	public static Timestamp move(Timestamp time,int minute)
	{
		Timestamp t = new Timestamp(0);
		GregorianCalendar g = new GregorianCalendar(
				time.getYear(), time.getMonth(), time.getDate(), 
				time.getHours(), time.getMinutes(), time.getSeconds());
		g.add(GregorianCalendar.MINUTE, minute);
		t.setYear(g.get(GregorianCalendar.YEAR) );
		t.setMonth(g.get(GregorianCalendar.MONTH));
		t.setDate(g.get(GregorianCalendar.DATE));
		t.setHours(g.get(GregorianCalendar.HOUR_OF_DAY));
		t.setMinutes(g.get(GregorianCalendar.MINUTE));
		t.setSeconds(g.get(GregorianCalendar.SECOND));
		return t;
	}
}



