package aishe.gov.in.dao;

import aishe.gov.in.enums.InstituteCategory;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.RefRemunerationStatus;
import aishe.gov.in.masterseo.ApprovalStatus;
import aishe.gov.in.masterseo.FormUpload;
import aishe.gov.in.masterseo.RemunerationCriterionType1EO;
import aishe.gov.in.masterseo.RemunerationCriterionType2EO;
import aishe.gov.in.masterseo.RemunerationDashboard;
import aishe.gov.in.masterseo.RemunerationEO;
import aishe.gov.in.masterseo.RemunerationNormEO;
import aishe.gov.in.masterseo.RemunerationNormType1RuleEO;
import aishe.gov.in.masterseo.RemunerationNormType2RuleEO;
import aishe.gov.in.masterseo.RemunerationReport;
import aishe.gov.in.masterseo.RemunerationStatementApproval;
import aishe.gov.in.masterseo.RemunerationStatementApprovalByCategoryEO;
import aishe.gov.in.masterseo.RemunerationStatementApprovalEO;
import aishe.gov.in.masterseo.RemunerationStatementApprovalLogEO;
import aishe.gov.in.masterseo.RemunerationStatementDashBoardEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailTransactionLogEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailWithAmountEO;
import aishe.gov.in.masterseo.RemunerationStatementEO;
import aishe.gov.in.masterseo.TransactionStatus;
import aishe.gov.in.masterseo.UserAccountLogEO;
import aishe.gov.in.masterseo.UserAndInstitutionDetails;
import aishe.gov.in.masterseo.UserBankAccountEO;
import aishe.gov.in.mastersvo.AfiiliatedWithUniversityCount;
import aishe.gov.in.mastersvo.RemunerationChangeStatus;
import aishe.gov.in.mastersvo.RemunerationStatementVo;
import aishe.gov.in.mastersvo.RemunerationTransactionAndApprovalLogVo;
import aishe.gov.in.mastersvo.SelectOptionVO;
import aishe.gov.in.mastersvo.StatementGenerationVo;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.IpAddressProvider;
import aishe.gov.in.utility.RemunerationConstant;
import aishe.gov.in.utility.RemunerationMailUtil;
import aishe.gov.in.utility.RemunerationNativeQueries;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RemunerationDaoImpl implements RemunerationDao {
    private static final Logger log = LoggerFactory.getLogger(RemunerationDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private RemunerationMailUtil mailUtil;

    @Override
    public UserBankAccountEO saveUserBankDetail(
            UserBankAccountEO bankAccountEO/* , RemunerationStatementApproval statementApproval */) {
        log.info("RemunerationDaoImpl : saveUserBankDetail method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bankAccountEO);
            // session.save(statementApproval);
            tx.commit();
            return bankAccountEO;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public UserBankAccountEO updateUserBankDetail(UserBankAccountEO bankAccountEO) {
        log.info("RemunerationDaoImpl : updateUserBankDetail method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bankAccountEO);
            tx.commit();
            return bankAccountEO;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public List<UserBankAccountEO> getUserBankDetail(Integer surveyYear, String userId, String aisheCode) {
        log.info("RemunerationDaoImpl : getUserBankDetail method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserBankAccountEO> criteriaQuery = builder.createQuery(UserBankAccountEO.class);
            Root<UserBankAccountEO> root = criteriaQuery.from(UserBankAccountEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
           /* if (userId != null) {
                predicates.add(builder.equal(root.get("accountKey").get("userId"), userId));
            }*/
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("accountKey").get("surveyYear"), surveyYear));
            }
            if (aisheCode != null) {
                predicates.add(builder.equal(root.get("aisheCode"), aisheCode));
            }
             /* if (formId != null) {
                predicates.add(builder.equal(root.get("formId"), formId));
            }
            if (institutionType != null && !InstitutionType.ALL.getType().equals(institutionType)) {
                predicates.add(builder.equal(root.get("instituteType"), institutionType));
            }*/
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserBankAccountEO> q = session.createQuery(criteriaQuery);
            List<UserBankAccountEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public RemunerationNormEO saveRemunerationNorm(RemunerationNormEO remunerationNorm) {
        log.info("RemunerationDaoImpl : saveRemunerationNorm method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(remunerationNorm);
            tx.commit();
            return remunerationNorm;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public RemunerationNormEO updateRemunerationNorm(RemunerationNormEO remunerationNorm) {
        log.info("RemunerationDaoImpl : updateRemunerationNorm method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(remunerationNorm);
            tx.commit();
            return remunerationNorm;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public List<RemunerationNormEO> getRemunerationNorm(Integer surveyYear, String formId) {
        log.info("RemunerationDaoImpl : getRemunerationNorm method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RemunerationNormEO> criteriaQuery = builder.createQuery(RemunerationNormEO.class);
            Root<RemunerationNormEO> root = criteriaQuery.from(RemunerationNormEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("remunerationKey").get("surveyYear"), surveyYear));
            }
            if (formId != null) {
                predicates.add(builder.equal(root.get("remunerationKey").get("formId"), formId));
            }
          /*  if (aisheCode != null) {
                predicates.add(builder.equal(root.get("aisheCode"), aisheCode));
            }
            if (institutionType != null && !InstitutionType.ALL.getType().equals(institutionType)) {
                predicates.add(builder.equal(root.get("instituteType"), institutionType));
            }*/
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<RemunerationNormEO> q = session.createQuery(criteriaQuery);
            List<RemunerationNormEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public RemunerationNormType1RuleEO saveRemunerationNormType1Rule(RemunerationNormType1RuleEO normType1RuleEO) {
        log.info("RemunerationDaoImpl : saveRemunerationNormType1Rule method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(normType1RuleEO);
            tx.commit();
            return normType1RuleEO;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public RemunerationNormType1RuleEO updateRemunerationNormType1Rule(RemunerationNormType1RuleEO normType1RuleEO) {
        log.info("RemunerationDaoImpl : updateRemunerationNormType1Rule method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(normType1RuleEO);
            tx.commit();
            return normType1RuleEO;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public List<RemunerationNormType1RuleEO> getRemunerationNormType1RuleEO(Integer surveyYear, String aisheCode, String institutionType, String formId) {
        log.info("RemunerationDaoImpl : getRemunerationNormType1RuleEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RemunerationNormType1RuleEO> criteriaQuery = builder.createQuery(RemunerationNormType1RuleEO.class);
            Root<RemunerationNormType1RuleEO> root = criteriaQuery.from(RemunerationNormType1RuleEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            if (formId != null) {
                predicates.add(builder.equal(root.get("formId"), formId));
            }
            if (aisheCode != null) {
                predicates.add(builder.equal(root.get("aisheCode"), aisheCode));
            }
            if (institutionType != null && !InstitutionType.ALL.getType().equals(institutionType)) {
                predicates.add(builder.equal(root.get("instituteType"), institutionType));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<RemunerationNormType1RuleEO> q = session.createQuery(criteriaQuery);
            List<RemunerationNormType1RuleEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public RemunerationNormType2RuleEO saveRemunerationNormType2Rule(RemunerationNormType2RuleEO normType1RuleEO) {
        log.info("RemunerationDaoImpl : saveRemunerationNormType2Rule method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(normType1RuleEO);
            tx.commit();
            return normType1RuleEO;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public RemunerationNormType2RuleEO updateRemunerationNormType2Rule(RemunerationNormType2RuleEO normType1RuleEO) {
        log.info("RemunerationDaoImpl : updateRemunerationNormType2Rule method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(normType1RuleEO);
            tx.commit();
            return normType1RuleEO;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public List<RemunerationNormType2RuleEO> getRemunerationNormType2RuleEO(Integer surveyYear, String aisheCode, String institutionType, String formId) {
        log.info("RemunerationDaoImpl : getRemunerationNormType2RuleEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RemunerationNormType2RuleEO> criteriaQuery = builder.createQuery(RemunerationNormType2RuleEO.class);
            Root<RemunerationNormType2RuleEO> root = criteriaQuery.from(RemunerationNormType2RuleEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            if (formId != null) {
                predicates.add(builder.equal(root.get("formId"), formId));
            }
            if (aisheCode != null) {
                predicates.add(builder.equal(root.get("aisheCode"), aisheCode));
            }
            if (institutionType != null && !InstitutionType.ALL.getType().equals(institutionType)) {
                predicates.add(builder.equal(root.get("instituteType"), institutionType));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<RemunerationNormType2RuleEO> q = session.createQuery(criteriaQuery);
            List<RemunerationNormType2RuleEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RemunerationTransactionAndApprovalLogVo> getRemunerationLog(Integer surveyYear, String stateCode, String fromDate, String toDate, String instituteCategory, String nativeQuery) {
        log.info("RemunerationDaoImpl : getRemunerationLog method invoked :");
        Session session1 = sessionFactory.openSession();
        List<RemunerationTransactionAndApprovalLogVo> logVo = new ArrayList<>();
        try {
            Query query = session1.createNativeQuery(nativeQuery);
            query.setParameter("state_code", stateCode);
            query.setParameter("institution_category", instituteCategory);
            query.setParameter("from_date", fromDate.equalsIgnoreCase("ALL") ?
                    DateUtils.convertStringSlashDateToDBDate("01/01/2008") :
                    DateUtils.convertStringSlashDateToDBDate(fromDate));
            query.setParameter("to_date", toDate.equalsIgnoreCase("ALL") ? DateUtils.convertStringSlashDateToDBDate(
                    "31/12/2070") : DateUtils.convertStringSlashDateToDBDate(toDate));
            query.setParameter("survey_year", surveyYear);
            List<Object[]> list = query.getResultList();
            if (!list.isEmpty()) {
                for (Object[] objects : list) {
                    RemunerationTransactionAndApprovalLogVo vo = new RemunerationTransactionAndApprovalLogVo();
                    if (objects[0] != null)
                        vo.setStateName(String.valueOf(objects[0]));
                    if (objects[1] != null)
                        vo.setInstitutionType(String.valueOf(objects[1]));
                    if (objects[2] != null)
                        vo.setFormId(String.valueOf(objects[2]));
                    if (objects[3] != null)
                        vo.setInstitutionId(String.valueOf(objects[3]));
                    if (objects[4] != null)
                        vo.setInstitutionName(String.valueOf(objects[4]));
                    if (objects[5] != null)
                        vo.setUploadDate(String.valueOf(objects[5]));
                    if (objects[6] != null)
                        vo.setSurveyYear(String.valueOf(objects[6]));
                    if (objects[7] != null)
                        vo.setFormUploadId(String.valueOf(objects[7]));
                    if (objects[8] != null)
                        vo.setStatus(String.valueOf(objects[8]));
                    if (objects[9] != null)
                        vo.setTimestamp(String.valueOf(objects[9]));
                    if (objects[10] != null)
                        vo.setUserId(String.valueOf(objects[10]));
                    if (objects[11] != null)
                        vo.setIpAddress(String.valueOf(objects[11]));
                    logVo.add(vo);
                }
            }
            return logVo;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return logVo;
    }

    @Override
    public Boolean updateRemunerationNorm(List<RemunerationNormEO> remunerationNorm, Integer survey) {
        log.info("RemunerationDaoImpl : updateRemunerationNorm method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (RemunerationNormEO normEO : remunerationNorm) {
                normEO.getRemunerationKey().setSurveyYear(survey);
                session.update(normEO);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    public UserAndInstitutionDetails getUserDetail(String userId) {
        log.info("RemunerationDaoImpl : updateRemunerationNorm method invoked :");
        Session session1 = sessionFactory.openSession();
        UserAndInstitutionDetails details = null;
        try {
            Query query = session1.createNativeQuery(RemunerationNativeQueries.USER_INSTITUTION_DETAILS, UserAndInstitutionDetails.class);
            query.setParameter("userId", userId);
            details = (UserAndInstitutionDetails) query.uniqueResult();
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();

        } finally {
            session1.close();
        }
        return details;
    }

    @Override
    public Boolean updateRemunerationNorm(List<RemunerationNormEO> remunerationNorm, Boolean freeze) {
        log.info("RemunerationDaoImpl : updateRemunerationNorm method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (RemunerationNormEO normEO : remunerationNorm) {
                                normEO.setIsFreezed(freeze);
                session.update(normEO);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                log.error("Error occurred during operation {} ", e.getMessage());
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
    public String getSingleStringValue(String userId, Integer surveyYear, String nativeQuery) {
        log.info("RemunerationDaoImpl : getSingleStringValue method invoked :");
        Session session1 = sessionFactory.openSession();
        String details = null;
        try {
            Query query = session1.createNativeQuery(nativeQuery);
            query.setParameter("userId", userId);
            query.setParameter("surveyYear", surveyYear);
            details = (String) query.uniqueResult();
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();

        } finally {
            session1.close();
        }
        return details;
    }

    @Override
    public List<ApprovalStatus> getApprovalStatus(String userId, Integer surveyYear, String nativeQuery) {
        log.info("RemunerationDaoImpl : getApprovalStatus method invoked :");
        Session session1 = sessionFactory.openSession();
        List<ApprovalStatus> details = new ArrayList<>();
        try {
            session1.clear();
            Query query = session1.createNativeQuery(nativeQuery, ApprovalStatus.class);
            query.setParameter("userId", userId);
            query.setParameter("surveyYear", surveyYear);
            details = (List<ApprovalStatus>) query.getResultList();
            List<ApprovalStatus> datas = new ArrayList<>();

        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();

        } finally {
            session1.close();
        }
        return details;
    }

    @Override
    public TransactionStatus getTransactionStatus(String userId, Integer surveyYear, String nativeQuery) {
        log.info("RemunerationDaoImpl : getTransactionStatus method invoked :");
        Session session1 = sessionFactory.openSession();
        TransactionStatus details = null;
        try {
            Query query = session1.createNativeQuery(nativeQuery, TransactionStatus.class);
            query.setParameter("userId", userId);
            query.setParameter("surveyYear", surveyYear);
            details = (TransactionStatus) query.uniqueResult();
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();

        } finally {
            session1.close();
        }
        return details;
    }

    @Override
    public List<RemunerationStatementApprovalByCategoryEO> getStatementApprovalByCategory(String stateCode, Integer surveyYear, String instituteCategory, String fromDate, String toDate, String statusId, String universityType, String institutionId, String approverRoleId, String collegeType, String parentInstitutionId, String stateBodyId, String nativeQuery) {
        log.info("RemunerationDaoImpl : getStatementApprovalByCategory method invoked :");
        Session session1 = sessionFactory.openSession();
        List<RemunerationStatementApprovalByCategoryEO> list = new ArrayList<>();
        try {
            Query query = session1.createNativeQuery(nativeQuery, RemunerationStatementApprovalByCategoryEO.class);
            query.setParameter("state_code", stateCode);
            query.setParameter("institution_category", instituteCategory);
            query.setParameter("from_date", fromDate.equalsIgnoreCase("ALL") ? DateUtils.convertStringSlashDateToDBDate("01/01/2008") : DateUtils.convertStringSlashDateToDBDate(fromDate));
            query.setParameter("to_date", toDate.equalsIgnoreCase("ALL") ? DateUtils.convertStringSlashDateToDBDate("31/12/2070") : DateUtils.convertStringSlashDateToDBDate(toDate));
            query.setParameter("survey_year", surveyYear);
            query.setParameter("status_id", statusId);
            query.setParameter("university_type", universityType);
            query.setParameter("institution_id", institutionId);
            query.setParameter("approver_role_id", approverRoleId);
            query.setParameter("college_type", collegeType);
            query.setParameter("parent_institution_id", parentInstitutionId);
            query.setParameter("state_body_id", stateBodyId);
            list = query.getResultList();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return list;
    }

    @Override
    public List<RemunerationStatementApprovalEO> saveOrUpdateStatementApproval(List<RemunerationStatementApprovalEO> approvalEOS, HttpServletRequest request) {
        log.info("RemunerationDaoImpl : saveOrUpdateStatementApproval method invoked :");
        Session session = sessionFactory.openSession();
        Session session1 = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (RemunerationStatementApprovalEO approvalEO : approvalEOS) {
                RemunerationStatementApprovalEO statementApproval = getRemunerationStatementApproval(approvalEO.getApprovalKey().getFormUploadId(), approvalEO.getApprovalKey().getApproverRoleId());
                approvalEO.setIpAddress(IpAddressProvider.getClientIpAddr(request));
                approvalEO.setTimestamp(new Timestamp(new Date().getTime()));
                if (null == statementApproval) {
                    session.save(approvalEO);
                    session1.save(RemunerationStatementApprovalLogEO
                            .builder()
                            .approverRoleId(approvalEO.getApprovalKey().getApproverRoleId())
                            .formUploadId(approvalEO.getApprovalKey().getFormUploadId())
                            .ipAddress(approvalEO.getIpAddress())
                            .statusId(approvalEO.getStatusId())
                            .userId(approvalEO.getApproverUserId())
                            .timestamp(approvalEO.getTimestamp())
                            .build());


                } else {
                    session.update(approvalEO);
                    session1.save(RemunerationStatementApprovalLogEO
                            .builder()
                            .approverRoleId(approvalEO.getApprovalKey().getApproverRoleId())
                            .formUploadId(approvalEO.getApprovalKey().getFormUploadId())
                            .ipAddress(approvalEO.getIpAddress())
                            .statusId(approvalEO.getStatusId())
                            .userId(approvalEO.getApproverUserId())
                            .timestamp(approvalEO.getTimestamp())
                            .build());
                }
                mailUtil.remunerationSendMailApprovalStatus(approvalEO);
            }
            tx.commit();
            return approvalEOS;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                    e.printStackTrace();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
            session1.close();
        }
        return null;
    }

    @Override
    public RemunerationStatementApprovalEO getRemunerationStatementApproval(Integer formUploadId, Integer approverRoleId) {
        log.info("RemunerationDaoImpl : getRemunerationStatementApproval method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RemunerationStatementApprovalEO> criteriaQuery = builder.createQuery(RemunerationStatementApprovalEO.class);
            Root<RemunerationStatementApprovalEO> root = criteriaQuery.from(RemunerationStatementApprovalEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (formUploadId != null) {
                predicates.add(builder.equal(root.get("approvalKey").get("formUploadId"), formUploadId));
            }
            if (approverRoleId != null) {
                predicates.add(builder.equal(root.get("approvalKey").get("approverRoleId"), approverRoleId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<RemunerationStatementApprovalEO> q = session.createQuery(criteriaQuery);
            RemunerationStatementApprovalEO list = q.uniqueResult();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }


    @Override
    public BigInteger getNextValueFromSequence() {
        log.info("RemunerationDaoImpl : getNextValueFromSequence method invoked :");
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createNativeQuery("select nextval('remuneration_statement_seq')");
            List<BigInteger> list = query.list();
            return list.get(0);
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public RemunerationStatementEO statementGeneration(RemunerationStatementVo statementVo, HttpServletRequest request) {
        log.info("RemunerationDaoImpl : statementGeneration method invoked :");
        Session session = sessionFactory.openSession();
        RemunerationStatementEO statementEO = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String statementId;
            if (null != statementVo.getGeneration() && !statementVo.getGeneration().isEmpty()) {
                statementId = statementVo.getSurveyYear() + DateUtils.formatDateWithOutBackslash() + getNextValueFromSequence();
                //statementId = remunerationUtil.statementIdGeneration(statementVo.getSurveyYear());
                Timestamp currentTime = DateUtils.getCurrentDateTimestamp();
                String ipAddress = IpAddressProvider.getClientIpAddr(request);
                statementEO = RemunerationStatementEO
                        .builder()
                        .id(statementId)
                        .approverUserId(statementVo.getApproverUserId())
                        .surveyYear(statementVo.getSurveyYear())
                        .timestamp(currentTime)
                        .ipAddress(ipAddress)
                        .build();
                session.save(statementEO);
                for (StatementGenerationVo generationVo : statementVo.getGeneration()) {
                    RemunerationStatementDetailEO detailEO = RemunerationStatementDetailEO.builder()
                            .statementId(statementId)
                            .approverUserId(statementVo.getApproverUserId())
                            .formUploadId(generationVo.getFormUploadId())
                            .remarks(RemunerationConstant.REMUNERATION_REMARKS)
                            .transactionStatusId(RemunerationConstant.REMUNERATION_TRANSACTION_PENDING)
                            .ipAddress(ipAddress)
                            .timestamp(currentTime)
                            .build();
                    session.save(detailEO);
                    RemunerationStatementDetailTransactionLogEO logEO = RemunerationStatementDetailTransactionLogEO
                            .builder()
                            .formUploadId(generationVo.getFormUploadId())
                            .timestamp(currentTime)
                            .userId(statementVo.getApproverUserId())
                            .ipAddress(ipAddress)
                            .statusId(RemunerationConstant.REMUNERATION_TRANSACTION_PENDING)
                            .build();
                    session.save(logEO);
                    RemunerationEO remunerationEO = RemunerationEO
                            .builder()
                            .formUploadId(generationVo.getFormUploadId())
                            .normTypeId(generationVo.getNormTypeId())
                            .typeId(RemunerationConstant.REMUNERATION_TYPE)
                            .amount(generationVo.getAmount()).build();
                    session.save(remunerationEO);
                    if (RemunerationConstant.REMUNERATION_NORM_TYPE_1 == generationVo.getNormTypeId()) {
                        RemunerationCriterionType1EO criterionType1EO = RemunerationCriterionType1EO
                                .builder()
                                .formUploadId(generationVo.getFormUploadId())
                                .noOfProgramme(generationVo.getNoOfProgramme())
                                .universityConstitutedFromColleges(generationVo.getConstitutedFromColleges())
                                .build();
                        session.save(criterionType1EO);
                    } else {
                        RemunerationCriterionType2EO criterionType2EO = RemunerationCriterionType2EO
                                .builder()
                                .formUploadId(generationVo.getFormUploadId())
                                .noOfProgramme(generationVo.getNoOfProgramme())
                                .build();
                        session.save(criterionType2EO);
                    }
                }
                tx.commit();
            }
            return statementEO;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
        return statementEO;
    }


    @Override
    public List<RemunerationStatementDashBoardEO> getRemunerationStatementDashBoard(String generatorUserId, Integer surveyYear, String fromDate, String toDate, String nativeQuery) {
        log.info("RemunerationDaoImpl : getRemunerationStatementDashBoard method invoked :");
        Session session1 = sessionFactory.openSession();
        List<RemunerationStatementDashBoardEO> details = new ArrayList<>();
        try {
            Query query = session1.createNativeQuery(nativeQuery, RemunerationStatementDashBoardEO.class);
            query.setParameter("generator_user_id", generatorUserId);
            query.setParameter("survey_year", surveyYear);
            query.setParameter("from_date", fromDate.equalsIgnoreCase("ALL") ? DateUtils.convertStringSlashDateToDBDate("01/01/2008") : DateUtils.convertStringSlashDateToDBDate(fromDate));
            query.setParameter("to_date", toDate.equalsIgnoreCase("ALL") ? DateUtils.convertStringSlashDateToDBDate("31/12/2070") : DateUtils.convertStringSlashDateToDBDate(toDate));
            details = query.getResultList();
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return details;
    }


    @Override
    public List<RemunerationStatementDetailWithAmountEO> getStatementDetailsByStatementId(String statementId,
                                                                                          String stateCode,
                                                                                          Integer surveyYear, String instituteCategory, String fromDate, String toDate, String statusId, String nativeQuery) {
        log.info("RemunerationDaoImpl : getStatementDetailsByStatementId method invoked :");
        Session session1 = sessionFactory.openSession();
        List<RemunerationStatementDetailWithAmountEO> list = new ArrayList<>();
        try {
            Query query = session1.createNativeQuery(nativeQuery, RemunerationStatementDetailWithAmountEO.class);
            query.setParameter("state_code", stateCode);
            query.setParameter("institution_category", instituteCategory);
            query.setParameter("statement_id", statementId);
            query.setParameter("from_date", fromDate.equalsIgnoreCase("ALL") ? DateUtils.convertStringSlashDateToDBDate("01/01/2008") : DateUtils.convertStringSlashDateToDBDate(fromDate));
            query.setParameter("to_date", toDate.equalsIgnoreCase("ALL") ? DateUtils.convertStringSlashDateToDBDate("31/12/2070") : DateUtils.convertStringSlashDateToDBDate(toDate));
            query.setParameter("survey_year", surveyYear);
            query.setParameter("transaction_status_id", statusId);
            list = query.getResultList();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return list;
    }

    @Override
    public RemunerationStatementEO getRemunerationStatement(String statementId) {
        log.info("RemunerationDaoImpl : getRemunerationStatement method invoked :");
        Session session = sessionFactory.openSession();
        try {
            return session.get(RemunerationStatementEO.class, statementId);
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RemunerationStatementDetailEO> saveRemunerationStatementTransactionApproval(List<RemunerationStatementDetailEO> detailEOS, HttpServletRequest request) {
        log.info("RemunerationDaoImpl : saveRemunerationStatementTransactionApproval method invoked :");
        Session session = sessionFactory.openSession();
        Session session1 = sessionFactory.openSession();

        Timestamp currentTime = DateUtils.getCurrentDateTimestamp();
        String ipAddress = IpAddressProvider.getClientIpAddr(request);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (RemunerationStatementDetailEO detailEO : detailEOS) {
                RemunerationStatementDetailEO statementDetailEO = session.get(RemunerationStatementDetailEO.class, detailEO.getFormUploadId());
                if (RemunerationConstant.REMUNERATION_TRANSACTION_APPROVED != statementDetailEO.getTransactionStatusId()) {
                    statementDetailEO.setTransactionStatusId(detailEO.getTransactionStatusId());
                    statementDetailEO.setApproverUserId(detailEO.getApproverUserId());
                    statementDetailEO.setTimestamp(currentTime);
                    statementDetailEO.setIpAddress(ipAddress);
                    statementDetailEO.setRemarks(detailEO.getRemarks());
                    session.update(statementDetailEO);
                    RemunerationStatementDetailTransactionLogEO logEO = RemunerationStatementDetailTransactionLogEO
                            .builder()
                            .formUploadId(detailEO.getFormUploadId())
                            .timestamp(currentTime)
                            .userId(detailEO.getApproverUserId())
                            .ipAddress(ipAddress)
                            .statusId(detailEO.getTransactionStatusId())
                            .build();
                    session1.save(logEO);
                    if (detailEOS.size() % 50 == 0) { // Same as JDBC batch size
                        //flush a batch of inserts and release memory:
                        session.flush();
                        session1.flush();
                        session1.clear();
                        session.clear();
                    }
                    mailUtil.sendEmailsAndSMSTransaction(detailEO);
                }
            }
            tx.commit();
            return detailEOS;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                log.error("Error occurred during operation {} ", e.getMessage());
                e.printStackTrace();
            }
        } finally {
            session.close();
            session1.close();
        }
        return null;
    }

    @Override
    public List<RemunerationDashboard> findStatementListByIdAndStatus(String statementId, String transactionStatusId, int limit, int offset, String nativeQuery) {
        log.info("RemunerationDaoImpl : findStatementListByIdAndStatus method invoked :");
        Session session1 = sessionFactory.openSession();
        List<RemunerationDashboard> list = new ArrayList<>();
        try {
            Query query = session1.createNativeQuery(nativeQuery, RemunerationDashboard.class);
            query.setParameter("statement_id", statementId);
            query.setParameter("transaction_status_id", transactionStatusId);
            query.setParameter("limit", limit);
            query.setParameter("offset", offset);
            list = query.getResultList();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return list;
    }

    @Override
    public AfiiliatedWithUniversityCount getAffiliatedCollegeCount(String universityId, Integer surveyYear) {
        try (Session session = sessionFactory.openSession()) {
            AfiiliatedWithUniversityCount afiiliated = new AfiiliatedWithUniversityCount();
            afiiliated.setTotalFormUploadedAffiliatedCollege((BigInteger) session.createNativeQuery("select  count(c.*) as  form_uploaded_affiliated_college from " +
                    "form_upload fu\n" +
                    "left join college c on (fu.college_institution_id=c.id and fu.survey_year=c.survey_year)\n" +
                    "where c.survey_year='" + surveyYear + "' and c.university_id='" + universityId + "'").uniqueResult());
            afiiliated.setTotalAffiliatedCollege((BigInteger) session.createNativeQuery("select  count(c.*) as  " +
                    "total_college_affiliated from college c where c.survey_year='" + surveyYear + "' and c.university_id" +
                    "='" + universityId + "'").uniqueResult());
            return afiiliated;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
        }
        return null;
    }

    @Override
    public List<SelectOptionVO> checkEligibility(String aisheCode, Integer surveyYear) {
        List<SelectOptionVO> optionVOS = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            String[] splitted = aisheCode.trim().split("\\s*-\\s*");

            String conditionQuery = splitted[0].equals("C") ? " And collegeInstitutionId='" + splitted[1] + "' and " +
                    "instituteType='C' and  formId= 'form2' " : splitted[0].equals("U") ?
                    " And universityId='" + splitted[1] + "' " +
                            "and " + "instituteType='U' and  formId= 'form1'" : splitted[0].equals("S") ? " And " +
                    "standaloneInstitutionId='" + splitted[1] + "' and " +
                    "instituteType='S' and  formId= 'form3'" : " ";
            List<FormUpload> resultList = session.createQuery("from FormUpload where 1=1 " + conditionQuery + " and " +
                    "surveyYear<='" + surveyYear + "'").getResultList();
            if (null != resultList && !resultList.isEmpty()) {

                // List<FormUpload> formUploads =resultList.stream().filter(formUpload -> formUpload.getSurveyYear() >= surveyYear).collect(Collectors.toList());
                if (resultList.size() > 1) {
                    return null;
                } else {
                    /*
                     List<FormUpload> formUploads =
                            resultList.stream().filter(formUpload -> formUpload.getSurveyYear() >= surveyYear).collect(Collectors.toList());
                    for(FormUpload upload:formUploads){
                        Integer second = upload.getSurveyYear() + 1;
                        optionVOS.add(new SelectOptionVO(upload.getSurveyYear().toString(), upload.getSurveyYear() + "-" + second));
                    }
                     */
                    List<FormUpload> resultList2 = session.createQuery("from FormUpload where 1=1 " + conditionQuery + " and " +
                            "surveyYear='" + surveyYear + "'").getResultList();
                    if (resultList2.size() == 1) {
                        Integer second = surveyYear + 1;
                        optionVOS.add(new SelectOptionVO(surveyYear.toString(), surveyYear + "-" + second));
                    } else {
                        return null;
                    }
                }
            }
            return optionVOS;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
        }
        return null;
    }

    @Override
    public void saveRemunerationApproval(RemunerationStatementApproval remunerationnApproval) {
        log.info("RemunerationDaoImpl : saveUserBankDetail method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(remunerationnApproval);
            // session.save(statementApproval);
            tx.commit();
            //  return remunerationnApproval;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
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
    }

    @Override
    public List<UserBankAccountEO> getUserBankDetails(Integer surveyYear, String userId, String aisheCode) {
        log.info("RemunerationDaoImpl : getUserBankDetail method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserBankAccountEO> criteriaQuery = builder.createQuery(UserBankAccountEO.class);
            Root<UserBankAccountEO> root = criteriaQuery.from(UserBankAccountEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (userId != null) {
                predicates.add(builder.equal(root.get("accountKey").get("userId"), userId));
            }
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("accountKey").get("surveyYear"), surveyYear));
            }
            if (aisheCode != null) {
                predicates.add(builder.equal(root.get("aisheCode"), aisheCode));
            }
	             /* if (formId != null) {
	                predicates.add(builder.equal(root.get("formId"), formId));
	            }
	            if (institutionType != null && !InstitutionType.ALL.getType().equals(institutionType)) {
	                predicates.add(builder.equal(root.get("instituteType"), institutionType));
	            }*/
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserBankAccountEO> q = session.createQuery(criteriaQuery);
            List<UserBankAccountEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RemunerationReport> remunerationReport(Integer surveyYear, InstituteCategory institutionType,
                                                       String aisheCode, String stateCode, String districtCode, String categoryType, String universityId, Date fDate, Date tDate, Integer statusId) {
        log.info("RemunerationDaoImpl : remunerationReport method invoked :");
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createNativeQuery("WITH remuneration_report (institution_type ,user_id, survey_year, " +
                            "account_holder_name, account_number, ifsc_code, updated_on, pan, aishe_code, bank_address, bank_name, pin_code, \n" +
                            "state_code ,state_name,district_code ,district_name,institution_name,type_id ,type ," +
                            "affilating_university_id,affilating_university_name,nodal_officer_name,status_id," +
                            "status_name,remarks,amount,number_of_affiliating_college,number_of_dcf_submitted_college,is_this_new_university) as (\n" +
                            "SELECT 'College Institution' as institution_type ,user_id, ua.survey_year, " +
                            "account_holder_name, account_number, ifsc_code, updated_on, pan, ua.aishe_code, " +
                            "bank_address, bank_name, ua.pin_code, \n" +
                            "s.st_code as state_code ,s.name as state_name,rd.dist_code as district_code ,rd.name as district_name,ru.name as institution_name,rut.id as type_id ,rut.type ,ru1.id as affilating_university_id,ru1.name as affilating_university_name\n" +
                            ",ids.officer_name as nodal_officer_name ,ua.status_id,rrs.status as status_name,ua" +
                            ".remarks,ua.amount,number_of_affiliating_college,number_of_dcf_submitted_college,is_this_new_university FROM" +
                            " public.user_account ua left join college ru on 'C-'||ru" +
                            ".id=ua.aishe_code and ru.survey_year=ua.survey_year\n" +
                            "left join ref_university ru1 on ru1.id=ru.university_id and ru1.survey_year=ua.survey_year\n" +
                            "left join ref_state s on s.st_code=ru.state_code left join ref_district rd on rd.dist_code=ru.district_code\n" +
                            " left join ref_remuneration_status rrs on rrs.id=ua.status_id " +
                            "left join ref_university_college_type rut on rut.id=ru.type_id\n" +
                            " left join webdcf.person_details_survey ids on (ids.inst_type||'-'||ids.aishe_code =ua.aishe_code and ids.survey_year= ua.survey_year and ids.officer_type='NO' ) " +
                            "where ua.survey_year=:surveyYear and (ua.aishe_code=:aisheCode OR 'ALL'=:aisheCode) \n" +
                            "AND (CAST(ua.updated_on AS date) >= :fromDate OR 'ALL' = :fromDate) AND (CAST(ua.updated_on AS date) <= :toDate OR 'ALL' = :toDate)\n" +
                            "AND ('College Institution'=:institutionType or 'ALL'=:institutionType) and (ru.state_code=:stateCode or 'ALL'=:stateCode)\n" +
                            "and (ru.district_code=:districtCode or 'ALL'=:districtCode) and (ru" +
                            ".type_id=:categoryType or 'ALL'=:categoryType) and (ru.university_id=:universityId or 'ALL'=:universityId) \n" +
                            " and (ua.status_Id=:statusId or 0=:statusId )" +
                            " and ru.id is not null  and ua.aishe_code is not null\n" +
                            " union all \n" +
                            "SELECT 'Standalone Institution' as institution_type ,user_id, ua.survey_year, " +
                            "account_holder_name, account_number, ifsc_code, updated_on, pan, ua.aishe_code, " +
                            "bank_address, bank_name, ua.pin_code, \n" +
                            "s.st_code as state_code ,s.name as state_name,rd.dist_code as district_code ,rd.name as district_name,ru.name as institution_name,cast(rut.id as text) as type_id ,rut.type ,null as affilating_university_id,null as affilating_university_name\n" +
                            ", ids.officer_name as nodal_officer_name ,ua.status_id,rrs.status as status_name,ua.remarks,ua.amount,number_of_affiliating_college,number_of_dcf_submitted_college,is_this_new_university FROM public" +
                            ".user_account ua left join " +
                            "ref_standalone_institution ru on 'S-'||ru.id=ua" +
                            ".aishe_code and ru.survey_year=ua.survey_year and ru.id is not null  and ua.aishe_code is not null\n" +
                            "left join ref_state s on s.st_code=ru.statecode left join ref_district rd on rd.dist_code=ru.district_code\n" +
                            " left join webdcf.person_details_survey ids on (ids.inst_type||'-'||ids.aishe_code =ua.aishe_code and ids.survey_year= ua.survey_year and ids.officer_type='NO' ) " +
                            "left join ref_state_body rut on rut.id=ru.statebodyid\n" +
                            " left join ref_remuneration_status rrs on rrs.id=ua.status_id " +
                            "where ua.survey_year=:surveyYear \n" +
                            "and (ua.aishe_code=:aisheCode OR 'ALL'=:aisheCode) \n" +
                            "AND (CAST(ua.updated_on AS date) >= :fromDate OR 'ALL' = :fromDate) AND (CAST(ua.updated_on AS date) <= :toDate OR 'ALL' = :toDate)\n" +
                            "AND ('Standalone Institution'=:institutionType or 'ALL'=:institutionType) and (ru.statecode=:stateCode or 'ALL'=:stateCode)\n" +
                            "and (ru.district_code=:districtCode or 'ALL'=:districtCode) and (CAST(ru" +
                            ".statebodyid AS TEXT)=:categoryType or 'ALL'=:categoryType)  and (ua.status_Id=:statusId or 0=:statusId ) \n" +
                            " and ru.id is not null  and ua.aishe_code is not null\n" +
                            " union all\n" +
                            "SELECT 'University' as institution_type ,user_id, ua.survey_year, account_holder_name, " +
                            "account_number, ifsc_code, updated_on, pan, ua.aishe_code, bank_address, bank_name, " +
                            "ua.pin_code, \n" +
                            "s.st_code as state_code ,s.name as state_name,rd.dist_code as district_code ,rd.name as district_name,ru.name as institution_name,rut.id as type_id ,rut.type ,null as affilating_university_id,null as affilating_university_name\n" +
                            ", ids.officer_name as nodal_officer_name ,ua.status_id,rrs.status as status_name,ua.remarks,ua.amount,number_of_affiliating_college,number_of_dcf_submitted_college,is_this_new_university " +
                            "FROM public.user_account ua left join ref_university ru on (ua" +
                            ".aishe_code='U-'||ru.id and ru.survey_year=ua.survey_year)\n" +
                            " left join ref_remuneration_status rrs on rrs.id=ua.status_id " +
                            "left join ref_state s on s.st_code=ru.statecode left join ref_district rd on rd.dist_code=ru.district_code\n" +
                            " left join webdcf.person_details_survey ids on (ids.inst_type||'-'||ids.aishe_code =ua.aishe_code and ids.survey_year= ua.survey_year and ids.officer_type='NO' ) " +
                            "left join ref_university_type rut on rut.id=ru.type_id\n" +
                            "where ua.survey_year=:surveyYear and (ua.aishe_code=:aisheCode OR 'ALL'=:aisheCode)\n" +
                            "AND (CAST(ua.updated_on AS date) >= :fromDate OR 'ALL' = :fromDate) AND (CAST(ua.updated_on AS date) <= :toDate OR 'ALL' = :toDate)\n" +
                            "AND ('University'=:institutionType or 'ALL'=:institutionType) and (ru.statecode=:stateCode or 'ALL'=:stateCode)\n" +
                            "and (ru.district_code=:districtCode or 'ALL'=:districtCode) and (ru.type_id=:categoryType or 'ALL'=:categoryType)\n" +
                            "  and (ua.status_Id=:statusId or 0=:statusId )   and ru.id is not null  and ua.aishe_code is not null\n" +
                            "\n" +
                            ")\n" +
                            "select * from remuneration_report order by institution_type,aishe_code,updated_on",
                    RemunerationReport.class);
            query.setParameter("surveyYear", surveyYear);
            query.setParameter("institutionType", institutionType.getType());
            query.setParameter("aisheCode", aisheCode);
            query.setParameter("stateCode", stateCode);
            query.setParameter("districtCode", districtCode);
            query.setParameter("categoryType", categoryType);
            query.setParameter("universityId", universityId);
            query.setParameter("fromDate", fDate);
            query.setParameter("toDate", tDate);
            query.setParameter("statusId", statusId);
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RemunerationChangeStatus> remunerationChangeStatus(List<RemunerationChangeStatus> detailEOS, HttpServletRequest request, UserInfo userInfo) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            Integer approverRoleId = getRoleIdByUserId(userInfo.getUsername());
            if (CommanObjectOperation.listValidate(detailEOS)) {
                transaction = session.beginTransaction();
                for (RemunerationChangeStatus status : detailEOS) {
                    session.createNativeQuery("update user_account set status_id=:statusId,remarks=:remarks where " +
                                    "aishe_code =:aisheCode and survey_year=:surveyYear ")
                            .setParameter("statusId", status.getStatus())
                            .setParameter("remarks", status.getRemarks())
                            .setParameter("aisheCode", status.getAisheCode())
                            .setParameter("surveyYear", status.getSurveyYear())
                            .executeUpdate();
                    UserAccountLogEO userAccountLogEO = new UserAccountLogEO(status.getUserId(), status.getAisheCode()
                            , userInfo.getUsername(), status.getStatus(), approverRoleId, status.getSurveyYear(),
                            DateUtils.obtainCurrentTimeStamp(), IpAddressProvider.getClientIpAddr(request), status.getRemarks());
                    session.save(userAccountLogEO);

                }
                transaction.commit();
                return detailEOS;
            }

        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            if (null != transaction && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return null;
    }


    private Integer getRoleIdByUserId(String userId) {
        try (Session session = sessionFactory.openSession()) {
            return (Integer) session.createNativeQuery("select role_id from USER_MASTER WHERE user_id =:userId ")
                    .setParameter("userId", userId).getSingleResult();
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
        }
        return null;
    }

    @Override
    public List<RefRemunerationStatus> refRemunerationStatus() {
        try (Session session = sessionFactory.openSession()) {
            return (List<RefRemunerationStatus>) session.createQuery("from RefRemunerationStatus order by id ").getResultList();
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
        }
        return null;
    }

    @Override
    public List<UserAccountLogEO> track(Integer surveyYear, String aisheCode) {
        try (Session session = sessionFactory.openSession()) {
            return (List<UserAccountLogEO>) session.createQuery("from UserAccountLogEO where aisheCode=:aisheCode and" +
                            " surveyYear=:surveyYear and statusId <> 13 order by actionTime ").setParameter(
                                    "surveyYear",
                            surveyYear)
                    .setParameter("aisheCode", aisheCode).getResultList();
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
        }
        return null;
    }

/*    @Override
    public RemunerationChangeStatus lockBankDetails(RemunerationChangeStatus detailEOS, HttpServletRequest request, UserInfo userInfo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            session.createNativeQuery("update user_account set lock_status=1,update_on=:updateOn where " +
                            "aishe_code =:aisheCode and survey_year=:surveyYear ")
                    .setParameter("aisheCode", detailEOS.getAisheCode())
                    .setParameter("surveyYear", detailEOS.getSurveyYear())
                    .setParameter("updateOn", DateUtils.obtainCurrentTimeStamp())
                    .executeUpdate();
            transaction.commit();
            return detailEOS;
        } catch (Exception e) {
            log.error("Error occurred during operation {} ", e.getMessage());
            if (null != transaction && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return null;
    }*/


}

