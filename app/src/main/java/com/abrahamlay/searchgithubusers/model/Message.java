package com.abrahamlay.searchgithubusers.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Message{

	@SerializedName("message")
	private String message;

	@SerializedName("documentation_url")
	private String documentationUrl;

	@SerializedName("errors")
	private List<ErrorsItem> errors;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setDocumentationUrl(String documentationUrl){
		this.documentationUrl = documentationUrl;
	}

	public String getDocumentationUrl(){
		return documentationUrl;
	}

	public void setErrors(List<ErrorsItem> errors){
		this.errors = errors;
	}

	public List<ErrorsItem> getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"Message{" + 
			"message = '" + message + '\'' + 
			",documentation_url = '" + documentationUrl + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}