package minPath.Tools;

import minPath.entity.Datas;

public class Distance {
	static final double R = 6371.004 ;  //km
	public static double getDistance(Datas start,Datas end)
	{
		double C = 0;
		double d = 0.0;
		
		double MLatA = start.getLatitude();
		double MLatB = end.getLatitude();
		double MLonA = start.getLongitude();
		double MLonB = end.getLongitude();
			
		C = Math.sin(MLatA)*Math.sin(MLatB)*Math.cos(MLonA-MLonB) + Math.cos(MLatA)*Math.cos(MLatB);
		d = R*Math.acos(C)*Math.PI/180.0 ;
		
		return d;
	}
}
