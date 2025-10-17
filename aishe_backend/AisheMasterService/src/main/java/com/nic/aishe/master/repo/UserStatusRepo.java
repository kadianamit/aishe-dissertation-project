package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.UserRole;
import com.nic.aishe.master.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusRepo extends JpaRepository<UserStatus, Integer> {

}
