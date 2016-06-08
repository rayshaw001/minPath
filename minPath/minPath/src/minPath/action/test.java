package minPath.action;

import java.sql.Timestamp;
import java.util.Stack;

import minPath.Tools.Distance;
import minPath.Tools.Method;
import minPath.Tools.Times;
import minPath.entity.Datas;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Datas> datas =null;
		Method.connect();
		@SuppressWarnings("deprecation")
		Timestamp pre = Times.getHistoryTimes();
		Timestamp time = new Timestamp(108, 1, 8, 16, 10, 49, 0);//骞翠互1900涓哄熀鍑嗭紝鏈堜唤浠�涓哄熀鍑�
		Datas start = new Datas();
		start.setLongitude(116.16446);
		start.setLatitude(39.86453);
		start.setTaxiId(9999);
		start.setTime(time);
		//鏋侀檺璺濈 116.16446锛�9.86453  ||	116.6917锛�9.86453
		//start 116.31018,39.95567
		//end	116.51172,39.92123
		Datas des = new Datas();
		des.setLongitude(116.6917);
		des.setLatitude(39.86453);
		datas = Method.getResult(start, des);
		Method.disconnect();
		System.out.println("longitude:		latitude:	");
		for(Datas d:datas)
		{
			System.out.println(d.getLongitude() + "		" + d.getLatitude() + "		" +Distance.getDistance(d, des));
		}
		System.out.println("total point is " +datas.size());
	}

}
