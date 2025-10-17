package com.nic.aishe.master.dto;

import java.util.LinkedHashMap;

public class ServiceResponse {

	private LinkedHashMap<Object, Object> serviceResponse;

	public LinkedHashMap<Object, Object> getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(LinkedHashMap<Object, Object> serviceResponse) {
		this.serviceResponse = serviceResponse;
	}

	public String toString() {
		return serviceResponse.toString();
	}

}