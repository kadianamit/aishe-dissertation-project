package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefRemunerationTransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefRemunerationTransactionStatusRepo extends JpaRepository<RefRemunerationTransactionStatus, Integer> {
}
