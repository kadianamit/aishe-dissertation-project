package aishe.gov.in.dao;

import aishe.gov.in.enums.InstitutionCategory;
import aishe.gov.in.masterseo.CisoAIOSystemDetailsEO;
import aishe.gov.in.masterseo.CisoDocumentEo;
import aishe.gov.in.masterseo.CisoHeiEO;
import aishe.gov.in.masterseo.CisoHeiEOData;
import aishe.gov.in.masterseo.CisoInfoApplicationDetails;
import aishe.gov.in.masterseo.CisoInfoEO;
import aishe.gov.in.masterseo.CisoLetterEo;
import aishe.gov.in.masterseo.CisoServerDetailsEO;
import aishe.gov.in.masterseo.CisoSubDomainDetailEO;
import aishe.gov.in.masterseo.CisoSwitchNetworkDetailsEO;
import aishe.gov.in.masterseo.CisoSystemDetailsEO;
import aishe.gov.in.masterseo.GovtDomainContactDetailEO;
import aishe.gov.in.mastersvo.InstituteDetailDTO;
import aishe.gov.in.utility.CisoHeMdEmadedPK;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.EncryptionDecryptionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CisoDaoImpl implements CisoDao {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(CisoDaoImpl.class);

    @Override
    public List<CisoInfoEO> getAll(String agencyCode, String post) {
        logger.info("CisoDaoImpl : CisoInfoEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoInfoEO> query = builder.createQuery(CisoInfoEO.class);
            Root<CisoInfoEO> allData = query.from(CisoInfoEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (agencyCode != null) {
                predicates.add(builder.equal(allData.get("cisoEmadedPK").get("agencyCode"), agencyCode));
            }
            if (post != null) {
                predicates.add(builder.equal(allData.get("cisoEmadedPK").get("post"), post));
            }
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(
                    builder.asc(allData.get("priority")),
                    builder.asc(allData.get("cisoEmadedPK").get("agencyCode")),
                    builder.asc(allData.get("cisoEmadedPK").get("post"))
            );
            List<CisoInfoEO> university = session.createQuery(query).getResultList();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoInfoEO getCiso(String agencyCode, String post) {
        logger.info("CisoDaoImpl : getCiso method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoInfoEO> query = builder.createQuery(CisoInfoEO.class);
            Root<CisoInfoEO> allData = query.from(CisoInfoEO.class);
            query.where(builder.and(builder.equal(allData.get("cisoEmadedPK").get("agencyCode"), agencyCode),
                    builder.equal(allData.get("cisoEmadedPK").get("post"), post)));
            CisoInfoEO university = session.createQuery(query).uniqueResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoInfoEO saveCiso(CisoInfoEO cisoInfoEO) {
        logger.info("CisoDaoImpl : saveCiso method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(cisoInfoEO);
            tx.commit();
            return cisoInfoEO;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoInfoEO updateCiso(CisoInfoEO cisoInfoEO) {
        logger.info("CisoDaoImpl : saveCiso method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(cisoInfoEO);
            tx.commit();
            return cisoInfoEO;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<CisoLetterEo> getCisoLetter(String agencyCode, String post) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoLetterEo> query = builder.createQuery(CisoLetterEo.class);
            Root<CisoLetterEo> allData = query.from(CisoLetterEo.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (agencyCode != null) {
                predicates.add(builder.equal(allData.get("agencyCode"), agencyCode));
            }
            if (post != null) {
                predicates.add(builder.equal(allData.get("post"), post));
            }
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            List<CisoLetterEo> university = session.createQuery(query).getResultList();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean deleteCisoInfo(CisoInfoEO cisoInfoEO) {
        logger.info("CisoDaoImpl : deleteCisoInfo method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(cisoInfoEO);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean deleteCisoLetter(CisoLetterEo letterEo) {
        logger.info("CisoDaoImpl : deleteCisoInfo method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(letterEo);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public CisoLetterEo saveLetter(CisoLetterEo letterEo) {
        logger.info("CisoDaoImpl : saveLetter method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(letterEo);
            tx.commit();
            return letterEo;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoLetterEo getCisoLetter(Integer letterId) {
        logger.info("CisoDaoImpl : getCiso method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoLetterEo> query = builder.createQuery(CisoLetterEo.class);
            Root<CisoLetterEo> allData = query.from(CisoLetterEo.class);
            query.where(builder.and(builder.equal(allData.get("id"), letterId)));
            CisoLetterEo university = session.createQuery(query).uniqueResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoLetterEo updateLetter(CisoLetterEo letterEo) {
        logger.info("CisoDaoImpl : updateLetter method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(letterEo);
            tx.commit();
            return letterEo;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public InstituteDetailDTO institutionDetail(String aisheCode) {
        String[] splitted = aisheCode.trim().split("\\s*-\\s*");
        StringBuilder query = new StringBuilder();
        switch (splitted[0].toUpperCase()) {
            case "U":
                query.append("select 'U-'||id,name,survey_year from ref_university c where c.survey_year=(select max(survey_year) from ref_university WHERE id='" + splitted[1] + "') and c.id='" + splitted[1] + "'");
                break;
            case "C":
                query.append("select 'C-'||id,name,survey_year from college c where c.survey_year=(select max(survey_year) from college WHERE  id='" + splitted[1] + "') AND c.id='" + splitted[1] + "'");
                break;
            case "S":
                query.append("select 'S-'||id,name,survey_year from ref_standalone_institution c where c.survey_year=(select max(survey_year) from ref_standalone_institution WHERE id='" + splitted[1] + "') and c.id='" + splitted[1] + "'");
                break;
        }
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> objects = session.createNativeQuery(query.toString()).getResultList();
            if (!objects.isEmpty()) {
                InstituteDetailDTO detailDTO = new InstituteDetailDTO();
                for (Object[] obj : objects) {
                    detailDTO.setAisheCode(obj[0].toString());
                    detailDTO.setInstituteName(obj[1].toString());
                    detailDTO.setSurveyYear(obj[2].toString());
                }
                return detailDTO;

            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public CisoInfoEO getCisoByName(String agencyName, String agencyCode) {
        logger.info("CisoDaoImpl : getCiso method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoInfoEO> query = builder.createQuery(CisoInfoEO.class);
            Root<CisoInfoEO> allData = query.from(CisoInfoEO.class);
            query.where(builder.and(builder.equal(allData.get("cisoEmadedPK").get("post"), agencyName),
                    builder.equal(allData.get("cisoEmadedPK").get("agencyCode"), agencyCode)));
            CisoInfoEO university = session.createQuery(query).uniqueResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<CisoInfoEO> getAll(InstitutionCategory category) {
        logger.info("CisoDaoImpl : getAll method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoInfoEO> query = builder.createQuery(CisoInfoEO.class);
            Root<CisoInfoEO> allData = query.from(CisoInfoEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (category != null) {
                predicates.add(builder.like(builder.lower(allData.get("agencyName")), "%" + category.getType() + "%"));
            }
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(
                    builder.asc(allData.get("priority")),
                    builder.asc(allData.get("cisoEmadedPK").get("agencyCode")));
            List<CisoInfoEO> university = session.createQuery(query).getResultList();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<CisoInfoApplicationDetails> getAllApplication(Integer id) {
        logger.info("CisoDaoImpl : getAllApplication method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoInfoApplicationDetails> query = builder.createQuery(CisoInfoApplicationDetails.class);
            Root<CisoInfoApplicationDetails> allData = query.from(CisoInfoApplicationDetails.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (id != null)
                predicates.add(builder.equal(allData.get("id"), id));
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            List<CisoInfoApplicationDetails> university = session.createQuery(query).getResultList();
      
            if (id != null) {
            	List<CisoInfoApplicationDetails> cisoAppList = new ArrayList<>();
            	CisoInfoApplicationDetails cisoInfoApplicationDetails= new CisoInfoApplicationDetails(); 
            	BeanUtils.copyProperties(university.get(0), cisoInfoApplicationDetails);
            	 byte[] finalAuditReportDocument= fetchCertificateDocument(cisoInfoApplicationDetails.getFinalAuditReportId());
            	 byte[] csdReportDocument= fetchCertificateDocument(cisoInfoApplicationDetails.getCsdReportId());
            	 byte[] auditCertificateDocument= fetchCertificateDocument(cisoInfoApplicationDetails.getAuditCertificateId());
            	 cisoInfoApplicationDetails.setFinalAuditReportDocument(finalAuditReportDocument);
            	 cisoInfoApplicationDetails.setCsdReportDocument(csdReportDocument);
            	 cisoInfoApplicationDetails.setAuditCertificateDocument(auditCertificateDocument);
            	 cisoAppList.add(cisoInfoApplicationDetails);
            	 return cisoAppList;
            }
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }


    private byte[] fetchCertificateDocument(Integer auditCertificateId) {
         Session session = sessionFactory.openSession();
      try {
          return (byte[]) session.createNativeQuery("select document_file from ciso.document where id ="+auditCertificateId+" ")
                  .getSingleResult();
      } catch (Exception e) {
      } finally {
          session.close();
      }
      return null;
  }

	@Override
    public CisoInfoApplicationDetails getApplication(Integer category) {
        logger.info("CisoDaoImpl : get All Ciso Info ApplicationDetails method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoInfoApplicationDetails> query = builder.createQuery(CisoInfoApplicationDetails.class);
            Root<CisoInfoApplicationDetails> allData = query.from(CisoInfoApplicationDetails.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (category != null)
                predicates.add(builder.equal(allData.get("id"), category));
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            return session.createQuery(query).uniqueResult();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoInfoApplicationDetails save(CisoInfoApplicationDetails applicationDetails) {
        logger.info("CisoDaoImpl : save CisoInfoApplicationDetails method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(applicationDetails);
            tx.commit();
            return applicationDetails;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoInfoApplicationDetails update(CisoInfoApplicationDetails applicationDetails) {
        logger.info("CisoDaoImpl : update CisoInfoApplicationDetails method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(applicationDetails);
            tx.commit();
            return applicationDetails;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean delete(CisoInfoApplicationDetails applicationDetails) {
        logger.info("CisoDaoImpl : delete CisoInfoApplicationDetails method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(applicationDetails);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<CisoHeiEO> cisoMdHeiDetail(String subCategory) {
        StringBuilder query = new StringBuilder();
        query.append("select id,aishe_code,institute_sub_type,institute_main_type,short_name,institute_full_name,address,matched_status,state_code,rs.name from ciso.ciso_md_hei"
                + " left join ref_state rs on rs.st_code =state_code");
        if (subCategory != null) {
            query.append(" where upper(institute_sub_type)= '" + subCategory.toUpperCase() + "'");
        }
        query.append(" order by institute_sub_type,id ASC");
        List<CisoHeiEO> data = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> objects = session.createNativeQuery(query.toString()).getResultList();
            if (!objects.isEmpty()) {
                for (Object[] obj : objects) {
                    CisoHeiEO detailDTO = new CisoHeiEO();
                    CisoHeMdEmadedPK pk = new CisoHeMdEmadedPK();
                    if (obj[0] != null) {
                        detailDTO.setId(Integer.parseInt(obj[0] + ""));
                    }
                    if (obj[1] != null) {
                        pk.setAisheCode(obj[1] + "");
                    }
                    if (obj[2] != null) {
                        detailDTO.setInstituteSubType(obj[2] + "");
                    }
                    if (obj[3] != null) {
                        detailDTO.setInstituteMainType(obj[3] + "");
                    }
                    if (obj[4] != null) {
                        detailDTO.setShortName(obj[4] + "");
                    }
                    if (obj[5] != null) {
                        pk.setInstituteFullName(obj[5] + "");
                    }
                    if (obj[6] != null) {
                        detailDTO.setAddress(obj[6] + "");
                    }
                    if (obj[7] != null) {
                        detailDTO.setMatchedStatus(obj[7] + "");
                    }
                    if (obj[8] != null) {
                        detailDTO.setStateCode(obj[8] + "");
                    }
                    if (obj[9] != null) {
                        detailDTO.setStateName(obj[9] + "");
                    }
                    detailDTO.setCisoEmadedPK(pk);
                    data.add(detailDTO);
                }
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Boolean deleteCisoInfoHeiMd(String agencyCode, String instituteFullName) {
        logger.info("CisoDaoImpl : deleteCisoInfo method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            CisoHeiEO data = fetchCisoByAgencyAndName(agencyCode, instituteFullName);
            tx = session.beginTransaction();
            if (data != null) {
                session.delete(data);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    private CisoHeiEO fetchCisoByAgencyAndName(String agencyCode, String instituteFullName) {
        logger.info("CisoDaoImpl : getCiso method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoHeiEO> query = builder.createQuery(CisoHeiEO.class);
            Root<CisoHeiEO> allData = query.from(CisoHeiEO.class);
            query.where(builder.and(builder.equal(allData.get("cisoEmadedPK").get("aisheCode"), agencyCode),
                    builder.equal(allData.get("cisoEmadedPK").get("instituteFullName"), instituteFullName))
            );
            CisoHeiEO university = session.createQuery(query).uniqueResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoHeiEO saveOrUpdateCisoHeiMd(CisoHeiEO cisohei) {
        logger.info("CisoDaoImpl : deleteCisoInfo method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            CisoHeiEOData data = fetchCisoByAisheCode(cisohei.getCisoEmadedPK().getAisheCode(), cisohei.getCisoEmadedPK().getInstituteFullName());
            tx = session.beginTransaction();
            if (data != null) {
                BeanUtils.copyProperties(cisohei, data);
                data.setAisheCode(cisohei.getCisoEmadedPK().getAisheCode());
                data.setInstituteFullName(cisohei.getCisoEmadedPK().getInstituteFullName());
                session.update(data);
            } else {

                session.save(cisohei);
            }
            tx.commit();
            return cisohei;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return cisohei;
    }

    @Override
    public List<CisoHeiEO> cisoMdHeiDetailNotInCiso(String subCategory) {
        StringBuilder query = new StringBuilder();
        query.append("select id,aishe_code,institute_sub_type,institute_main_type,short_name,institute_full_name,address,matched_status,state_code,rs.name from ciso.ciso_md_hei "
                + " mdhei left join ref_state rs on rs.st_code = state_code where aishe_code not in (select agency_code from ciso.ciso_info)\r\n"
                + " and institute_full_name not in (select agency_name from ciso.ciso_info) ");
        if (subCategory != null) {
            query.append(" and upper(institute_sub_type)= '" + subCategory.toUpperCase() + "'");
        }
        query.append(" order by institute_sub_type,id ASC");
        List<CisoHeiEO> data = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> objects = session.createNativeQuery(query.toString()).getResultList();
            if (!objects.isEmpty()) {
                for (Object[] obj : objects) {
                    CisoHeiEO detailDTO = new CisoHeiEO();
                    CisoHeMdEmadedPK pk = new CisoHeMdEmadedPK();
                    if (obj[0] != null) {
                        detailDTO.setId(Integer.parseInt(obj[0] + ""));
                    }
                    if (obj[1] != null) {
                        pk.setAisheCode(obj[1] + "");
                    }
                    if (obj[2] != null) {
                        detailDTO.setInstituteSubType(obj[2] + "");
                    }
                    if (obj[3] != null) {
                        detailDTO.setInstituteMainType(obj[3] + "");
                    }
                    if (obj[4] != null) {
                        detailDTO.setShortName(obj[4] + "");
                    }
                    if (obj[5] != null) {
                        pk.setInstituteFullName(obj[5] + "");
                    }
                    if (obj[6] != null) {
                        detailDTO.setAddress(obj[6] + "");
                    }
                    if (obj[7] != null) {
                        detailDTO.setMatchedStatus(obj[7] + "");
                    }
                    if (obj[8] != null) {
                        detailDTO.setStateCode(obj[8] + "");
                    }
                    if (obj[9] != null) {
                        detailDTO.setStateName(obj[9] + "");
                    }
                    detailDTO.setCisoEmadedPK(pk);
                    data.add(detailDTO);
                }
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private CisoHeiEOData fetchCisoByAisheCode(String agencyCode, String instituteFullName) {
        logger.info("CisoDaoImpl : getCiso method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoHeiEOData> query = builder.createQuery(CisoHeiEOData.class);
            Root<CisoHeiEOData> allData = query.from(CisoHeiEOData.class);
            query.where(builder.and(builder.equal(allData.get("aisheCode"), agencyCode))//,
                    // builder.equal(allData.get("cisoEmadedPK").get("instituteFullName"), instituteFullName))
            );
            CisoHeiEOData university = session.createQuery(query).uniqueResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<CisoServerDetailsEO> getServerDetailsList() {
        logger.info("CisoDaoImpl : CisoInfoEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoServerDetailsEO> query = builder.createQuery(CisoServerDetailsEO.class);
            Root<CisoServerDetailsEO> allData = query.from(CisoServerDetailsEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(
                    builder.asc(allData.get("id")));
            List<CisoServerDetailsEO> university = session.createQuery(query).getResultList();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<CisoSwitchNetworkDetailsEO> getCisoNetworkDetailsList() {
        logger.info("CisoDaoImpl : CisoInfoEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoSwitchNetworkDetailsEO> query = builder.createQuery(CisoSwitchNetworkDetailsEO.class);
            Root<CisoSwitchNetworkDetailsEO> allData = query.from(CisoSwitchNetworkDetailsEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(
                    builder.asc(allData.get("id")));
            List<CisoSwitchNetworkDetailsEO> university = session.createQuery(query).getResultList();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<CisoSystemDetailsEO> getCisoSystemDetailsList() {
        logger.info("CisoDaoImpl : CisoInfoEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoSystemDetailsEO> query = builder.createQuery(CisoSystemDetailsEO.class);
            Root<CisoSystemDetailsEO> allData = query.from(CisoSystemDetailsEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(
                    builder.asc(allData.get("id")));
            List<CisoSystemDetailsEO> university = session.createQuery(query).getResultList();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoServerDetailsEO saveOrUpdateCisoServerDetails(CisoServerDetailsEO cisoServerDetailsEO) {
        logger.info("CisoDaoImpl : saveCiso method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (cisoServerDetailsEO.getId() == 0) {
                session.save(cisoServerDetailsEO);
            } else {
                session.update(cisoServerDetailsEO);
            }
            tx.commit();
            return cisoServerDetailsEO;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoSwitchNetworkDetailsEO saveOrUpdateCisoSwitchNetworkDetailsEO(
            CisoSwitchNetworkDetailsEO cisoServerDetailsEO) {
        logger.info("CisoDaoImpl : saveCiso method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (cisoServerDetailsEO.getId() == 0) {
                session.save(cisoServerDetailsEO);
            } else {
                session.update(cisoServerDetailsEO);
            }
            tx.commit();
            return cisoServerDetailsEO;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoSystemDetailsEO saveOrUpdateCisoSystemDetailsList(CisoSystemDetailsEO cisoServerDetailsEO) {
        logger.info("CisoDaoImpl : saveCiso method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (cisoServerDetailsEO.getId() == 0) {
                session.save(cisoServerDetailsEO);
            } else {
                session.update(cisoServerDetailsEO);
            }
            tx.commit();
            return cisoServerDetailsEO;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean deleteCisoServerDetails(Integer serverId) {
        logger.info("CisoDaoImpl : deleteCisoInfo method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            CisoServerDetailsEO data = (CisoServerDetailsEO) session.get(CisoServerDetailsEO.class, serverId);
            tx = session.beginTransaction();
            session.delete(data);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean deleteSwitchNetworkDetails(Integer networkId) {
        logger.info("CisoDaoImpl : deleteCisoInfo method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        CisoSwitchNetworkDetailsEO data = (CisoSwitchNetworkDetailsEO) session.get(CisoSwitchNetworkDetailsEO.class, networkId);
        try {
            tx = session.beginTransaction();
            session.delete(data);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean deleteSystemDetails(Integer systemId) {
        logger.info("CisoDaoImpl : deleteCisoInfo method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        CisoSystemDetailsEO data = (CisoSystemDetailsEO) session.get(CisoSystemDetailsEO.class, systemId);
        try {
            tx = session.beginTransaction();
            session.delete(data);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<CisoDocumentEo> getCisoDocumentList(List<Integer> id) {
        logger.info("CisoDaoImpl : CisoDocumentEo method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CisoDocumentEo> query = builder.createQuery(CisoDocumentEo.class);
            Root<CisoDocumentEo> allData = query.from(CisoDocumentEo.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (id != null) {
            Predicate inPredicate = allData.get("id").in(id);
            predicates.add(inPredicate);
        }
            if (id == null) {
            	Predicate isNullPredicate = allData.get("refDocumentType").get("id").isNull();
            	predicates.add(isNullPredicate);
            }
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(
                    builder.asc(allData.get("id")));
            List<CisoDocumentEo> university = session.createQuery(query).getResultList();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CisoDocumentEo saveOrUpdateCisoDocument(CisoDocumentEo cisoServerDetailsEO) {
        logger.info("CisoDaoImpl : saveCiso method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            cisoServerDetailsEO.setDateTime(DateUtils.obtainCurrentTimeStamp());
            if (cisoServerDetailsEO.getId() == null || cisoServerDetailsEO.getId() == 0) {
            	cisoServerDetailsEO.setId(0);
                session.save(cisoServerDetailsEO);
            } else {
                session.update(cisoServerDetailsEO);
            }
            tx.commit();
            Integer id =cisoServerDetailsEO.getId();
            cisoServerDetailsEO.setId(id);
            return cisoServerDetailsEO;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean deleteCisoDocument(Integer systemId) {
        logger.info("CisoDaoImpl : deleteCisoInfo method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        CisoDocumentEo data = (CisoDocumentEo) session.get(CisoDocumentEo.class, systemId);
        try {
            tx = session.beginTransaction();
            session.delete(data);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<CisoAIOSystemDetailsEO> aioSystemDetails() {
        logger.info("CisoDaoImpl : aioSystemDetails method invoked :");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CisoAIOSystemDetailsEO order by id ").list();
        } catch (Exception e) {
            logger.error("CisoDaoImpl : aioSystemDetails has error : {} ", e.getMessage());
        }
        return null;
    }

    @Override
    public CisoAIOSystemDetailsEO saveOrUpdateCisoAIOSystemDetails(CisoAIOSystemDetailsEO cisoServerDetailsEO) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (cisoServerDetailsEO.getId() == 0) {
                cisoServerDetailsEO.setId(null);
            }
            if (null != cisoServerDetailsEO.getEmail()) {
                cisoServerDetailsEO.setEmailId(cisoServerDetailsEO.getEmail());
            }
            if (null != cisoServerDetailsEO.getMobile()) {
                cisoServerDetailsEO.setMobileNo(cisoServerDetailsEO.getMobile());
            }
            if (null != cisoServerDetailsEO.getLandline()) {
                cisoServerDetailsEO.setLandlineNo(cisoServerDetailsEO.getLandline());
            }
            session.saveOrUpdate(cisoServerDetailsEO);
            transaction.commit();
        } catch (Exception e) {
            logger.error("CisoDaoImpl : saveOrUpdateCisoAIOSystemDetails has error : {} ", e.getMessage());
            if (null != transaction && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<?> govtDomainContactDetails() {
        logger.info("CisoDaoImpl : govtDomainContactDetails method invoked :");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from GovtDomainContactDetailEO order by id ").list();
        } catch (Exception e) {
            logger.error("CisoDaoImpl : govtDomainContactDetails has error : {} ", e.getMessage());
        }
        return null;
    }

    @Override
    public CisoSubDomainDetailEO savesubDomainDetail(CisoSubDomainDetailEO cisoServerDetailsEO) {
        logger.info("CisoDaoImpl : savesubDomainDetail method invoked :");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (cisoServerDetailsEO.getId() == 0) {
                cisoServerDetailsEO.setId(null);
            }
            session.saveOrUpdate(cisoServerDetailsEO);
            transaction.commit();
            return cisoServerDetailsEO;
        } catch (Exception e) {
            logger.error("CisoDaoImpl : savesubDomainDetail has error : {} ", e.getMessage());
            if (null != transaction && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<CisoSubDomainDetailEO> subDomainDetail() {
        logger.info("CisoDaoImpl : subDomainDetail method invoked :");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CisoSubDomainDetailEO order by id ").list();
        } catch (Exception e) {
            logger.error("CisoDaoImpl : CisoSubDomainDetail has error : {} ", e.getMessage());
        }
        return null;
    }

    @Override
    public GovtDomainContactDetailEO saveDomainContactDetails(GovtDomainContactDetailEO eo) {
        logger.info("CisoDaoImpl : saveDomainContactDetails method invoked :");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (eo.getId() == 0) {
                eo.setId(null);
            }
            session.saveOrUpdate(eo);
            transaction.commit();
            return eo;
        } catch (Exception e) {
            logger.error("CisoDaoImpl : saveDomainContactDetails has error : {} ", e.getMessage());
            if (null != transaction && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return null;
    }
}