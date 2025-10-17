package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.InstituteManagement;
import com.nic.aishe.master.entity.InstituteManagementRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteManagementRequestStatusRepo extends JpaRepository<InstituteManagementRequestStatus, Integer> {

}