package aishe.gov.in.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.masterseo.RefSpecialityEO;
import aishe.gov.in.masterseo.UniversityEO;


@Service
public class UniversityServiceImpl implements UniversityService {
	
	private static final Logger logger = LoggerFactory.getLogger(UniversityService.class);
	@Autowired
	UniversityDao universityDao;

	@Override
	public boolean saveOrUpdateUniversityBasicInformation(UniversityVO university) {
		logger.info("university serviceImpl : saveOrUpdateUniversityBasicInformation method invoked : {}");
		UniversityEO universityEO = new UniversityEO();
		if (university != null) {
			NodalOfficerEO nodalOfficer = null;
			StaffQuarterEO staffQuarter = null;
			universityEO.setId(university.getAisheCode());
			universityEO.setName(university.getName());
			universityEO.setEstablishmentYear(university.getEstablishmentYear());
			universityEO.setYearWhenDeclaredUniversity(university.getYearWhenDeclaredUniversity());
			universityEO.setOtherSpeciality(university.getOtherSpeciality());
			UniversityEO universityEO2 = universityDao.getUniversityDetail(university.getAisheCode(),university.getSurveyYear());
			if(university.getSpeciality().getId().equalsIgnoreCase("0")) {
				
			}
			else {
				RefSpecialityEO specialityEO=university.getSpeciality();
				universityEO2.setSpeciality(specialityEO);
			}
			if (universityEO2.getNodalOfficer().getId() != null) {
				universityDao.saveOrUpdateNodalOfficer(university.getNodalOfficer(), "update");
				nodalOfficer = university.getNodalOfficer();
			} else {
				nodalOfficer = new NodalOfficerEO();
				nodalOfficer.setName(university.getNodalOfficer().getName());
				nodalOfficer.setDesignation(university.getNodalOfficer().getDesignation());
				nodalOfficer.setMobile(university.getNodalOfficer().getMobile());
				nodalOfficer.setTelephone(university.getNodalOfficer().getTelephone());
				nodalOfficer.setEmail(university.getNodalOfficer().getEmail());
				nodalOfficer.setId(universityDao.saveOrUpdateNodalOfficer(nodalOfficer, "save"));
			}
			if(university.getHaveStaffQuarter()) {
				staffQuarter=universityDao.getStaffQuarter(university.getStaffQuarter().getId());
				String status="save";
				if(staffQuarter!=null) {
					status="update";
				}
				staffQuarter.setId(university.getStaffQuarter().getId());
				staffQuarter.setNonTeachingStaff(university.getStaffQuarter().getNonTeachingStaff());
				staffQuarter.setTeachingStaff(university.getStaffQuarter().getTeachingStaff());
				staffQuarter.setTotal(university.getStaffQuarter().getTotal());
				Integer staffQuarterId=universityDao.saveStaffQuarter(staffQuarter,status);
				staffQuarter.setId(staffQuarterId);
			}
			universityEO.setNodalOfficer(nodalOfficer);
			universityEO.setSpecialized(university.getSpecialized());
			universityEO.setGirl_exclusive(university.getGirl_exclusive());
			universityEO.setHaveStaffQuarter(university.getHaveStaffQuarter());
			universityEO.setStaffQuarter(staffQuarter);
			universityEO.setHaveHostel(university.getHaveHostel());
			universityEO.setNoOfHostel(university.getNoOfHostel());
			if (university.getHaveHostel()) {
				List<StudentHostelEO> studentHostelEOList = university.getHostels();
				Boolean hostelStatus = universityDao.getUniversityStudentHostel(university.getAisheCode(),university.getSurveyYear());
				for (StudentHostelEO studentHostelEO : studentHostelEOList) {
					if (hostelStatus) {
						StudentHostelEO studentHostel = universityDao.getStudentHostel(studentHostelEO.getId());
						if (studentHostel != null) {
							universityDao.saveOrUpdateStudentHostel(studentHostelEO, "update");
						} else {
							Integer hostelId = universityDao.saveOrUpdateStudentHostel(studentHostelEO, "save");
							UniversityStudentHostelEO universityStudentHostelEO = new UniversityStudentHostelEO();
							universityStudentHostelEO.setUniversityId(university.getAisheCode());
							universityStudentHostelEO.setSurveyYear(university.getSurveyYear());
							universityStudentHostelEO.setStudentHostelId(hostelId);
							universityDao.saveOrUpdateUniversityStudentHostel(universityStudentHostelEO);
						}
					} else {
						Integer hostelId = universityDao.saveOrUpdateStudentHostel(studentHostelEO, "save");
						UniversityStudentHostelEO universityStudentHostelEO = new UniversityStudentHostelEO();
						universityStudentHostelEO.setUniversityId(university.getAisheCode());
						universityStudentHostelEO.setSurveyYear(university.getSurveyYear());
						universityStudentHostelEO.setStudentHostelId(hostelId);
						universityDao.saveOrUpdateUniversityStudentHostel(universityStudentHostelEO);
					}
				}
			}
		}
		return universityDao.saveOrUpdateUniversityBasicInformation(universityEO);
	}

	@Override
	public boolean saveOrUpdateUniversityAddress(UniversityEO university) {
		// TODO Auto-generated method stub
		return universityDao.saveOrUpdateUniversityAddress(university);
	}

	}
