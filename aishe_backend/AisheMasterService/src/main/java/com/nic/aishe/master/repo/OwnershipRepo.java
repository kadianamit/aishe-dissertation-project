package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.Ownership;

import java.util.List;

@Repository
public interface OwnershipRepo extends JpaRepository<Ownership, String> {
    @Query(value = "select * from ref_institution_management where active is true and valid_for_standalone is true order by id",nativeQuery = true)
	List<Ownership> findAllValidForStandalone();
    @Query(value = "select * from ref_institution_management where active is true and valid_for_college is true  order by id",nativeQuery = true)
    List<Ownership> findAllValidForCollege();
    @Query(value = "select * from ref_institution_management where id=:id and active is true order by id ",nativeQuery = true)
    Ownership getById(@Param("id") String id);
}
