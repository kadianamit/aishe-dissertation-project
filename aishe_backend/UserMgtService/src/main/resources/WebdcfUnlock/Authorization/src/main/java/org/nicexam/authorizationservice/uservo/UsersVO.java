package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class UsersVO implements Serializable {

	private static final long serialVersionUID = 6706648113918049065L;
    //Users
	private Long Id;
	
	private Long registeredSiteId;

	private String loginId;
	
	private String name;
	
	private String password;
	
	private String passwordTwo;

	private String passwordThree;
	
	private VernacularNativeVO vernacularName;
	//Users Contact
	
    private Long userId;
	
	private String primaryEmailAddress;
	
	private String recoveryEmailAddress;

	private String primaryMobileNumber;
	
	private String recoveryMobileNumber;
	
	private String registeredSiteName;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public Long getRegisteredSiteId() {
		return registeredSiteId;
	}

	public void setRegisteredSiteId(Long registeredSiteId) {
		this.registeredSiteId = registeredSiteId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordTwo() {
		return passwordTwo;
	}

	public void setPasswordTwo(String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}

	public String getPasswordThree() {
		return passwordThree;
	}

	public void setPasswordThree(String passwordThree) {
		this.passwordThree = passwordThree;
	}

	public VernacularNativeVO getVernacularName() {
		return vernacularName;
	}

	public void setVernacularName(VernacularNativeVO vernacularName) {
		this.vernacularName = vernacularName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPrimaryEmailAddress() {
		return primaryEmailAddress;
	}

	public void setPrimaryEmailAddress(String primaryEmailAddress) {
		this.primaryEmailAddress = primaryEmailAddress;
	}

	public String getRecoveryEmailAddress() {
		return recoveryEmailAddress;
	}

	public void setRecoveryEmailAddress(String recoveryEmailAddress) {
		this.recoveryEmailAddress = recoveryEmailAddress;
	}

	public String getPrimaryMobileNumber() {
		return primaryMobileNumber;
	}

	public void setPrimaryMobileNumber(String primaryMobileNumber) {
		this.primaryMobileNumber = primaryMobileNumber;
	}

	public String getRecoveryMobileNumber() {
		return recoveryMobileNumber;
	}

	public void setRecoveryMobileNumber(String recoveryMobileNumber) {
		this.recoveryMobileNumber = recoveryMobileNumber;
	}

	public String getRegisteredSiteName() {
		return registeredSiteName;
	}

	public void setRegisteredSiteName(String registeredSiteName) {
		this.registeredSiteName = registeredSiteName;
	}	
}