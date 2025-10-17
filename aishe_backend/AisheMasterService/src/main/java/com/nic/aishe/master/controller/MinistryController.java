package com.nic.aishe.master.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.nic.aishe.master.entity.RefInstitutionRankGrade;
import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.repo.RefInstitutionRankGradeRapo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.dto.InstitutionTypeDto;
import com.nic.aishe.master.entity.BankAccountType;
import com.nic.aishe.master.entity.InstituteSpeciality;
import com.nic.aishe.master.entity.Management;
import com.nic.aishe.master.entity.Ministry;
import com.nic.aishe.master.entity.Ownership;
import com.nic.aishe.master.entity.RefAccreditationCycle;
import com.nic.aishe.master.entity.RefBlockEO;
import com.nic.aishe.master.entity.RefDisciplineEO;
import com.nic.aishe.master.entity.RefFeeReimbursementType;
import com.nic.aishe.master.entity.RefForeignStudentType;
import com.nic.aishe.master.entity.RefGovernanceStructure;
import com.nic.aishe.master.entity.RefMappingDesignationNatureAppointment;
import com.nic.aishe.master.entity.RefMinistryOnosEO;
import com.nic.aishe.master.entity.RefMinorityCommunityType;
import com.nic.aishe.master.entity.RefNaacGrades;
import com.nic.aishe.master.entity.RefNepprogramInstituteType;
import com.nic.aishe.master.entity.RefNirfDisciplineCategoryEO;
import com.nic.aishe.master.entity.RefStateBody;
import com.nic.aishe.master.entity.RefStateBodyType;
import com.nic.aishe.master.entity.RefStatusPriorEstablishment;
import com.nic.aishe.master.entity.RefStatutoryBody;
import com.nic.aishe.master.entity.RefSubDistrictEO;
import com.nic.aishe.master.entity.RefTitle;
import com.nic.aishe.master.entity.RefUrbanLocalBodyEO;
import com.nic.aishe.master.impl.CollegeServiceImpl;
import com.nic.aishe.master.impl.InstituteSpecialityService;
import com.nic.aishe.master.repo.BankAccountTypeRepo;
import com.nic.aishe.master.repo.BlockRepo;
import com.nic.aishe.master.repo.ManagmentRepo;
import com.nic.aishe.master.repo.OwnershipRepo;
import com.nic.aishe.master.repo.RefDisciplineRepo;
import com.nic.aishe.master.repo.RefForeignStudentTypeRepo;
import com.nic.aishe.master.repo.RefGovernanceStructureRepo;
import com.nic.aishe.master.repo.RefMappingDesignationNatureAppointmentRepo;
import com.nic.aishe.master.repo.RefMinistryOnosRepo;
import com.nic.aishe.master.repo.RefNirfDisciplineCategoryRepo;
import com.nic.aishe.master.repo.RefStateBodyRapo;
import com.nic.aishe.master.repo.RefStateBodyTypeRapo;
import com.nic.aishe.master.repo.RefStatutoryBodyRepo;
import com.nic.aishe.master.repo.RefSubDistrictRepo;
import com.nic.aishe.master.repo.RefTitleRepo;
import com.nic.aishe.master.repo.RepFeeReimbursementTypeRepo;
import com.nic.aishe.master.repo.RepNepProgramInstituteTypeRepo;
import com.nic.aishe.master.repo.UrbanLocalBodyRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;

@RestController
@RequestMapping
//@CrossOrigin("*")
public class MinistryController {
    @Autowired
    private CollegeServiceImpl collegeServiceImpl;
    @Autowired(required = false)
    private InstituteSpecialityService instituteSpecialityService;
    @Autowired(required = false)
    private OwnershipRepo ownershipRepo;
    @Autowired(required = false)
    private ManagmentRepo managmentRepo;
    @Autowired(required = false)
    private UrbanLocalBodyRepo urbanLocalBodyRepo;
    @Autowired(required = false)
    private BlockRepo blockRepo;
    @Autowired(required = false)
    private RefStateBodyRapo refStateBodyRapo;
    @Autowired(required = false)
    RefStateBodyTypeRapo refStateBodyTypeRapo;
    @Autowired(required = false)
    RefSubDistrictRepo refSubDistrictRepo;


