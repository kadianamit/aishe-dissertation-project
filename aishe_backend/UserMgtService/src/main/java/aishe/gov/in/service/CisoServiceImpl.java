package aishe.gov.in.service;

import aishe.gov.in.dao.CisoDao;
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
import aishe.gov.in.mastersvo.CisoInfoDTO;
import aishe.gov.in.mastersvo.InstituteDetailDTO;
import aishe.gov.in.utility.CisoEmadedPK;
import aishe.gov.in.utility.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CisoServiceImpl implements CisoService {

    @Autowired
    private CisoDao cisoDao;

    @Override
    public List<CisoInfoDTO> getAll(String agencyCode, String post) {
        List<CisoInfoDTO> cisoInfoDTO = null;
        List<CisoInfoEO> cisoInfoEOS = cisoDao.getAll(agencyCode, post);
        if (null != cisoInfoEOS && !cisoInfoEOS.isEmpty()) {
            cisoInfoDTO = new ArrayList<>();
            for (CisoInfoEO infoEO : cisoInfoEOS) {
                CisoInfoDTO cisoInfo = new CisoInfoDTO();
                BeanUtils.copyProperties(infoEO, cisoInfo);
                cisoInfo.setAgencyCode(infoEO.getCisoEmadedPK().getAgencyCode());
                cisoInfo.setPost(infoEO.getCisoEmadedPK().getPost());
                List<CisoLetterEo> letterEos = getCisoLetter(infoEO.getAgencyCode(), infoEO.getPost());
                List<CisoLetterEo> cisoLetterEoList = new ArrayList<>();
                if (null != letterEos && !letterEos.isEmpty()) {
                    for (CisoLetterEo letterEo : letterEos) {
                        if (null != letterEo.getIssueDate()) {
                            letterEo.setIssuedDated(DateUtils.convertDBDateToStringPerson(letterEo.getIssueDate()));
                            cisoLetterEoList.add(letterEo);
                        }
                    }
                }

                cisoInfo.setLetter(cisoLetterEoList);

                cisoInfoDTO.add(cisoInfo);
            }
        }
        return cisoInfoDTO;
    }

    @Override
    public CisoInfoEO getCiso(String agencyCode, String post) {
        return cisoDao.getCiso(agencyCode, post);
    }

    @Override
    public CisoInfoEO saveCiso(CisoInfoEO cisoInfoEO, byte[] byteArr, MultipartFile file) throws IOException {
        CisoInfoEO cisoInfo = null;
        CisoEmadedPK cisoEmadedPK = new CisoEmadedPK(cisoInfoEO.getAgencyCode().toUpperCase(), cisoInfoEO.getPost());
        cisoInfoEO.setCisoEmadedPK(cisoEmadedPK);
        CisoLetterEo letterEo = null;
        CisoInfoEO infoEO = cisoDao.getCisoByName(cisoInfoEO.getPost(), cisoInfoEO.getAgencyCode());
        if (byteArr != null && byteArr.length > 0) {
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            inputStream.read();
            inputStream.close();
            letterEo = CisoLetterEo
                    .builder()
                    .letter(byteArr)
                    .agencyCode(cisoInfoEO.getAgencyCode())
                    .post(cisoInfoEO.getPost())
                    .letterTitle(cisoInfoEO.getLetterTitle())
                    .remarks(cisoInfoEO.getRemarks())
                    .issueDate(cisoInfoEO.getIssuedDate())
                    .build();
        }
        if (null == infoEO) {
            cisoInfo = cisoDao.saveCiso(cisoInfoEO);
            if (null != cisoInfo)
                if (null != letterEo)
                    cisoDao.saveLetter(letterEo);
        } else {
            cisoInfo = cisoDao.updateCiso(cisoInfoEO);
            if (null != cisoInfo)
                if (null != letterEo)
                    cisoDao.saveLetter(letterEo);
        }
        return cisoInfo;
    }

    @Override
    public CisoLetterEo saveCisoLetter(CisoLetterEo letterEo, byte[] byteArr, MultipartFile file) throws IOException {
        CisoInfoEO infoEO = cisoDao.getCiso(letterEo.getAgencyCode(), letterEo.getPost());
        InputStream inputStream = new ByteArrayInputStream(byteArr);
        inputStream.read();
        inputStream.close();
        letterEo.setLetter(byteArr);
        letterEo.setIssueDate(DateUtils.convertStringDateToDBDate(letterEo.getIssuedDated()));
        if (null != infoEO) {
            if (null == letterEo.getId()) {
                return cisoDao.saveLetter(letterEo);
            } else {
                return cisoDao.updateLetter(letterEo);
            }
        }
        return null;
    }


    @Override
    public List<CisoLetterEo> getCisoLetter(String agencyCode, String post) {
        return cisoDao.getCisoLetter(agencyCode, post);
    }

    @Override
    public CisoLetterEo getCisoLetter(Integer letterId) {
        return cisoDao.getCisoLetter(letterId);
    }

    @Override
    public Boolean deleteCisoInfo(String agencyCode, String post) {
        CisoInfoEO cisoInfoEO = cisoDao.getCiso(agencyCode, post);
        List<CisoLetterEo> cisoLetter = cisoDao.getCisoLetter(agencyCode, post);
        if (null != cisoLetter && !cisoLetter.isEmpty()) {
            for (CisoLetterEo letterEo : cisoLetter) {
                cisoDao.deleteCisoLetter(letterEo);
            }
        }
        return cisoDao.deleteCisoInfo(cisoInfoEO);
    }

    @Override
    public Boolean deleteCisoLetter(Integer letterId) {
        CisoLetterEo letterEo = cisoDao.getCisoLetter(letterId);
        return cisoDao.deleteCisoLetter(letterEo);
    }

    @Override
    public InstituteDetailDTO institutionDetail(String letterId) {
        return cisoDao.institutionDetail(letterId);
    }

    @Override
    public List<CisoInfoDTO> getAll(InstitutionCategory category) {
        List<CisoInfoDTO> cisoInfoDTO = null;
        List<CisoInfoEO> cisoInfoEOS = cisoDao.getAll(category);
        if (null != cisoInfoEOS && !cisoInfoEOS.isEmpty()) {
            cisoInfoDTO = new ArrayList<>();
            for (CisoInfoEO infoEO : cisoInfoEOS) {
                CisoInfoDTO cisoInfo = new CisoInfoDTO();
                BeanUtils.copyProperties(infoEO, cisoInfo);
                cisoInfo.setAgencyCode(infoEO.getCisoEmadedPK().getAgencyCode());
                cisoInfo.setPost(infoEO.getCisoEmadedPK().getPost());
              /*  List<CisoLetterEo> letterEos = getCisoLetter(infoEO.getAgencyCode(), infoEO.getPost());
                List<CisoLetterEo> cisoLetterEoList = new ArrayList<>();
                if (null != letterEos && !letterEos.isEmpty()) {
                    for (CisoLetterEo letterEo : letterEos) {
                        if (null != letterEo.getIssueDate()) {
                            letterEo.setIssuedDated(DateUtils.convertDBDateToStringPerson(letterEo.getIssueDate()));
                            cisoLetterEoList.add(letterEo);
                        }
                    }
                }

                cisoInfo.setLetter(cisoLetterEoList);*/
                cisoInfoDTO.add(cisoInfo);
            }
        }
        return cisoInfoDTO;
    }

    @Override
    public List<CisoInfoApplicationDetails> getAllApplication(Integer category) {
        return cisoDao.getAllApplication(category);
    }

    @Override
    public CisoInfoApplicationDetails saveUpdate(CisoInfoApplicationDetails category) {
        if (null == category.getId() || 0 == category.getId())
            return cisoDao.save(category);
        else return cisoDao.update(category);
    }

    @Override
    public Boolean deleteApplication(Integer id) {
        CisoInfoApplicationDetails applicationDetails = cisoDao.getApplication(id);
        if (null != applicationDetails)
            return cisoDao.delete(applicationDetails);
        return false;
    }

	@Override
	public List<CisoHeiEO> cisoMdHeiDetail(String subCategory) {
		 return cisoDao.cisoMdHeiDetail(subCategory);
	}

	@Override
	public Boolean deleteCisoInfoHeiMd( String agencyCode,String instituteFullName) {
	    return cisoDao.deleteCisoInfoHeiMd(agencyCode,instituteFullName);
	    }

	@Override
	public CisoHeiEO saveOrUpdateCisoHeiMd(CisoHeiEO cisohei) {
		 return cisoDao.saveOrUpdateCisoHeiMd(cisohei);
	}

	@Override
	public List<CisoHeiEO> cisoMdHeiDetailNotInCiso(String subCategory) {
		 return cisoDao.cisoMdHeiDetailNotInCiso(subCategory);
	}

	@Override
	public List<CisoServerDetailsEO> getServerDetailsList() {
        List<CisoServerDetailsEO> cisoInfoEOS = cisoDao.getServerDetailsList();
        return cisoInfoEOS;
    }

	@Override
	public List<CisoSwitchNetworkDetailsEO> getCisoNetworkDetailsList() {
		  List<CisoSwitchNetworkDetailsEO> cisoInfoEOS = cisoDao.getCisoNetworkDetailsList();
	        return cisoInfoEOS;
	    }

	@Override
	public List<CisoSystemDetailsEO> getCisoSystemDetailsList() {
		  List<CisoSystemDetailsEO> cisoInfoEOS = cisoDao.getCisoSystemDetailsList();
	        return cisoInfoEOS;
	    }

	@Override
	public CisoServerDetailsEO saveOrUpdateCisoServerDetails(CisoServerDetailsEO cisoServerDetailsEO) {
		 return cisoDao.saveOrUpdateCisoServerDetails(cisoServerDetailsEO);
	}

	@Override
	public CisoSwitchNetworkDetailsEO saveOrUpdateCisoSwitchNetworkDetailsEO(
			CisoSwitchNetworkDetailsEO cisoServerDetailsEO) {
		return cisoDao.saveOrUpdateCisoSwitchNetworkDetailsEO(cisoServerDetailsEO);
	}

	@Override
	public CisoSystemDetailsEO saveOrUpdateCisoSystemDetailsList(CisoSystemDetailsEO cisoServerDetailsEO) {
		return cisoDao.saveOrUpdateCisoSystemDetailsList(cisoServerDetailsEO);
	}

	@Override
	public Boolean deleteServerDetails(Integer serverId) {
	  return cisoDao.deleteCisoServerDetails(serverId);
	}

	@Override
	public Boolean deleteSwitchNetworkDetails(Integer networkId) {
		 return cisoDao.deleteSwitchNetworkDetails(networkId);
	}

	@Override
	public Boolean deleteSystemDetails(Integer systemId) {
		 return cisoDao.deleteSystemDetails(systemId);
	}

	@Override
	public List<CisoDocumentEo> getCisoDocumentList(List<Integer> id) {
		 List<CisoDocumentEo> cisoInfoEOS = cisoDao.getCisoDocumentList(id);
	        return cisoInfoEOS;
	}

	@Override
	public CisoDocumentEo saveOrUpdateCisoDocument(CisoDocumentEo cisoServerDetailsEO) {
		return cisoDao.saveOrUpdateCisoDocument(cisoServerDetailsEO);
	}

	@Override
	public Boolean deleteCisoDocument(Integer systemId) {
		return cisoDao.deleteCisoDocument(systemId);
	}

    @Override
    public List<CisoAIOSystemDetailsEO> aioSystemDetails() {
        return cisoDao.aioSystemDetails();
    }

    @Override
    public CisoAIOSystemDetailsEO saveOrUpdateCisoAIOSystemDetails(CisoAIOSystemDetailsEO cisoServerDetailsEO) {
        return cisoDao.saveOrUpdateCisoAIOSystemDetails(cisoServerDetailsEO);
    }

    @Override
    public List<?> govtDomainContactDetails() {
        return cisoDao.govtDomainContactDetails();
    }

    @Override
    public CisoSubDomainDetailEO savesubDomainDetail(CisoSubDomainDetailEO cisoServerDetailsEO) {
        return cisoDao.savesubDomainDetail(cisoServerDetailsEO);
    }

    @Override
    public List<CisoSubDomainDetailEO> subDomainDetail() {
        return cisoDao.subDomainDetail();
    }

    @Override
    public GovtDomainContactDetailEO saveDomainContactDetails(GovtDomainContactDetailEO eo) {
        return cisoDao.saveDomainContactDetails(eo);
    }
}