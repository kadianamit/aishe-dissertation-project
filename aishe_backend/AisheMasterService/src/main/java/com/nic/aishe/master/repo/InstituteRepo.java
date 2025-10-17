package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.InstituteBasicInfo;
@Repository
public interface InstituteRepo extends JpaRepository<InstituteBasicInfo, Integer> {

	InstituteBasicInfo findByAisheCode(String aisheCode);
}