package com.nic.aishe.master.impl;

import com.nic.aishe.master.dto.InstitutionTypeDto;
import com.nic.aishe.master.entity.CollegeInfo;
import com.nic.aishe.master.entity.CollegeInfoData;
import com.nic.aishe.master.entity.Ministry;
import com.nic.aishe.master.entity.NodalOfficer;
import com.nic.aishe.master.entity.RefAccreditationCycle;
import com.nic.aishe.master.entity.RefMinorityCommunityType;
import com.nic.aishe.master.entity.RefNaacGrades;
import com.nic.aishe.master.entity.RefStandAloneType;
import com.nic.aishe.master.entity.RefStatusPriorEstablishment;
import com.nic.aishe.master.entity.RefUniversityCollegeType;
import com.nic.aishe.master.entity.RefUniversityType;
import com.nic.aishe.master.entity.StaffQuarter;
import com.nic.aishe.master.entity.TeacherDesignation;
import com.nic.aishe.master.repo.CollegeRepo;
import com.nic.aishe.master.repo.CollegeRepoData;
import com.nic.aishe.master.repo.Hostelrepo;
import com.nic.aishe.master.repo.MinistryRepo;
import com.nic.aishe.master.repo.NodalOfficerRepo;
import com.nic.aishe.master.repo.RefAccrediationCycleRepo;
import com.nic.aishe.master.repo.RefMinorityCommunityTypeRepo;
import com.nic.aishe.master.repo.RefNaacGradeRepo;
import com.nic.aishe.master.repo.RefStandAloneTypeRepo;
import com.nic.aishe.master.repo.RefStatusPriorEstablishmentRepo;
import com.nic.aishe.master.repo.RefUniversityCollegeTypeRepo;
import com.nic.aishe.master.repo.RefUniversityTypeRepo;
import com.nic.aishe.master.repo.StaffQuarterRepo;
import com.nic.aishe.master.repo.TeacherDesignationRepo;
import com.nic.aishe.master.service.CollegeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired(required=false)
    private CollegeRepo collegeRepo;
    @Autowired(required=false)
    private Hostelrepo hostelrepo;
    @Autowired(required=false)
    private NodalOfficerRepo nodalOfficerRepo;
    @Autowired(required=false)
    MinistryRepo ministryRepo;
    @Autowired(required=false)
    private TeacherDesignationRepo teacherDesignationRepo;
    @Autowired(required=false)
    private RefUniversityCollegeTypeRepo refUniversityCollegeTypeRepo;
    @Autowired(required=false)
    private RefUniversityTypeRepo refUniversityTypeRepo;
    @Autowired(required=false)
    private RefStandAloneTypeRepo refStandAloneTypeRepo;
    @Autowired(required=false)
    RefAccrediationCycleRepo refAccrediationCycleRepo;
    @Autowired(required=false)
    RefNaacGradeRepo refNaacGradeRepo;
    @Autowired(required=false)
    RefStatusPriorEstablishmentRepo refStatusPriorEstablishment;
    @Autowired(required=false)
    RefMinorityCommunityTypeRepo refMinorityCommunityTypeRepo;
    @Autowired(required=false)
    StaffQuarterRepo staffQuarterRepo;
    @Autowired(required=false)
    private CollegeRepoData collegeRepoData;
    @Override
    public CollegeInfo findCollegeBySurveyYearAndAisheCode(Integer year, Integer aishecode) {
        return collegeRepo.findBySurveyYearAndId(year, aishecode);
    }

    @Override
    public Page<CollegeInfo> findAllCollege(int page,int size) {
        return collegeRepo.findAll(PageRequest.of(page, size));
    }

    @Override
    public CollegeInfo updateCollegeAddress(CollegeInfo collegeInfo) {
        return collegeRepo.save(collegeInfo);
    }

    @Override
    public List<CollegeInfo> findCollegeByAisheCode(Integer id) {
        return collegeRepo.findAllById(id);
    }

    @Override
    public CollegeInfo  addCollege(CollegeInfo collegeInfo) {
        collegeInfo.getHostels().forEach(h->{
            if(h.getId()!=0) {
                hostelrepo.save(h);
            }else if(h.getId()==0) {
                Integer maxid2 = hostelrepo.maxid();
                h.setId(maxid2+1);
                hostelrepo.save(h);
            }

        });
        if(collegeInfo.getNodalOfficer().getId()==0 || collegeInfo.getNodalOfficer()!=null) {
            Integer maxvalue = nodalOfficerRepo.maxvalue();
            NodalOfficer nodalOfficer = collegeInfo.getNodalOfficer();
            nodalOfficer.setId(maxvalue+1);
            nodalOfficerRepo.save(nodalOfficer);
            collegeInfo.setNodalOfficer(nodalOfficer);
        }

//		State state=new State();
//		state.setStateCode("09");
//		District district=new District();
//		district.setDistCode("146");
//		collegeInfo.setStateCode(state);
//		collegeInfo.setDistrictCode(district);
        collegeInfo.setHasOtherMinority(collegeInfo.getHasOtherMinority());
        collegeInfo.setAisheCode(collegeInfo.getAisheCode());
        collegeInfo.setAccredited(collegeInfo.getAccredited());
        //FinanceIncome financeIncome = new FinanceIncome();
        //financeIncome.setTotal(0.0);

        //Expenditure expenditure = new Expenditure();
        //expenditure.setTotal(0.0);

        //Infrastructure infrastructure = new Infrastructure();
        collegeInfo.setEvening(collegeInfo.getEvening());
        collegeInfo.addInfrastructure(collegeInfo.getInfrastructure());
        collegeInfo.addFinanceIncome(collegeInfo.getFinanceIncome());
        collegeInfo.addExpenditure(collegeInfo.getExpenditure());
        if(collegeInfo.getStaffQuarter().getId()==0) {
            Integer maxidsq = staffQuarterRepo.maxvalue();
            StaffQuarter sq = new StaffQuarter();
            sq.setId(maxidsq+1);
            sq.setTeachingStaff(collegeInfo.getStaffQuarter().getTeachingStaff());
            sq.setNonTeachingStaff(collegeInfo.getStaffQuarter().getNonTeachingStaff());
            sq.setTotal(collegeInfo.getStaffQuarter().getTotal());
            staffQuarterRepo.save(sq);
            collegeInfo.setStaffQuarter(sq);
        }
        //collegeInfo.setId(collegeInfo.getAisheCode());
        collegeInfo.setOwnershipStatus(collegeInfo.getOwnershipStatus());
        collegeInfo.setInstituteManagement(collegeInfo.getInstituteManagement());
        collegeInfo.setWhetherAwardedByUniversity(collegeInfo.getWhetherAwardedByUniversity());
        collegeInfo.setWhetherVillageAdoptedUnderUnnatBharat(collegeInfo.getWhetherVillageAdoptedUnderUnnatBharat());
        collegeInfo.setAwardedByUniversityId(collegeInfo.getAwardedByUniversityId());
        collegeInfo.setHasDiplomaCourses(collegeInfo.getHasDiplomaCourses());
        collegeInfo.setOtherDiploma(collegeInfo.getOtherDiploma());
        if(collegeInfo.getSpecialized()==false) {
            collegeInfo.setSpeciality(null);
            collegeInfo.setSpecialized(false);
            collegeInfo.setOtherSpeciality("");
        }
        collegeInfo.setInstituteType(collegeInfo.getInstituteType());
        collegeInfo.setStatutoryBody(collegeInfo.getStatutoryBody());
        collegeInfo.setOtherStatutoryBody(collegeInfo.getOtherStatutoryBody());
        collegeInfo.setStatusPriorToEstablishment(collegeInfo.getStatusPriorToEstablishment());
        collegeInfo.setIsMinorityManagedInstitution(collegeInfo.getIsMinorityManagedInstitution());
        collegeInfo.setTypeMinorityCommunityManagingId(collegeInfo.getTypeMinorityCommunityManagingId());
        collegeInfo.setIsNCC(collegeInfo.getIsNCC());
        collegeInfo.setFemaleEnrolledNCC(collegeInfo.getFemaleEnrolledNCC());
        collegeInfo.setTotalEnrolledNCC(collegeInfo.getTotalEnrolledNCC());
        collegeInfo.setIsNSS(collegeInfo.getIsNSS());
        collegeInfo.setFemaleEnrolledNSS(collegeInfo.getFemaleEnrolledNSS());
        collegeInfo.setTotalEnrolledNSS(collegeInfo.getTotalEnrolledNSS());
        collegeInfo.setStudentEnrolledNCC(collegeInfo.getStudentEnrolledNCC());
        collegeInfo.setStudentEnrolledNSS(collegeInfo.getStudentEnrolledNSS());
        collegeInfo.setGirl_exclusive(collegeInfo.getGirl_exclusive());
        collegeInfo.setSpecialized(collegeInfo.getSpecialized());
        collegeInfo.setAutonomous(collegeInfo.getAutonomous());
        collegeInfo.setBoysExclusive(collegeInfo.getBoysExclusive());
        collegeInfo.setOffersLoan(collegeInfo.getOffersLoan());
        collegeInfo.setOffersScholarship(collegeInfo.getOffersScholarship());
        collegeInfo.setHasFellowships(collegeInfo.getHasFellowships());
        return collegeRepo.save(collegeInfo);
    }

    @Override
    public CollegeInfo updateCollege(Integer aishecode, CollegeInfo collegeInfo,CollegeInfo college) {
        //	CollegeInfo college = collegeRepo.findBySurveyYearAndId(collegeInfo.getSurveyYear(), aishecode);
        if (college != null) {
              collegeInfo.getHostels().forEach(h->{
                if(h.getId()!=0) {
                    hostelrepo.save(h);
                }else if(h.getId()==0) {
                    Integer maxid2 = hostelrepo.maxid();
                    h.setId(maxid2+1);
                    hostelrepo.save(h);
                }
            });
            if(collegeInfo.getSpecialized()==false) {
                college.setOtherSpeciality(null);
            }else {
                college.setOtherSpeciality(collegeInfo.getOtherSpeciality());
            }
            college.setEvening(collegeInfo.getEvening());
            college.setAisheCode(collegeInfo.getAisheCode());
            //college.setWebsite(collegeInfo.getWebsite());
            college.setName(collegeInfo.getName());
            college.setEstablishmentYear(collegeInfo.getEstablishmentYear());
            //	college.setConstructedArea(collegeInfo.getConstructedArea());
            //	college.setLocation(collegeInfo.getLocation());
            // college.setAwardsDegreeThroughUniversity();
            college.setGirl_exclusive(collegeInfo.getGirl_exclusive());
            college.setSurveyYear(collegeInfo.getSurveyYear());
            college.setUniversityId(collegeInfo.getUniversityId());

            if(collegeInfo.getNodalOfficer().getId()!=0 || collegeInfo.getNodalOfficer()!=null) {
                //Integer maxvalue = nodalOfficerRepo.maxvalue();
                NodalOfficer nodalOfficer = collegeInfo.getNodalOfficer();
                nodalOfficer.setId(collegeInfo.getNodalOfficer().getId());
                nodalOfficerRepo.save(nodalOfficer);
                collegeInfo.setNodalOfficer(nodalOfficer);
            }
            college.setNodalOfficer(collegeInfo.getNodalOfficer());
            // college.setRecognitionYear(collegeInfo.getRecognitionYear());
            // setInstituteManagement(collegeInfo.getInstituteManagement());

            college.setHaveStaffQuarter(collegeInfo.getHaveStaffQuarter());
//			if(collegeInfo.getHaveStaffQuarter()==false) {
//				StaffQuarter sq= college.getStaffQuarter();
//				sq.setNonTeachingStaff(0);
//				sq.setTeachingStaff(0);
//				sq.setTotal(0);
//				sq.setId(college.getStaffQuarter().getId());
//				staffQuarterRepo.save(sq);
//				college.setStaffQuarter(sq);
//			}else {
//			StaffQuarter sq = new StaffQuarter();
//			sq.setTeachingStaff(collegeInfo.getStaffQuarter().getTeachingStaff());
//			sq.setNonTeachingStaff(collegeInfo.getStaffQuarter().getNonTeachingStaff());
//			sq.setTotal(collegeInfo.getStaffQuarter().getTotal());
//			sq.setId(collegeInfo.getStaffQuarter().getId());
//			collegeInfo.setStaffQuarter(sq);

            if(collegeInfo.getStaffQuarter().getId()!=0) {
                StaffQuarter sq = new StaffQuarter();
                sq.setId(collegeInfo.getStaffQuarter().getId());
                sq.setTeachingStaff(collegeInfo.getStaffQuarter().getTeachingStaff());
                sq.setNonTeachingStaff(collegeInfo.getStaffQuarter().getNonTeachingStaff());
                sq.setTotal(collegeInfo.getStaffQuarter().getTotal());
                staffQuarterRepo.save(sq);
                collegeInfo.setStaffQuarter(sq);
            }

            college.setStaffQuarter(collegeInfo.getStaffQuarter());
            //}

            college.setHaveHostel(collegeInfo.getHaveHostel());
            college.setNoOfHostel(collegeInfo.getNoOfHostel());
            college.setHostels(collegeInfo.getHostels());
            // college.setMinistry(collegeInfo.getMinistry());
            college.setRemarks(collegeInfo.getRemarks());
            college.setAccredited(collegeInfo.getAccredited());
            college.setIsForeignStudents(collegeInfo.getIsForeignStudents());
            college.setOffersScholarship(collegeInfo.getOffersScholarship());
            college.setOffersLoan(collegeInfo.getOffersLoan());
            //college.setPinCode(collegeInfo.getPinCode());
            college.setHasFellowships(collegeInfo.getHasFellowships());
            college.setHasOtherMinority(collegeInfo.getHasOtherMinority());
            //college.setBlockCityTown(collegeInfo.getBlockCityTown());
            college.setHasForeignTeachers(collegeInfo.getHasForeignTeachers());
            college.setOtherAffiliatedUniversityId(collegeInfo.getOtherAffiliatedUniversityId());
            college.setAffiliationYear(collegeInfo.getAffiliationYear());
//			college.setAddressline1(collegeInfo.getAddressline1());
//			college.setAddressline2(collegeInfo.getAddressline2());
//			college.setArea(collegeInfo.getArea());
//			college.setCity(collegeInfo.getCity());
//			college.setConstructedArea(collegeInfo.getConstructedArea());
//			college.setBlockCityTown(collegeInfo.getBlockCityTown());
//			college.setLatitude(collegeInfo.getLatitude());
//			college.setLongitude(collegeInfo.getLongitude());
//			college.setStateCode(collegeInfo.getStateCode());
//			college.setDistrictCode(collegeInfo.getDistrictCode());
            //		college.setWebsite(collegeInfo.getWebsite());
            college.setHasOtherMinority(collegeInfo.getHasOtherMinority());
            college.setOwnershipStatus(collegeInfo.getOwnershipStatus());
            college.setInstituteManagement(collegeInfo.getInstituteManagement());
            college.setWhetherAwardedByUniversity(collegeInfo.getWhetherAwardedByUniversity());
            college.setAwardedByUniversityId(collegeInfo.getAwardedByUniversityId());
            college.setOtherDiploma(collegeInfo.getOtherDiploma());
            college.setHasDiplomaCourses(collegeInfo.getHasDiplomaCourses());
            college.setWhetherVillageAdoptedUnderUnnatBharat(collegeInfo.getWhetherVillageAdoptedUnderUnnatBharat());
            if(collegeInfo.getSpecialized()==false) {
                college.setSpeciality(null);
                college.setSpecialized(false);
                college.setOtherSpeciality("");
            }
            college.setInstituteType(collegeInfo.getInstituteType());
            college.setStatutoryBody(collegeInfo.getStatutoryBody());
            college.setOtherStatutoryBody(collegeInfo.getOtherStatutoryBody());
            college.setStatusPriorToEstablishment(collegeInfo.getStatusPriorToEstablishment());
            college.setIsMinorityManagedInstitution(collegeInfo.getIsMinorityManagedInstitution());
            college.setTypeMinorityCommunityManagingId(collegeInfo.getTypeMinorityCommunityManagingId());
            college.setIsNCC(collegeInfo.getIsNCC());
            college.setFemaleEnrolledNCC(collegeInfo.getFemaleEnrolledNCC());
            college.setTotalEnrolledNCC(collegeInfo.getTotalEnrolledNCC());
            college.setIsNSS(collegeInfo.getIsNSS());
            college.setFemaleEnrolledNSS(collegeInfo.getFemaleEnrolledNSS());
            college.setTotalEnrolledNSS(collegeInfo.getTotalEnrolledNSS());
            college.setStudentEnrolledNCC(collegeInfo.getStudentEnrolledNCC());
            college.setStudentEnrolledNSS(collegeInfo.getStudentEnrolledNSS());
            college.setAutonomous(collegeInfo.getAutonomous());
            college.setGirl_exclusive(collegeInfo.getGirl_exclusive());
            college.setSpecialized(collegeInfo.getSpecialized());
            college.setBoysExclusive(collegeInfo.getBoysExclusive());
            //staffQuarterRepo.save(sq);
            collegeInfo.setAutonomous(collegeInfo.getAutonomous());
            college=collegeRepo.save(college);
            return college;
        }

        return null;
    }

    public List<Ministry> fetchAllMinistry(Integer id) {
        List<Ministry> ministryList = ministryRepo.findAllMinistry();
        return ministryList;
    }

    public List<TeacherDesignation> findAllTeacherDesignation() {
        return teacherDesignationRepo.findAll();
    }


    public List<InstitutionTypeDto> findAllInstitutionType(String type) {
        List<InstitutionTypeDto> dto = new ArrayList<InstitutionTypeDto>();
        switch (type) {
            case "U":
                List<RefUniversityType> uteo= refUniversityTypeRepo.findAll();
                for(int i=0;i<uteo.size();i++) {
                    InstitutionTypeDto dtos = new InstitutionTypeDto();
                    BeanUtils.copyProperties(uteo.get(i), dtos);
                    dto.add(dtos);
                }
                break;
            case "C":
                List<RefUniversityCollegeType> rfct= refUniversityCollegeTypeRepo.findAll();
                for(int i=0;i<rfct.size();i++) {
                    InstitutionTypeDto dtosc = new InstitutionTypeDto();
                    BeanUtils.copyProperties(rfct.get(i), dtosc);
                    dto.add(dtosc);
                }
                break;
            case "S":
                List<RefStandAloneType> rfsalntype= refStandAloneTypeRepo.findAll();
                for(int i=0;i<rfsalntype.size();i++) {
                    InstitutionTypeDto dtosc = new InstitutionTypeDto();
                    BeanUtils.copyProperties(rfsalntype.get(i), dtosc);
                    dto.add(dtosc);
                }
                break;
            default:
                break;
        }

        return dto;
    }

    public List<RefNaacGrades> fetchAllNaccGrades() {
        List<RefNaacGrades> ministryList = refNaacGradeRepo.findAll();
        return ministryList;
    }

    public List<RefAccreditationCycle> fetchAllAccrediationCycle() {
        List<RefAccreditationCycle> ministryList = refAccrediationCycleRepo.findAll();
        return ministryList;
    }

    public List<RefStatusPriorEstablishment> fetchAllRefStatusPriorEstablishmentList() {
        List<RefStatusPriorEstablishment> ministryList = refStatusPriorEstablishment.findAll();
        return ministryList;
    }

    public List<RefMinorityCommunityType> fetchAllMinorityCommunityType() {
        List<RefMinorityCommunityType> ministryList = refMinorityCommunityTypeRepo.findAll();
        return ministryList;
    }

    public CollegeInfoData findCollegeBySurveyYearAndAisheCodeData(Integer surveyYear, Integer collegeId) {
        return collegeRepoData.findBySurveyYearAndId(surveyYear, collegeId);
    }



}
