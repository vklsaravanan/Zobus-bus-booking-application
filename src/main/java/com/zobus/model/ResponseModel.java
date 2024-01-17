package com.zobus.model;

public class ResponseModel {
	String status = "error";
	Object data = null;
	Object error = null;
	
	public ResponseModel(String status, Object data, Object error) {
		this.status = status;
		this.data = data;
		this.error = error;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public void setError(Object error) {
		this.error = error;
	}
	
	
}