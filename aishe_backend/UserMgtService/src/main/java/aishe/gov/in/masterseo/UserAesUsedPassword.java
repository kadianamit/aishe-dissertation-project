package aishe.gov.in.masterseo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity 
@Table(schema="public",name="user_aes_password")
public class UserAesUsedPassword {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="aes_password")
	private String aesPassword;
	
	@Column(name="date_time")
	private LocalDateTime dateTime;

	@Column(name="token")
	private String token;
	
	@Column(name="is_token_expired")
	private boolean isTokenExpired;
}