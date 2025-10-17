package aishe.gov.in.dao;

import aishe.gov.in.masterseo.OtpDetailsEO;
import aishe.gov.in.utility.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Repository
public class AisheOtpDaoImpl implements AisheOtpDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Integer saveEmailOtp(OtpDetailsEO forgot) {
        Session session = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Integer id = 0;
            if (forgot.getSno() == 0) {
                id = ((Integer) session.createQuery("select max(sno) from OtpDetailsEO").uniqueResult());
                if (id == null) {
                    id = 0;
                }
                forgot.setSno(id + 1);
            } else {
                OtpDetailsEO data = (OtpDetailsEO) session2.get(OtpDetailsEO.class, forgot.getSno());
                forgot.setMobileNumber(data.getMobileNumber());
                forgot.setMobileOtp(data.getMobileOtp());
                forgot.setMobileOtpDatetime(data.getMobileOtpDatetime());
                forgot.setIsMobilenoVerified(data.getIsMobilenoVerified());
                forgot.setMobileNoVerifiedDatetime(data.getMobileNoVerifiedDatetime());
                forgot.setSno(forgot.getSno());
            }
            session.saveOrUpdate(forgot);
            tx.commit();
            return forgot.getSno();
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            session.close();
            session2.close();
        }
        return forgot.getSno();
    }

    @Override
    public Integer saveMobileOtp(OtpDetailsEO forgot) {
        Session session = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Integer id = 0;
            if (forgot.getSno() == 0) {
                id = ((Integer) session.createQuery("select max(sno) from OtpDetailsEO").uniqueResult());
                if (id == null) {
                    id = 0;
                }
                forgot.setSno(id + 1);
            } else {
                OtpDetailsEO data = (OtpDetailsEO) session2.get(OtpDetailsEO.class, forgot.getSno());
                forgot.setEmailId(data.getEmailId());
                forgot.setEmailOtp(data.getEmailOtp());
                forgot.setEmailOtpDatetime(data.getEmailOtpDatetime());
                forgot.setIsEmailIdVerified(data.getIsEmailIdVerified());
                forgot.setEmailIdVerifiedDatetime(data.getEmailIdVerifiedDatetime());
                forgot.setSno(forgot.getSno());
            }
            session.saveOrUpdate(forgot);
            tx.commit();
            return forgot.getSno();
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            session.close();
            session2.close();
        }
        return forgot.getSno();
    }

    @Override
    public Boolean verifyEmailOtp(String email, String otp) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            OtpDetailsEO otpVerify = fetchOtpDetailsFromEmailOtp(email, otp);
            if (DateUtils.obtainCurrentTimeStamp().isAfter(otpVerify.getEmailOtpDatetime().plusMinutes(15))) {
                return false;
            }
            otpVerify.setEmailIdVerifiedDatetime(DateUtils.obtainCurrentTimeStamp());
            otpVerify.setIsEmailIdVerified(true);
            session.update(otpVerify);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            session.close();
        }
        return false;
    }

    private OtpDetailsEO fetchOtpDetailsFromEmailOtp(String email, String otp) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OtpDetailsEO> query = builder.createQuery(OtpDetailsEO.class);
            Root<OtpDetailsEO> allData = query.from(OtpDetailsEO.class);
            query.where(builder.and(builder.equal(allData.get("emailId"), email), builder.equal(allData.get("emailOtp"), otp)));
            OtpDetailsEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean verifyMobileOtp(String mobile, String otp) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            OtpDetailsEO otpVerify = fetchOtpDetailsFromMobileandOtp(mobile, otp);
            if (DateUtils.obtainCurrentTimeStamp().isAfter(otpVerify.getMobileOtpDatetime().plusMinutes(15))) {
                return false;
            }
            otpVerify.setIsMobilenoVerified(true);
            otpVerify.setMobileNoVerifiedDatetime(DateUtils.obtainCurrentTimeStamp());
            session.update(otpVerify);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            session.close();
        }
        return false;
    }

    private OtpDetailsEO fetchOtpDetailsFromMobileandOtp(String mobile, String otp) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OtpDetailsEO> query = builder.createQuery(OtpDetailsEO.class);
            Root<OtpDetailsEO> allData = query.from(OtpDetailsEO.class);
            query.where(builder.and(builder.equal(allData.get("mobileNumber"), mobile), builder.equal(allData.get("mobileOtp"), otp)));
            OtpDetailsEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Integer findByEmailIdForCount(String email) {
        LocalDateTime currTime = DateUtils.obtainCurrentTimeStamp().minusMinutes(1);
        Session session1 = sessionFactory.openSession();
        try {
            BigInteger surveyYearCsy = ((BigInteger) session1.createNativeQuery("select count(*) from otp_details where" + " email_id='" + email + "' and email_otp_datetime >= cast('" + currTime + "' as timestamp with time zone) and is_emailid_verified is false ").uniqueResult());
            Integer data = Integer.valueOf(surveyYearCsy.toString());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return 0;
    }

    @Override
    public Integer findByMobileNumberForCount(String mobileNumber) {
        LocalDateTime currTime = DateUtils.obtainCurrentTimeStamp().minusMinutes(1);
        Session session1 = sessionFactory.openSession();
        try {
            BigInteger surveyYearCsy = ((BigInteger) session1.createNativeQuery("select count(*) from otp_details where" + " mobile_no='" + mobileNumber + "' and mobile_otp_datetime>=cast('" + currTime + "' as timestamp with time zone)and is_mobileno_verified is false ").uniqueResult());
            Integer data = Integer.valueOf(surveyYearCsy.toString());
            return data;
        } catch (Exception e) {
            try {

            } catch (Exception trEx) {
            }
        } finally {
            session1.close();
        }
        return 0;
    }
}