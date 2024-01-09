package com.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature{

	@JsonProperty("temp")
	private Object temp;

	@JsonProperty("temp_min")
	private Object tempMin;

	@JsonProperty("humidity")
	private int humidity;

	@JsonProperty("pressure")
	private int pressure;

	@JsonProperty("feels_like")
	private Object feelsLike;

	@JsonProperty("temp_max")
	private Object tempMax;

	public void setTemp(Object temp){
		this.temp = temp;
	}

	public Object getTemp(){
		return temp;
	}

	public void setTempMin(Object tempMin){
		this.tempMin = tempMin;
	}

	public Object getTempMin(){
		return tempMin;
	}

	public void setHumidity(int humidity){
		this.humidity = humidity;
	}

	public int getHumidity(){
		return humidity;
	}

	public void setPressure(int pressure){
		this.pressure = pressure;
	}

	public int getPressure(){
		return pressure;
	}

	public void setFeelsLike(Object feelsLike){
		this.feelsLike = feelsLike;
	}

	public Object getFeelsLike(){
		return feelsLike;
	}

	public void setTempMax(Object tempMax){
		this.tempMax = tempMax;
	}

	public Object getTempMax(){
		return tempMax;
	}

	@Override
 	public String toString(){
		return 
			"Temperature{" + 
			"temp = '" + temp + '\'' + 
			",temp_min = '" + tempMin + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",feels_like = '" + feelsLike + '\'' + 
			",temp_max = '" + tempMax + '\'' + 
			"}";
		}
}