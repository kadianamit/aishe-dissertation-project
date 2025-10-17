package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.aishe.master.entity.RefStateBodyType;

public interface RefStateBodyTypeRapo extends JpaRepository<RefStateBodyType, Integer> {

	@Query(value = "select * from ref_state_body_type c  where c.state_body_id =:stateBodyId ", nativeQuery = true)
	List<RefStateBodyType> findByStateBodyId(Integer stateBodyId);
}
