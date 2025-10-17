package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.user_master")
public class UsersEO implements Serializable {

    private static final long serialVersionUID = 3407886935652866895L;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "bcrypt_password")
    private String bcryptPassword;

    @Column(name="is_password_change")
	private Boolean isPasswordChange;
    
    @Column(name="password_change_date")
	private LocalDateTime passwordChangeDate;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBcryptPassword() {
        return bcryptPassword;
    }

    public void setBcryptPassword(String bcryptPassword) {
        this.bcryptPassword = bcryptPassword;
    }

	public Boolean getIsPasswordChange() {
		return isPasswordChange;
	}

	public void setIsPasswordChange(Boolean isPasswordChange) {
		this.isPasswordChange = isPasswordChange;
	}

	public LocalDateTime getPasswordChangeDate() {
		return passwordChangeDate;
	}

	public void setPasswordChangeDate(LocalDateTime passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
	}

}