package com.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind{

	@JsonProperty("deg")
	private int deg;

	@JsonProperty("speed")
	private Object speed;

	@JsonProperty("gust")
	private Object gust;

	public void setDeg(int deg){
		this.deg = deg;
	}

	public int getDeg(){
		return deg;
	}

	public void setSpeed(Object speed){
		this.speed = speed;
	}

	public Object getSpeed(){
		return speed;
	}

	public void setGust(Object gust){
		this.gust = gust;
	}

	public Object getGust(){
		return gust;
	}

	@Override
 	public String toString(){
		return 
			"Wind{" + 
			"deg = '" + deg + '\'' + 
			",speed = '" + speed + '\'' + 
			",gust = '" + gust + '\'' + 
			"}";
		}
}