package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeachingVernacularNamesVO {
	@JsonProperty
	private String language;
	@JsonProperty
	private String name;
	@JsonProperty
	private String shortName;
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
