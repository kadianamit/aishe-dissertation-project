package aishe.gov.in.dao;

import aishe.gov.in.masterseo.UniversityEO;


public interface UniversityDao {
	
	public UniversityEO getUniversityDetail(String universityId,Integer surveyYear);
	
	public boolean saveOrUpdateUniversityBasicInformation(UniversityEO university);

	public Integer saveOrUpdateNodalOfficer(NodalOfficerEO nodalOfficer,String status);

	public Integer saveOrUpdateStudentHostel(StudentHostelEO studentHostel,String status);

	public RefStudentHostelType getHostelType(String type);

	public Boolean saveOrUpdateUniversityHostel(UniversityHostelEO universityHostelEO);

	public Integer saveOrUpdateStaffQuarter(StaffQuarterEO staffQuarter,String status);

	public Boolean getUniversityStudentHostel(String universityId, Integer surveyYear);

	public StudentHostelEO getStudentHostel(Integer id);	

	public Boolean saveOrUpdateUniversityStudentHostel(UniversityStudentHostelEO universityStudentHostelEO);

	boolean saveOrUpdateUniversityAddress(UniversityEO university);

	public StaffQuarterEO getStaffQuarter(Integer staffQuarterId);

	public Integer saveStaffQuarter(StaffQuarterEO staffQuarter,String status);

}
