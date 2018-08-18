package com.abrahamlay.searchgithubusers.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ErrorsItem{

	@SerializedName("code")
	private String code;

	@SerializedName("field")
	private String field;

	@SerializedName("resource")
	private String resource;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setField(String field){
		this.field = field;
	}

	public String getField(){
		return field;
	}

	public void setResource(String resource){
		this.resource = resource;
	}

	public String getResource(){
		return resource;
	}

	@Override
 	public String toString(){
		return 
			"ErrorsItem{" + 
			"code = '" + code + '\'' + 
			",field = '" + field + '\'' + 
			",resource = '" + resource + '\'' + 
			"}";
		}
}