package minPath.Tools;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import minPath.entity.Datas;

public class Method {
	static SessionFactory sessionfactory = null ;
	static Session session = null;
	static public Stack<Datas> stacks = null;
	//鍙傛暟
	static final double deg_x = 0.00005;
	static final int time_x = 60;
	static final double error = 0.7;
	static final int map_size = 2500;
	static final double map_error = 0.20;//max 0.28
	static final int maxDot = 2000000;
	//涓存椂鍙橀噺
	static double minLon;
	static double minLat;
	static double maxLon;
	static double maxLat;
	/**
	 * 鍓富鏂规硶 - 璁″垝璋冪敤娆″簭
	 * 璁＄畻缁撴灉
	 * @param s 浠ｈ〃璧风偣
	 * @param d 浠ｈ〃缁堢偣
	 * @return 鍖呭惈璧风偣銆佺粓鐐逛互鍙婃墍鏈夌殑涓棿鑺傜偣
	 */
	public static Stack<Datas> getResult(Datas s,Datas d)
	{
		/**	0銆佹柊寤轰竴涓粨鏋滃爢鏍�
		 * 	1銆佸厛鍦ㄦ暟鎹簱鎵惧埌鐩镐技鐨勮捣鐐癸紙鏃堕棿銆佸嚭鍙戠偣鐨勭粡绾害鐩镐技锛�
		 * 	2銆佽皟鐢ㄦ悳绱紝杩斿洖鏈��鍚堢殑涓嬩竴涓妭鐐瑰苟鍘嬪叆鍫嗘爤
		 * 	3銆佹壘鍒扮殑鑺傜偣鏄粓鐐癸紝鍒欓�鍑哄惊鐜紝鍚﹀垯锛岀鍥涙楠�
		 * 	4銆佷竴鎵惧埌鐨勪笅涓�釜鑺傜偣涓哄熀鍑嗭紝缁х画姝ラ2
		 */
		connect();
		//	0銆佹柊寤虹粨鏋滃爢鏍�
		stacks = new Stack<>();
		
		Datas temp = findStart(s);
		//suitable data push into stack
		stacks.push(temp);
		try
		{
			while(!isEnd(temp,d))
			//while(!temp.equals(d))
			{
				temp = find(temp,d);
				stacks.push(temp);
				System.out.println(Distance.getDistance(temp, d));
			}
			stacks.push(d);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			for(Datas data:stacks)
			{
				System.out.println("longitude:"+data.getLongitude()+" latitude:"+data.getLatitude() + "distance:"+data.getDistance() + "sudu:"+data.getV());
			}
		}
		
		disconnect();
		return stacks;
	}
	/**
	 * 鎵惧埌璧峰鑺傜偣
	 * @param start
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected static Datas findStart(Datas start)
	{
		/**
		 * d.time = '" + start.getTime() + "' and
		 */
		List<Datas> datas = null;
		Datas data = null;
		String hql = null;
		double x = 0.0;
		while(data==null)
		{
			hql = suitableHql(start, x);
			datas = (List<Datas>)session.createQuery(hql).list();
			System.out.println(datas);
			data = datas.size()==0||datas==null?null:datas.get(0);
			x+=0.00002;
		}
		return data;
	}
	/**
	 * 瀵绘壘涓ょ偣闂寸殑涓嬩竴鐐�
	 * @param start 
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected static Datas find(Datas start,Datas end)
	{
		List<Datas> datas = null;
		Datas result = null;
		String hql = null;
		double x = 0.0;
		int i = 0;
		
		//涓嬩竴鐐圭殑閫熷害銆佽窛绂汇�鍔犳潈瀛樺湪淇℃伅瀛樺湪褰撳墠缁撶偣
		while(result==null)
		{
			//鏌ユ壘鍒扮殑鏄墍鏈夊綋鍓嶇粨鐐圭殑鏁版嵁
			hql = suitableHql(start,x);
			datas = (List<Datas>)session.createQuery(hql).list();
			for(Datas d:datas)
			{
				//閬嶅巻姹傚嚭鍒颁笅涓�偣鐨勮窛绂伙紝涓嬩竴鐐瑰埌缁堢偣鐨勮窛绂伙紝鏈�粓鐨勫姞鏉僿eight
				compute(d,end);
			}
			//鏈�悗鍓╀笅鎶奷atas鎺掑簭鏈��鍚堢殑鍦ㄥ墠闈�
			//鎺掑簭
			Collections.sort(datas);
			//System.out.println("hell: " + datas.get(0).getDistance());
			result = datas.get(0).getData();
			x+=0.00004;
		}
		while(isContains(result))
		{
			if(++i<datas.size())
			{
				result = datas.get(i).getData();		//杩樻槸鍙兘瓒婄晫
			}
			else
			{
				i=0;
				x+=0.00004;
				hql = suitableHql(start,x);
				datas = (List<Datas>)session.createQuery(hql).list();
				for(Datas d:datas)
				{
					//閬嶅巻姹傚嚭鍒颁笅涓�偣鐨勮窛绂伙紝涓嬩竴鐐瑰埌缁堢偣鐨勮窛绂伙紝鏈�粓鐨勫姞鏉僿eight
					compute(d,end);
				}
				//鏈�悗鍓╀笅鎶奷atas鎺掑簭鏈��鍚堢殑鍦ㄥ墠闈�
				//鎺掑簭
				Collections.sort(datas);
				System.out.println("hell: " + datas.get(0).getDistance());
				result = datas.get(0).getData();
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean compute(Datas start, Datas des)
	{
		boolean flag = true;
		//杩欓噷鍙悳绱簡taxiId锛岃繕娌℃湁绛涢�鏃堕棿
		String hql = "from Datas d where d.taxiId = " + 
		start.getTaxiId() + " order by time";
		/**	
		 * 	0銆佺瓫閫夊嚭鎵�湁鍦ㄥ綋鍓嶇粨鐐圭殑鍑虹杞�
		 * 	1銆佺瓫閫夊嚭鎵�湁绗﹀悎鏃堕棿鑺傜偣鐨勫嚭绉熻溅
		 * 
		 */
		List<Datas> datas = null;
		Datas temp = null;
		int index = 0;
		try{
			datas = (List<Datas>)session.createQuery(hql).list();
			//mistake
			index = datas.indexOf(start)+1;
			/*
			for(Datas contain:stacks)
			{
				if(datas.get(index).equals(contain))
				{
					index ++;
					break;
				}
			}
			 */
			if(index<datas.size())
			{
				temp = datas.get(index);
				start.setDistance(Distance.getDistance(temp, des));
				start.setV(Distance.getDistance(start, temp));//閫熷害   杩橀渶瑕侀櫎浠ユ椂闂�
				start.setData(temp);
			}
			else
			{
				flag = false ;
				start.setData(null);
				start.setDistance(Double.MAX_VALUE);
				start.setV(0.0);//閫熷害   杩橀渶瑕侀櫎浠ユ椂闂�
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			flag = false; 
			start.setData(null);
			start.setDistance(Double.MAX_VALUE);
			start.setV(0.0);//閫熷害   杩橀渶瑕侀櫎浠ユ椂闂�
		}
		return flag;
	}
	
	static protected String suitableHql(Datas start, double x)
	{
		/*
		 * d.time > '" + Times.move(start.getTime(), 0-time_x) +
				"' and d.time < '" + Times.move(start.getTime(), time_x) +
				"' and 
		 * 
		 */
		String hql = "from Datas d where d.longitude < " + (start.getLongitude() + deg_x + x) +
				" and d.longitude > " + (start.getLongitude() - deg_x - x) +
				" and d.latitude < " + (start.getLatitude() + deg_x + x) + 
				" and d.latitude > " + (start.getLatitude() - deg_x - x);
		return hql;
	}
	static protected boolean isContains(Datas result)
	{
		boolean flag = false ;
		for(Datas d:stacks)
		{
			if(d.getLongitude()==result.getLongitude())
			{
				if(d.getLatitude()==result.getLatitude())
				{
					flag = true;
					break;
				}
			}
		}
		
		return flag;
	}
	protected static boolean isEnd(Datas d,Datas e) 
	{
		boolean flag = false;
		double distance = Distance.getDistance(d, e);
		if(distance<error)
		{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 鎵撳紑鏁版嵁搴撹繛鎺�
	 * @return true if successfully open the connection else false;
	 */
	public static boolean connect()
	{
		try
		{
			HibernateSessionFactory.getConfiguration();
			sessionfactory = HibernateSessionFactory.getSessionFactory();
			session = HibernateSessionFactory.getSession();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if(session!=null)
			{
				session.close();
			}
			if(sessionfactory!=null)
			{
				sessionfactory.close();
			}
			return false;
		}
		return true;
	}
	
	/** 鏂紑鏁版嵁搴撹繛鎺�
	 * @return true if successfully closed connection else false
	 */
	public static void disconnect()
	{
		//session.close();
		//sessionfactory.close();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Point> getMap(Datas s,Datas e)
	{
		 List<Point> map =new ArrayList<Point>();
		 List<Datas> datas ;
		 String hql ;
		 minLon = s.getLongitude()<e.getLongitude()?s.getLongitude():e.getLongitude();
		 minLat = s.getLatitude()<e.getLatitude()?s.getLatitude():e.getLatitude();
		 maxLon = s.getLongitude()>e.getLongitude()?s.getLongitude():e.getLongitude();
		 maxLat = s.getLatitude()>e.getLatitude()?s.getLatitude():e.getLatitude();
		 double averLon = (minLon+maxLon)/2;
		 double averLat= (minLat + maxLat)/2;
		 int x = 0;
		 int y = 0;
		 connect();
		 hql = "from Datas d where d.longitude > " + (averLon - map_error) + 
			 				 " and d.longitude < " + (averLon + map_error) +
			 				  " and d.latitude > " + (averLat - map_error) +
			 				  " and d.latitude < " + (averLat + map_error); 
		 datas = (List<Datas>)session.createQuery(hql).setMaxResults(maxDot).list();
		 for(Datas d:datas)
		 {
			 x = (int) ((d.getLongitude()-minLon)*map_size);
			 y = (int) ((d.getLatitude()-minLat)*map_size);
			 map.add(new Point(x,y));
			 //System.out.println("x:"+x+"\t y:"+y);
		 }
		 disconnect();
		 System.out.println("get map end");
		 return map;
	}
	
	
	public static List<Point> getPath(Stack<Datas> s)
	{
		 List<Point> map =new ArrayList<Point>();
		 int x = 0;
		 int y = 0;
		 for(Datas d:s)
		 {
			 x = (int) ((d.getLongitude()-minLon)*map_size);
			 y = (int) ((d.getLatitude()-minLat)*map_size);
			 map.add(new Point(x,y));
		 }
		 return map;
	}
}
