package aishe.gov.in.service;

import java.io.IOException;
import java.util.List;

import aishe.gov.in.masterseo.CisoAIOSystemDetailsEO;
import aishe.gov.in.masterseo.CisoSubDomainDetailEO;
import aishe.gov.in.masterseo.GovtDomainContactDetailEO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import aishe.gov.in.enums.InstitutionCategory;
import aishe.gov.in.masterseo.CisoDocumentEo;
import aishe.gov.in.masterseo.CisoHeiEO;
import aishe.gov.in.masterseo.CisoInfoApplicationDetails;
import aishe.gov.in.masterseo.CisoInfoEO;
import aishe.gov.in.masterseo.CisoLetterEo;
import aishe.gov.in.masterseo.CisoSwitchNetworkDetailsEO;
import aishe.gov.in.masterseo.CisoServerDetailsEO;
import aishe.gov.in.masterseo.CisoSystemDetailsEO;
import aishe.gov.in.mastersvo.CisoInfoDTO;
import aishe.gov.in.mastersvo.InstituteDetailDTO;

public interface CisoService {

    List<CisoInfoDTO> getAll(String agencyCode, String post);

    CisoInfoEO getCiso(String agencyCode,String post);

    CisoInfoEO saveCiso(CisoInfoEO cisoInfoEO, byte[] bytes, MultipartFile file) throws IOException;

    CisoLetterEo saveCisoLetter(CisoLetterEo cisoInfoEO, byte[] bytes, MultipartFile file) throws IOException;

    List<CisoLetterEo> getCisoLetter(String agencyCode,String post);

    CisoLetterEo getCisoLetter(Integer id);
    Boolean deleteCisoInfo(String agencyCode,String post);
    Boolean deleteCisoLetter(Integer letterId);
    InstituteDetailDTO institutionDetail(String letterId);
    List<CisoInfoDTO> getAll(InstitutionCategory category);
    List<CisoInfoApplicationDetails> getAllApplication(Integer category);

    CisoInfoApplicationDetails saveUpdate(CisoInfoApplicationDetails category);

    Boolean deleteApplication(Integer id);

	List<CisoHeiEO> cisoMdHeiDetail(String subCategory);

	Boolean deleteCisoInfoHeiMd( String agencyCode,String instituteFullName);

	CisoHeiEO saveOrUpdateCisoHeiMd(CisoHeiEO cisohei);

	List<CisoHeiEO> cisoMdHeiDetailNotInCiso(String subCategory);

	List<CisoServerDetailsEO> getServerDetailsList();

	List<CisoSwitchNetworkDetailsEO> getCisoNetworkDetailsList();

	List<CisoSystemDetailsEO> getCisoSystemDetailsList();

	CisoServerDetailsEO saveOrUpdateCisoServerDetails(CisoServerDetailsEO cisoServerDetailsEO);

	CisoSwitchNetworkDetailsEO saveOrUpdateCisoSwitchNetworkDetailsEO(CisoSwitchNetworkDetailsEO cisoServerDetailsEO);

	CisoSystemDetailsEO saveOrUpdateCisoSystemDetailsList(CisoSystemDetailsEO cisoServerDetailsEO);

	Boolean deleteServerDetails(Integer serverId);

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
