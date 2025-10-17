package com.nic.aishe.master.dto;

import java.io.Serializable;

public class ApplicationTypeNameListVO implements Serializable {
	
	private static final long serialVersionUID = 5734139589950519136L;

	//private String language_id;
	
	private String native_name="";
	
	private String native_short_name="";
	
	private String native_acronym="";
	
	private String native_previous_name="";

	public String getNative_name() {
		return native_name;
	}

	public void setNative_name(String native_name) {
		this.native_name = native_name;
	}

	public String getNative_short_name() {
		return native_short_name;
	}

	public void setNative_short_name(String native_short_name) {
		this.native_short_name = native_short_name;
	}

	public String getNative_acronym() {
		return native_acronym;
	}

	public void setNative_acronym(String native_acronym) {
		this.native_acronym = native_acronym;
	}

	public String getNative_previous_name() {
		return native_previous_name;
	}

	public void setNative_previous_name(String native_previous_name) {
		this.native_previous_name = native_previous_name;
	}
}
