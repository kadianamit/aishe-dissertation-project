package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.RefNirfDisciplineCategoryEO;
@Repository
public interface RefNirfDisciplineCategoryRepo extends JpaRepository<RefNirfDisciplineCategoryEO, Integer> {

}