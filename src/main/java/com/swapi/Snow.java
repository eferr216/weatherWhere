package com.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow{

	@JsonProperty("1h")
	private int jsonMember1h;

	public void setJsonMember1h(int jsonMember1h){
		this.jsonMember1h = jsonMember1h;
	}

	public int getJsonMember1h(){
		return jsonMember1h;
	}

	@Override
 	public String toString(){
		return 
			"Snow{" + 
			"1h = '" + jsonMember1h + '\'' + 
			"}";
		}
}