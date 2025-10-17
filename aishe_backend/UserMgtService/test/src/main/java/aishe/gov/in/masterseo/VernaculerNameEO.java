package aishe.gov.in.masterseo;

import java.util.List;

import javax.persistence.Column;

public class VernaculerNameEO {
  @Column
  private List language;
  @Column
  private String name;
  @Column
  private String shortName;
public List getLanguage() {
	return language;
}
public void setLanguage(List language) {
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
