package com.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind{

	@JsonProperty("deg")
	private int deg;

	@JsonProperty("speed")
	private Object speed;

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

	@Override
 	public String toString(){
		return 
			"Wind{" + 
			"deg = '" + deg + '\'' + 
			",speed = '" + speed + '\'' + 
			"}";
		}
}