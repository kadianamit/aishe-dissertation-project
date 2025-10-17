package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRapo extends JpaRepository<RefLanguage, Integer> {
}
