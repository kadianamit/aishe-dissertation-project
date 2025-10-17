package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ref_bank_account_type")
public class BankAccountType{

	@Id
	private Integer id;

	@Column(name="type")
	private String type;

}