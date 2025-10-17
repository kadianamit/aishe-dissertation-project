package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class VernacularNativeVO implements Serializable {


	private static final long serialVersionUID = -180073090447892868L;

	private String language_id;

	private String native_name;

	public String getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}

	public String getNative_name() {
		return native_name;
	}

	public void setNative_name(String native_name) {
		this.native_name = native_name;
	}





}
