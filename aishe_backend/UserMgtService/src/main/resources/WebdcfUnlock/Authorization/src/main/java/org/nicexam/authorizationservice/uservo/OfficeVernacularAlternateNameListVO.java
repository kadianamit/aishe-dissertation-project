package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class OfficeVernacularAlternateNameListVO implements Serializable {

	private static final long serialVersionUID = -985450361399604528L;

	private String language_id;

	private String alternate_native_name;

	public String getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}

	public String getAlternate_native_name() {
		return alternate_native_name;
	}

	public void setAlternate_native_name(String alternate_native_name) {
		this.alternate_native_name = alternate_native_name;
	}
}
