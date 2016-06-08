package minPath.test;

import java.util.List;

import minPath.Tools.HibernateSessionFactory;
import minPath.entity.Datas;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class action {
	static final double R = 6371.004 ;  //km
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Integer> ids;
		List<Datas> datas;
		HibernateSessionFactory.getConfiguration();
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
		Session session = HibernateSessionFactory.getSession();
		ids = (List<Integer>)session.createQuery("select distinct taxiId from Datas u").list();
		System.out.println(ids==null);
		
		
				
		for(Integer i:ids)
		{
			try
			{
				System.out.println("id is " + i);
				datas = (List<Datas>)session.createQuery("from Datas u where u.taxiId = " + i + "  order by time").list();
				handle(datas);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		session.close();
		sessionfactory.close();
	}
	public static void handle(List<Datas> datas)
	{
		//��ʼ��sessionfactory
		SessionFactory sessionfactory = HibernateSessionFactory.getSessionFactory();
		Session session = HibernateSessionFactory.getSession();
		
		int size = datas.size();
		Datas data = datas.get(0);
		double d = 0.0;
		double d1 = 0.0;
		double d2 = 0.0;
		int i=1;
		while(i<size)
		{
			//�������
			double MLatA = data.getLatitude();
			double MLatB = datas.get(i).getLatitude();
			double MLonA = data.getLongitude();
			double MLonB = datas.get(i).getLongitude();
			double C = 0;
			/*
			d1 = data.getLatitude() -datas.get(i).getLatitude();
			d2 = data.getLongitude() -datas.get(i).getLongitude();
			d = Math.sqrt(d1*d1 + d2*d2);
			*/
			
			C = Math.sin(MLatA)*Math.sin(MLatB)*Math.cos(MLonA-MLonB) + Math.cos(MLatA)*Math.cos(MLatB);
			d = R*Math.acos(C)*Math.PI/180.0 ;
			
			System.out.println(
			"from id is " + 
			data.getId() + 
			" to id is " + 
			datas.get(i).getId() + 
			" distance is " + d);
			
			//�洢����
			/*Distance  dis = new Distance();
			dis.setDistance(d);
			dis.setDataByDataIdFrom(data);
			dis.setDataByDataIdTo(datas.get(i));
			//��ݿ����
			if(d!=0)
			{
				session.save(dis);
			}
			//������
			data = datas.get(i);
			*/
			i++;
		}
		//session.close();
		//sessionfactory.close();
	}
}
