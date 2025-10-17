package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class OfficeAcronymListVO implements Serializable {

	private static final long serialVersionUID = -727083395490987082L;

	private String language_id;

	private String native_acronym;

	public String getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}

	public String getNative_acronym() {
		return native_acronym;
	}

	public void setNative_acronym(String native_acronym) {
		this.native_acronym = native_acronym;
	}
}
