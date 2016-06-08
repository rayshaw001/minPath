package minPath.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Data entity. @author MyEclipse Persistence Tools
 */

public class Datas implements java.io.Serializable,Comparable<Datas> {
	private static final long serialVersionUID = -3922970380908409754L;
	
	private Long id;
	private Timestamp time;
	private Integer taxiId;
	private Double longitude;
	private Double latitude;
	
	private double V;
	private double distance;
	private int weight;
	
	private Datas data;

	// Constructors

	public double getV() {
		return V;
	}

	public double getDistance() {
		return distance;
	}

	public int getWeight() {
		return weight;
	}

	public void setV(double v) {
		V = v;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	/** default constructor */
	public Datas() {
	}

	/** minimal constructor */
	public Datas(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Datas(Long id, Timestamp time, Integer taxiId,
			Double longitude,Double latitude) {
		this.id = id;
		this.time = time;
		this.taxiId = taxiId;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getTaxiId() {
		return this.taxiId;
	}

	public void setTaxiId(Integer taxiId) {
		this.taxiId = taxiId;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Datas getData() {
		return data;
	}

	public void setData(Datas data) {
		this.data = data;
	}
	@Override
	public boolean equals(Object obj)
	{
		boolean flag = false;
		if(this.getLongitude().equals(((Datas)obj).getLongitude()))
		{
			if(this.getLatitude().equals(((Datas)obj).getLatitude()))
			{
				flag = true;
			}
		}
		/*
		if(obj instanceof Datas)
		{
			if(Math.abs(this.getLongitude()-((Datas)obj).getLongitude())<0.00005)
			{
				if(Math.abs(this.getLatitude()-((Datas)obj).getLatitude())<0.00005)
				{
					flag = true;
				}
			}
		}
		 */
		return flag;
	}
	@Override			//閫熷害涓庤窛绂诲浣曞姞鏉�	
	public int compareTo(Datas s) {
		int flag = 0;  
        if(this.getDistance()<s.getDistance()){  
            flag=flag-2;
        }  
        else if(this.getDistance()>s.getDistance()){  
            flag=flag+2;  
        } 
        if(this.getV()<s.getV()){  
            flag++;
        }  
        else if(this.getV()>s.getV()){  
            flag--;  
        }   
        return flag;  
    }
}