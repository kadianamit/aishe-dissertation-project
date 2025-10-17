package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefNirfCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefNirfCategoryRapo extends JpaRepository<RefNirfCategory, Integer> {
}
