package aishe.gov.in.masterseo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "data_sharing.user_master")
@Data
public class DataSharingUsersEO implements Serializable {

    private static final long serialVersionUID = 3407886935652866895L;

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;




}