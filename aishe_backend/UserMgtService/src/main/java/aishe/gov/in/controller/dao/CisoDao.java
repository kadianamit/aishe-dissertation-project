package aishe.gov.in.dao;

import aishe.gov.in.enums.InstitutionCategory;
import aishe.gov.in.masterseo.CisoAIOSystemDetailsEO;
import aishe.gov.in.masterseo.CisoDocumentEo;
import aishe.gov.in.masterseo.CisoHeiEO;
import aishe.gov.in.masterseo.CisoInfoApplicationDetails;
import aishe.gov.in.masterseo.CisoInfoEO;
import aishe.gov.in.masterseo.CisoLetterEo;
import aishe.gov.in.masterseo.CisoSubDomainDetailEO;
import aishe.gov.in.masterseo.CisoSwitchNetworkDetailsEO;
import aishe.gov.in.masterseo.CisoServerDetailsEO;
import aishe.gov.in.masterseo.CisoSystemDetailsEO;
import aishe.gov.in.masterseo.GovtDomainContactDetailEO;
import aishe.gov.in.mastersvo.InstituteDetailDTO;

import javax.persistence.Id;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CisoDao {

    List<CisoInfoEO> getAll(String agencyCode, String post);

    CisoInfoEO getCiso(String agencyCode,String post);

    CisoInfoEO saveCiso(CisoInfoEO cisoInfoEO);

    CisoInfoEO updateCiso(CisoInfoEO cisoInfoEO);

    List<CisoLetterEo> getCisoLetter(String agencyCode,String post);

    Boolean deleteCisoInfo(CisoInfoEO cisoInfoEO);
    Boolean deleteCisoLetter(CisoLetterEo letterEo);
    CisoLetterEo saveLetter(CisoLetterEo letterEo);
    CisoLetterEo getCisoLetter(Integer letterId);

    CisoLetterEo updateLetter(CisoLetterEo letterEo);
    InstituteDetailDTO institutionDetail(String aisheCode);

	CisoInfoEO getCisoByName(String agencyName, String post);
    List<CisoInfoEO> getAll(InstitutionCategory category);
    List<CisoInfoApplicationDetails> getAllApplication(Integer category);
    CisoInfoApplicationDetails getApplication(Integer category);

    CisoInfoApplicationDetails save(CisoInfoApplicationDetails applicationDetails);

    CisoInfoApplicationDetails update(CisoInfoApplicationDetails applicationDetails);
    Boolean delete(CisoInfoApplicationDetails applicationDetails);

	List<CisoHeiEO> cisoMdHeiDetail(String subCategory);

	Boolean deleteCisoInfoHeiMd(String agencyCode,String instituteFullName);

	CisoHeiEO saveOrUpdateCisoHeiMd(CisoHeiEO cisohei);

	List<CisoHeiEO> cisoMdHeiDetailNotInCiso(String subCategory);

	List<CisoServerDetailsEO> getServerDetailsList();

	List<CisoSwitchNetworkDetailsEO> getCisoNetworkDetailsList();

	List<CisoSystemDetailsEO> getCisoSystemDetailsList();

	CisoServerDetailsEO saveOrUpdateCisoServerDetails(CisoServerDetailsEO cisoServerDetailsEO);

	CisoSwitchNetworkDetailsEO saveOrUpdateCisoSwitchNetworkDetailsEO(CisoSwitchNetworkDetailsEO cisoServerDetailsEO);

	CisoSystemDetailsEO saveOrUpdateCisoSystemDetailsList(CisoSystemDetailsEO cisoServerDetailsEO);

	Boolean deleteCisoServerDetails(Integer serverId);

	Boolean deleteSwitchNetworkDetails(Integer networkId);

	Boolean deleteSystemDetails(Integer systemId);

	List<CisoDocumentEo> getCisoDocumentList(List<Integer> id);

	CisoDocumentEo saveOrUpdateCisoDocument(CisoDocumentEo cisoServerDetailsEO);

	Boolean deleteCisoDocument(Integer systemId);

    List<CisoAIOSystemDetailsEO> aioSystemDetails();

    CisoAIOSystemDetailsEO saveOrUpdateCisoAIOSystemDetails(CisoAIOSystemDetailsEO cisoServerDetailsEO);

    List<?> govtDomainContactDetails();

    CisoSubDomainDetailEO savesubDomainDetail(CisoSubDomainDetailEO cisoServerDetailsEO);

    List<CisoSubDomainDetailEO> subDomainDetail();

    GovtDomainContactDetailEO saveDomainContactDetails(GovtDomainContactDetailEO eo);
}
