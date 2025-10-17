package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.JobStatus;
@Repository
public interface JobStatusRepo extends JpaRepository<JobStatus, Integer> {
	@Query(value="select * from ref_job_status where status is true",nativeQuery = true)
	List<JobStatus> findAllJobStatusApplicable();


}