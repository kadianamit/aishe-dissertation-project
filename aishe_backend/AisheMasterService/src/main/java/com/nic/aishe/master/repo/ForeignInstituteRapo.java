package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.ForeignInstitute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForeignInstituteRapo extends JpaRepository<ForeignInstitute, Integer> {
    @Query(value = "SELECT * FROM Foreign_Institute WHERE country_id=:countryId order by id", nativeQuery = true)
    List<ForeignInstitute> findAllByCountryId(String countryId);

    @Query(value = "SELECT * FROM Foreign_Institute", nativeQuery = true)
	List<ForeignInstitute> findAllByCountry();
    @Query(value = "SELECT * FROM Foreign_Institute where status_id=:status ", nativeQuery = true)
    List<ForeignInstitute> findAllByCountryByStatus(Integer status);
    @Query(value = "SELECT * FROM Foreign_Institute WHERE country_id=:countryId and status_id=:status order by id", nativeQuery = true)
    List<ForeignInstitute> findAllByCountryIdAndStatus(String countryId, Integer status);
}
