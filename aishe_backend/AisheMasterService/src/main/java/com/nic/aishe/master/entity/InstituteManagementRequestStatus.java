package com.nic.aishe.master.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "ref_institution_management_request_status")
public class InstituteManagementRequestStatus {
    @Id
    private Integer id;
    private String name;
}
