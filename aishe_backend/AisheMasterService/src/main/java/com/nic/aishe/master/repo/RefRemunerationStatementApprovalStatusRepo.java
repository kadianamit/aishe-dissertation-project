package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefRemunerationStatementApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefRemunerationStatementApprovalStatusRepo extends JpaRepository<RefRemunerationStatementApprovalStatus, Integer> {
}