    @Autowired(required = false)
    private BankAccountTypeRepo bankAccountTypeRepo;
    @Autowired(required = false)
    private RefGovernanceStructureRepo refGovernanceStructureRepo;
    @Autowired(required = false)
    private RepNepProgramInstituteTypeRepo repNepProgramInstituteTypeRepo;
    @Autowired(required = false)
    private RepFeeReimbursementTypeRepo repFeeReimbursementTypeRepo;
    @Autowired(required = false)
    private RefTitleRepo repTitleRepo;
    @Autowired(required = false)
    private RefForeignStudentTypeRepo refForeignStudentRepo;
    @Autowired(required = false)
    private RefMappingDesignationNatureAppointmentRepo refMappingDesignationNatureAppointmentRepo;
    @Autowired(required = false)
    RefNirfDisciplineCategoryRepo refNirfDisciplineCategoryRepo;

    @Autowired(required = false)
    RefDisciplineRepo refDisciplineRepo;

    @Autowired(required = false)
    RefStatutoryBodyRepo refStatutoryBodyRepo;
    
    @Autowired(required = false)
    RefMinistryOnosRepo refMinistryOnosRepo;

    @Autowired(required = false)
    RefInstitutionRankGradeRapo rankGradeRapo;

    @GetMapping(value = "/api/ministry", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ministry> fetchAllMinistry(
            @RequestParam(required = false) Integer id/* , @WithUser UserInfo userInfo */) {
        // CommanObjectOperation.usernameValidate(userInfo);
        List<Ministry> officeTypeList = collegeServiceImpl.fetchAllMinistry(id);
        return officeTypeList;
    }

    @GetMapping(value = "/api/speciality", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstituteSpeciality> fetchAllSpeciality(@RequestParam(required = false) Boolean formType, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<InstituteSpeciality> speciality = instituteSpecialityService.getAllSpeciality();
        List<InstituteSpeciality> specialityList = new ArrayList<>();
        for (int i = 0; i < speciality.size(); i++) {
            if (formType == true && speciality.get(i).getValid_for_form1() == true) {
                specialityList.add(speciality.get(i));
            } else if (formType == false && speciality.get(i).getValid_for_form2() == true) {
                specialityList.add(speciality.get(i));
            }
        }
        return specialityList;
    }

    @GetMapping(value = "/api/management", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ownership> fetchAllManagment(@RequestParam String institutionCategory) {
        if (institutionCategory.equalsIgnoreCase("S")) {
            return ownershipRepo.findAllValidForStandalone();
        }
        if (institutionCategory.equalsIgnoreCase("C")) {
            List<Ownership> list = ownershipRepo.findAllValidForCollege();
            if (list != null && list.size() > 0) {
                for (Ownership management : list)
                    if (CommonObjectOperation.listValidate(management.getOwnershipForCollege())) {
                        List<Management> managements = new ArrayList<>();
                        for (Integer id : management.getOwnershipForCollege()) {
                            managements.add(managmentRepo.getById(id));
                        }
                        management.setOwnershipForClg(managements);
                    }
                return list;
            }
                /*List<Ownership> ownershipList = ownershipRepo.findAll();
                return ownershipList;*/
        }

        return Collections.EMPTY_LIST;
    }


    @GetMapping(value = "/api/ownership", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Management> fetchAllOwnership(@RequestParam String institutionCategory) {
        if(CommonObjectOperation.stringValidate(institutionCategory)){
            if(!CommonObjectOperation.threeLength(institutionCategory)){
                throw new InvalidInputException("Institution Category length should be less then 3 ");
            }
        }
        switch (institutionCategory.toUpperCase()) {
            case "U":
                return managmentRepo.applicableForUniversity();
            case "C":
                return managmentRepo.applicableForCollege();
            case "S":
                return managmentRepo.applicableForStandalone();
        }
        return Collections.EMPTY_LIST;
    }


    @GetMapping(value = "/api/institutiontype", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstitutionTypeDto> fetchAllInstitutionType(@RequestParam(required = false) String type, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        if(CommonObjectOperation.stringValidate(type)){
            if(!CommonObjectOperation.sevenLength(type)){
                throw new InvalidInputException("Type length should be less then 7 ");
            }
        }
        List<InstitutionTypeDto> insTypeList = collegeServiceImpl.findAllInstitutionType(type);
        return insTypeList;
    }

    @GetMapping(value = "/api/institutiontypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstitutionTypeDto> fetchAllInstitutionType(@RequestParam(required = false) String type) {
        if(CommonObjectOperation.stringValidate(type)){
            if(!CommonObjectOperation.sevenLength(type)){
                throw new InvalidInputException("Type length should be less then 7 ");
            }
        }
        List<InstitutionTypeDto> insTypeList = collegeServiceImpl.findAllInstitutionType(type);
        return insTypeList;
    }

    @GetMapping(value = "/api/accreditation-cycle", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefAccreditationCycle> fetchAllAccrediationCycle(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<RefAccreditationCycle> refAccreditationList = collegeServiceImpl.fetchAllAccrediationCycle();
        return refAccreditationList;
    }

    @GetMapping(value = "/api/naac-grades", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefNaacGrades> fetchAllNaccGrades(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<RefNaacGrades> refAccreditationList = collegeServiceImpl.fetchAllNaccGrades();
        return refAccreditationList;
    }

    @GetMapping(value = "/api/status-prior-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefStatusPriorEstablishment> fetchAllStatusPriorEstablishment(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<RefStatusPriorEstablishment> refStatusPriorEstablishmentList = collegeServiceImpl.fetchAllRefStatusPriorEstablishmentList();
        return refStatusPriorEstablishmentList;
    }

    @GetMapping(value = "/api/minority-community-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefMinorityCommunityType> fetchAllMinorityCommunityType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<RefMinorityCommunityType> refStatusPriorEstablishmentList = collegeServiceImpl.fetchAllMinorityCommunityType();
        return refStatusPriorEstablishmentList;
    }
/*
    @GetMapping(value = "/api/deaffiliation-reason", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeAffiliationReasonDto> fetchAllDeAffiliationReason(@RequestParam String instituteType, @WithUser UserInfo userInfo
    ) {
        List<DeAffiliationReasonDto> listData = new ArrayList<>();
        if (instituteType.equalsIgnoreCase("S")) {

            List<RefStandaloneDeaffilationReason> SList = standaloneDeAffiliationRepo.findAll();
            for (int i = 0; i < SList.size(); i++) {
                DeAffiliationReasonDto data = new DeAffiliationReasonDto();
                BeanUtils.copyProperties(SList.get(i), data);
                listData.add(data);
            }
        }
        if (instituteType.equalsIgnoreCase("C")) {
            List<RefCollegeDeaffilationReason> CList = collegeDeAffiliationRepo.findAll();
            for (int i = 0; i < CList.size(); i++) {
                DeAffiliationReasonDto data = new DeAffiliationReasonDto();
                BeanUtils.copyProperties(CList.get(i), data);
                listData.add(data);
            }
        }

        return listData;
    }*/

    @GetMapping(value = "/api/block", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefBlockEO> fetchAllBlock(@RequestParam(required = false) String district) {
        if(CommonObjectOperation.stringValidate(district)){
            if(!CommonObjectOperation.fiveLength(district)){
                throw new InvalidInputException("District length should be less then 5 ");
            }
        }
        List<RefBlockEO> blockList = blockRepo.findAllBlock(district);
        return blockList.stream()
                .sorted(Comparator.comparing(RefBlockEO::getBlockName))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/urbanlocalbody", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefUrbanLocalBodyEO> fetchAllUrbanLocalBody(@RequestParam(required = false) String district) {
        if(CommonObjectOperation.stringValidate(district)){
            if(!CommonObjectOperation.fiveLength(district)){
                throw new InvalidInputException("District length should be less then 5 ");
            }
        }
        List<RefUrbanLocalBodyEO> localBodyList = urbanLocalBodyRepo.fetchAllUrbanLocalBody(district);
        return localBodyList.stream()
                .sorted(Comparator.comparing(RefUrbanLocalBodyEO::getUlbName))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/bodytype", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefStateBody> fetchRefStateBody() {
        List<RefStateBody> blockList = refStateBodyRapo.findAll();
        return blockList.stream()
                .sorted(Comparator.comparing(RefStateBody::getType))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/ref-statebody-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefStateBodyType> fetchRefStateBodyType(@RequestParam(required = false) Integer stateBodyId) {
        List<RefStateBodyType> blockList = new ArrayList<>();
        if (stateBodyId != null) {
            blockList = refStateBodyTypeRapo.findByStateBodyId(stateBodyId);
        } else {
            blockList = refStateBodyTypeRapo.findAll();
        }

        return blockList.stream()
                .sorted(Comparator.comparing(RefStateBodyType::getName))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/ref_sub_district", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefSubDistrictEO> fetchRefSubDistrict(@RequestParam(required = false) Integer lgdDistrictCode, @RequestParam(required = false) Integer lgdStateCode,
                                                      @RequestParam(required = false) Integer lgdSubDistrictCode) {
        List<RefSubDistrictEO> blockList = new ArrayList<>();
        if (lgdStateCode != null && lgdDistrictCode != null) {
            blockList = refSubDistrictRepo.findByLgdStateDistrictCode(lgdStateCode, lgdDistrictCode);
        } else if (lgdStateCode != null) {
            blockList = refSubDistrictRepo.findByLgdStateCode(lgdStateCode);
        } else if (lgdDistrictCode != null) {
            blockList = refSubDistrictRepo.findByLgdDistrictCode(lgdDistrictCode);
        } else {
            blockList = refSubDistrictRepo.findAll();
        }

        return blockList.stream()
                .sorted(Comparator.comparing(RefSubDistrictEO::getName))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/bank-account-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BankAccountType> fetchAllBankAccountType() {
        List<BankAccountType> ownershipList = bankAccountTypeRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/ref-governance-structure", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefGovernanceStructure> fetchAllGovernanceStructure() {
        List<RefGovernanceStructure> ownershipList = refGovernanceStructureRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/rep-nep-program-institute-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefNepprogramInstituteType> fetchAllNepProgramInstituteType() {
        List<RefNepprogramInstituteType> ownershipList = repNepProgramInstituteTypeRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/rep-fee-reimbursement-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefFeeReimbursementType> fetchAllFeeReimbursementType() {
        List<RefFeeReimbursementType> ownershipList = repFeeReimbursementTypeRepo.findAll();
        return ownershipList;
    }


    @GetMapping(value = "/api/ref-designation-natureappointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefMappingDesignationNatureAppointment> fetchRefStateBodyType(@RequestParam(required = false) String designationId) {
        List<RefMappingDesignationNatureAppointment> blockList = new ArrayList<>();
        if (designationId != null) {
            if(CommonObjectOperation.stringValidate(designationId)){
                if(!CommonObjectOperation.fiveLength(designationId)){
                    throw new InvalidInputException("Designation Id length should be less then 5 ");
                }
            }
            blockList = refMappingDesignationNatureAppointmentRepo.findByDesignationId(designationId);
        } else {
            blockList = refMappingDesignationNatureAppointmentRepo.findAll();
        }

        return blockList.stream()
                .sorted(Comparator.comparing(RefMappingDesignationNatureAppointment::getId))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/ref-nirf-discipline-category", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefNirfDisciplineCategoryEO> fetchAllNirfDisciplineCategory() {
        List<RefNirfDisciplineCategoryEO> ownershipList = refNirfDisciplineCategoryRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/ref-nirf-discipline-category-private", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefNirfDisciplineCategoryEO> fetchAllNirfDisciplineCategory(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<RefNirfDisciplineCategoryEO> ownershipList = refNirfDisciplineCategoryRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/ref-title", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefTitle> fetchAllRefTitle() {
        List<RefTitle> ownershipList = repTitleRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/ref-title-private", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefTitle> fetchAllRefTitle(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<RefTitle> ownershipList = repTitleRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/ref-foreign-student-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefForeignStudentType> fetchAllRefForeignStudentType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<RefForeignStudentType> ownershipList = refForeignStudentRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/ref-discipline", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefDisciplineEO> fetchAllRefDiscipline() {
        List<RefDisciplineEO> ownershipList = refDisciplineRepo.findAll();
        return ownershipList;
    }

    @GetMapping(value = "/api/ref-statutorybody", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefStatutoryBody> fetchAllStatutoryBody() {
        List<RefStatutoryBody> ownershipList = refStatutoryBodyRepo.findAll();
        return ownershipList;
    }
    
    @GetMapping(value = "/api/ref-ministry-onos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefMinistryOnosEO> fetchAllMinistryOnosEO() {
        List<RefMinistryOnosEO> ownershipList = refMinistryOnosRepo.findAllActive();
        return ownershipList;
    }


    @GetMapping(value = "/api/ref-institution-ranking-grade", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RefInstitutionRankGrade> fetchAllInstitutionGrade() {
        List<RefInstitutionRankGrade> ownershipList = rankGradeRapo.findAll();
        return ownershipList;
    }



    
}
