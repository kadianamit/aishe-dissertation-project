package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class OfficeVernacularShortNameListVO implements Serializable {

	private static final long serialVersionUID = 3728066142055909322L;

	private String language_id;

	private String native_short_name;

	public String getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}

	public String getNative_short_name() {
		return native_short_name;
	}

	public void setNative_short_name(String native_short_name) {
		this.native_short_name = native_short_name;
	}

	
	
	
}
