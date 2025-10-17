package com.nic.aishe.master.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.PersonCategoryCount;
@Repository
public interface PersonCountRepo extends JpaRepository<PersonCategoryCount, Integer> {
	
	@Query(value="select max(id) from persons_count_by_category",nativeQuery = true)
	Integer maxid();

}