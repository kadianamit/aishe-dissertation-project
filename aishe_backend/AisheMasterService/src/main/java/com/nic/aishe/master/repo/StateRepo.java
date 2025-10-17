package com.nic.aishe.master.repo;

import java.util.List;
import java.util.Map;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.dto.ApplicationTypeNameListVO;
import com.nic.aishe.master.entity.State;

@Repository
public interface StateRepo extends JpaRepository<State, Integer> {
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	@Query(value = "SELECT  * from public.ref_state ",  nativeQuery = true)
	List<State> findAllState();


}