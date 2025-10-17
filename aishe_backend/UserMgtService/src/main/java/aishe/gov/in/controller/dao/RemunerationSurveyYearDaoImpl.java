package aishe.gov.in.dao;

import aishe.gov.in.masterseo.FormUpload;
import aishe.gov.in.masterseo.RemunerationNormEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import aishe.gov.in.masterseo.SurveyMaster;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Repository
@Log4j2
public class RemunerationSurveyYearDaoImpl implements RemunerationSurveyYearDao {

    @Autowired
    // @Qualifier("coreEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    protected final Session getCurrentSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).withOptions().jdbcTimeZone(TimeZone.getTimeZone("UTC")).openSession();
    }


    @Override
    public List<FormUpload> findByApproverIdForBasicForms(String aisheCode) {
        log.info("RemunerationSurveyYearDaoImpl in findByApproverIdForBasicForms method invoked ");
        String[] splitted = aisheCode.trim().split("\\s*-\\s*");
        try (Session session1 = getCurrentSession()) {
            String conditionQuery = splitted[0].equals("C") ? " And collegeInstitutionId='" + splitted[1] + "' and " +
                    "instituteType='C' " : splitted[0].equals("U") ? " And universityId='" + splitted[1] + "' and " +
                    "instituteType='U' " : splitted[0].equals("S") ? " And standaloneInstitutionId='" + splitted[1] + "' and " +
                    "instituteType='S' " : " ";

            Query query = session1.createQuery("from FormUpload where  isArchived = :isArchived and" +
                    " formId in ('form1','form2','form3') " + conditionQuery);
            query.setParameter("isArchived", false);

            List<FormUpload> formUpload = query.list();
            return formUpload;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", ex.getMessage());
            return null;
        }
    }


    @Override
    public List<FormUpload> findByApproverIdAndSurveyYearForBasicForms(String aisheCode, Integer surveyYear) {
        log.info("RemunerationSurveyYearDaoImpl in findByApproverIdAndSurveyYearForBasicForms method invoked ");
        String[] splitted = aisheCode.trim().split("\\s*-\\s*");
        try (Session session1 = getCurrentSession()) {
            String conditionQuery = splitted[0].equals("C") ? " And collegeInstitutionId='" + splitted[1] + "' and " +
                    "instituteType='C' " : splitted[0].equals("U") ? " And universityId='" + splitted[1] + "' and " +
                    "instituteType='U' " : splitted[0].equals("S") ? " And standaloneInstitutionId='" + splitted[1] + "' and " +
                    "instituteType='S' " : " ";
            Query query = session1.createQuery("from FormUpload where   surveyYear=:surveyYear " +
                    "and isArchived = :isArchived and formId in ('form1','form2','form3') " + conditionQuery);
            query.setParameter("isArchived", false);
            query.setParameter("surveyYear", surveyYear);
            List<FormUpload> formUpload = query.list();
            return formUpload;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", ex.getMessage());
            return null;
        }
    }

    @Override
    public List<SurveyMaster> getSurveyListForSubmittingBankDetails() {
        log.info("RemunerationSurveyYearDaoImpl in getSurveyListForSubmittingBankDetails method invoked ");
        try (Session session1 = getCurrentSession()) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -6);
            Date sixMonthBeforeDate = calendar.getTime();
            Query query =
                    session1.createQuery("from SurveyMaster where :sixMonthBeforeDate < endDate order by surveyYear" +
                            " desc");
            query.setParameter("sixMonthBeforeDate", sixMonthBeforeDate);
            // List<SurveyMasterEO> surveyMasterList = getHibernateTemplate().find("from SurveyMaster where ? < endDate order by surveyYear desc", sixMonthBeforeDate);
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", ex.getMessage());
            return null;
        }
    }


    @Override
    public List<SurveyMaster> getClosedSurveyList(int x) {
        log.info("RemunerationSurveyYearDaoImpl in getClosedSurveyList method invoked ");
        try (Session session1 = getCurrentSession()) {
            String currentDate = getCurrentDateInYYYYMMDDFormat();
            Query query = session1.createQuery("from SurveyMaster where endDate < '" + currentDate + "' order by surveyYear " +
                    "desc");
            List<SurveyMaster> surveyMasterList = query.list();
            List<SurveyMaster> subList = surveyMasterList.subList(0, x);
            return subList;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", ex.getMessage());
            return null;
        }
    }

    private String getCurrentDateInYYYYMMDDFormat() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(currentDate);
    }

    @Override
    public RemunerationStatementDetailEO findByFormIdAndStatusId(int formUploadId, int statusId) {
        log.info("RemunerationSurveyYearDaoImpl in findByFormIdAndStatusId method invoked ");
        try (Session session1 = getCurrentSession()) {
            Query query = session1.createQuery("from RemunerationStatementDetailEO where formUploadId = " +
                    ":formUploadId and " +
                    "transactionStatusId = :transactionStatusId");
            query.setParameter("formUploadId", formUploadId);
            query.setParameter("transactionStatusId", statusId);
            List<RemunerationStatementDetailEO> list = query.list();
            if (list != null && list.size() == 1) {
                return list.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", ex.getMessage());
        }
        return null;
    }


    @Override
    public List<RemunerationNormEO> findListByFrezzedSurveyYear(boolean flag) {
        log.info("RemunerationSurveyYearDaoImpl in findListByFrezzedSurveyYear method invoked ");
        try (Session session1 = getCurrentSession()) {
            Query query = session1.createQuery("from RemunerationNormEO where isFreezed =:isFreezed");
            query.setParameter("isFreezed", flag);
            List<RemunerationNormEO> formUpload = query.list();
            return formUpload;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<SurveyMaster> getAllSurveyList() {
        log.info("RemunerationSurveyYearDaoImpl in getAllSurveyList method invoked ");
        try (Session session1 = getCurrentSession()) {
            List<SurveyMaster> surveyMasterList = session1.createQuery("from SurveyMaster order by surveyYear desc").list();
            return surveyMasterList;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Generic Query can not be completed for list output of {} column due to this exception", ex.getMessage());
        }
        return null;
    }
}
