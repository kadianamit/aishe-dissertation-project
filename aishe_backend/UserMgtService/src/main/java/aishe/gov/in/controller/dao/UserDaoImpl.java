package aishe.gov.in.dao;

import aishe.gov.in.enums.Constant;
import aishe.gov.in.masterseo.OtpDetailsEO;
import aishe.gov.in.masterseo.OtpDetailsMouEO;
import aishe.gov.in.masterseo.UserActionLog;
import aishe.gov.in.masterseo.UsersEO;
import aishe.gov.in.masterseo.CaptchaValidationEO;
import aishe.gov.in.mastersvo.ChangePasswordDTO;
import aishe.gov.in.mastersvo.ForgotPasswordDto;
import aishe.gov.in.mastersvo.ResetPasswordDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.EncryptionDecryptionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    BCryptPasswordEncoder bcrypt;

    @Autowired
    HttpHeaders header;

    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public String findByEmailIdIgnoreCase(String email) {
        logger.info("UserDaoImpl : findByEmailIdIgnoreCase Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            Query query = (Query) session.createNativeQuery("select user_id from user_master where email_id ='"+email+"'"
                    + " and is_approved=1");
            Object userrolelist = (Object) query.getSingleResult();
            String userId = (String) userrolelist;
            return userId;
        } catch (Exception e) {
            logger.info("Users Info Display" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean saveForgotPassword(OtpDetailsMouEO forgot) {
        logger.info("UserDaoImpl : saveForgotPassword method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(forgot);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction" + trEx.getMessage());
            }
            logger.error("Error In saveForgotPassword {}" + e.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public String verifyOtpForogtPassword(ForgotPasswordDto forgotPassword) {
    	String password = EncryptionDecryptionUtil.getDecryptedString(forgotPassword.getPassword());
        String cpassword = EncryptionDecryptionUtil.getDecryptedString(forgotPassword.getConfirmPassword());
        forgotPassword.setPassword(password);
//        forgotPassword.setConfirmPassword(cpassword);
        logger.info("UserDaoImpl : verifyOtpForogtPassword Method Invoked");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
        	//if(forgotPassword.getEmailOtp().equals("") || forgotPassword.getMobileOtp().equals("")) {
        	//	return "Enter Otp For Mobile and Email Both";
        //}
          //  String userid = findByLoginId(forgotPassword.getLoginId());
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OtpDetailsEO> cQuery = cb.createQuery(OtpDetailsEO.class);
            Root<OtpDetailsEO> root = cQuery.from(OtpDetailsEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
          //  Predicate mobotp = cb.equal(root.get("mobileOtp"), forgotPassword.getMobileOtp());
		//	Predicate emailotp = cb.equal(root.get("emailOtp"), forgotPassword.getEmailOtp());
			Predicate cslsy = cb.or(cb.equal(root.get("mobileOtp"), forgotPassword.getMobileOtp()),
					cb.equal(root.get("emailOtp"), forgotPassword.getEmailOtp()));
			
			Predicate emmob = cb.or(cb.equal(root.get("mobileNumber"), forgotPassword.getMobile()),
					cb.equal(root.get("emailId"), forgotPassword.getEmail()));
			predicates.add(emmob);
			predicates.add(cslsy);
           // cQuery.where(cb.and(cb.equal(root.get("emailOtp"),  forgotPassword.getEmailOtp())),
            		//(cb.equal(root.get("emailOtp"), forgotPassword.getEmailOtp())),
             //       (cb.equal(root.get("mobileOtp"), forgotPassword.getMobileOtp())));
            //TypedQuery<OtpDetailsEO> query = session.createQuery(cQuery);
           // List<OtpDetailsEO> usersOtpDetailsList = query.getResultList();
			cQuery.where(predicates.toArray(new Predicate[predicates.size()]));
			List<OtpDetailsEO> usersOtpDetailsList = session.createQuery(cQuery).getResultList();
			if (usersOtpDetailsList != null && usersOtpDetailsList.size() > 0) {
                for (OtpDetailsEO detailsEO : usersOtpDetailsList) { 
					if (detailsEO.getEmailOtp() != null && forgotPassword.getEmailOtp() != null && !forgotPassword.getEmailOtp().equals("")) {
						if (forgotPassword.getEmailOtp().equals(detailsEO.getEmailOtp())) {
							if (DateUtils.obtainCurrentTimeStamp()
									.isAfter(detailsEO.getEmailOtpDatetime().plusMinutes(15))) {
								return "expemailotp";
							}
						}
					}
					else if (detailsEO.getMobileOtp() != null && !forgotPassword.getMobileOtp().equals("") && forgotPassword.getMobileOtp() != null) {
						if (forgotPassword.getMobileOtp().equals(detailsEO.getMobileOtp())) {
							if (DateUtils.obtainCurrentTimeStamp()
									.isAfter(detailsEO.getMobileOtpDatetime().plusMinutes(15))) {
								return "expmobileotp";
							}
						}
					}
                           else
                                return "invalidemailotp";
            
                }
               
            CriteriaBuilder cbContact = session.getCriteriaBuilder();
            CriteriaQuery<UsersEO> cQueryContact = cbContact.createQuery(UsersEO.class);
            Root<UsersEO> rootContacts = cQueryContact.from(UsersEO.class);
            cQueryContact.where(cbContact.and(cbContact.equal(rootContacts.get("userId"), forgotPassword.getLoginId())));
            TypedQuery<UsersEO> contactQuery = session.createQuery(cQueryContact);
            UsersEO usersFpEO = contactQuery.getSingleResult();
            if(password.equals(cpassword)) {
                usersFpEO.setBcryptPassword(bcrypt.encode(password));
            }
            session.update(usersFpEO);

            for (OtpDetailsEO detailsEO : usersOtpDetailsList) {
                session.delete(detailsEO);
            }
            }
            tx.commit();
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction" + trEx.getMessage());
            }
            logger.error("Error in verifyOtp " + e.getMessage());
            return "error";
        } finally {
            session.close();
        }
        return "success";
    }

    public String findByLoginId(String loginId) {
        logger.info("UserDaoImpl : findByLoginId Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            String userId = (String) session.createNativeQuery("select user_id from user_master where user_id='" + loginId + "'")
                    .uniqueResult();
            return userId;
        } catch (Exception e) {
            logger.info("isUserNameExist Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

	@Override
	public String findByAisheCodeIgnoreCase(String aisheCode) {
		 logger.info("UserDaoImpl : findByEmailIdIgnoreCase Method Invoked");
	        Session session = sessionFactory.openSession();
	        try {
	            Query query = (Query) session.createNativeQuery("select aishe_code from user_master where aishe_code ='"+aisheCode+"'"
	                    + " and is_approved=1");
	            Object userrolelist = (Object) query.getSingleResult();
	            String userId = (String) userrolelist;
	            return userId;
	        } catch (Exception e) {
	            logger.info("Users Info Display" + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public boolean updateUserPassword(String userId, String oldPassword, String confirmPassword, String newPassword) {
		 logger.info("UserDaoImpl : saveForgotPassword method invoked :");
	        Session session = sessionFactory.openSession();
	        Transaction tx = session.beginTransaction();
	        try {
	        	String oldP= EncryptionDecryptionUtil.getDecryptedString(oldPassword);
	        	String confiP= EncryptionDecryptionUtil.getDecryptedString(confirmPassword);
	        	String newP= EncryptionDecryptionUtil.getDecryptedString(newPassword);
	        	UsersEO user = new UsersEO();
	        	user = (UsersEO)session.get(UsersEO.class, userId);
	        	user.setBcryptPassword(bcrypt.encode(newP));
	            session.update(user);
	            tx.commit();
	            return true;
	        } catch (Exception e) {
	            try {
	                if (tx != null && tx.isActive()) {
	                    tx.rollback();
	                }
	            } catch (Exception trEx) {
	                logger.error("Couldn’t roll back transaction" + trEx.getMessage());
	            }
	            logger.error("Error In saveForgotPassword {}" + e.getMessage());
	        } finally {
	            try {
	                session.close();
	            } catch (Exception e) {
	            }
	        }
	        return false;
	    }
    @Override
    public boolean resetPassword(ResetPasswordDTO changePassword,HttpServletRequest request,UserInfo userinfo) {
        logger.info("UserDaoImpl : resetPassword Method Invoked");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        UsersEO eo = new UsersEO();
        try {
            eo = (UsersEO) session.get(UsersEO.class, userinfo.getUsername());
            if (eo != null) {
            	String newp = EncryptionDecryptionUtil.getDecryptedString(changePassword.getNewPassword());
            	String oldp = EncryptionDecryptionUtil.getDecryptedString(changePassword.getConfirmPassword());
                boolean check = newp.equals(oldp);
                if (check) {
                    eo.setBcryptPassword(bcrypt.encode(newp));
                    
                    if(eo.getPasswordChangeDate()==null && (eo.getIsPasswordChange()==null || eo.getIsPasswordChange().equals(false))) {
                    	eo.setPasswordChangeDate(DateUtils.obtainCurrentTimeStamp());
    				}
                    eo.setIsPasswordChange(true);
                    //	eo.setUpdatedBy(userinfo.getUserId());
                    //	eo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
                    session.update(eo);
                    tx.commit();
                    
                    UserActionLog userActionLog = new UserActionLog();
                    userActionLog.setId(null);
                    userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                    if (request != null) {
                        userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
                        if (userActionLog.getIpAddress() == null || "".equals(userActionLog.getIpAddress())) {
                            userActionLog.setIpAddress(request.getRemoteAddr());
                        }
                    }
                    userActionLog.setIpAddress(request.getRemoteAddr());
                    userActionLog.setUserId(userinfo.getUsername());
                    userActionLog.setActionId(Constant.CHANGE_PASSWORD);
                    saveUserActionLog(userActionLog);
                    return true;
                } else {
                    return false;
                }

            }
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                logger.info("Couldn’t roll back transaction" + rbe.getMessage());
            }
            logger.info("Update All Offices Details Info Display" + e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }

    	 public void saveUserActionLog(UserActionLog useraction) {
    	        Session session = sessionFactory.openSession();
    	        Transaction tx = null;
    	        try {
    	            tx = session.beginTransaction();
    	            session.save(useraction);
    	            tx.commit();
    	        } catch (Exception e) {
    	            logger.error("Generic Query can not be completed for get output of {} due to this exception", e.getMessage());
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
    public boolean changePassword(ChangePasswordDTO changePassword,HttpServletRequest request,UserInfo userInfo) {
        logger.info("UserDaoImpl : changePassword Method Invoked");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        UsersEO eo = new UsersEO();
        try {
            eo = (UsersEO) session.get(UsersEO.class, userInfo.getUsername());
            if (eo != null) {
            	
            	String oldPassword =EncryptionDecryptionUtil.getDecryptedString(changePassword.getOldPassword());
                boolean oldp = bcrypt.matches(oldPassword, eo.getBcryptPassword());
                if (oldp) {
                	
                	String newPassword =EncryptionDecryptionUtil.getDecryptedString(changePassword.getNewPassword());
                    eo.setBcryptPassword(bcrypt.encode(newPassword));
                    if(eo.getPasswordChangeDate()==null && (eo.getIsPasswordChange()==null || eo.getIsPasswordChange().equals(false))) {
                    	eo.setPasswordChangeDate(DateUtils.obtainCurrentTimeStamp());
    				}
                    eo.setIsPasswordChange(true);
                    //eo.setUpdatedBy(eo.getUserId());
                    //eo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
                    session.update(eo);
                    tx.commit();
                    UserActionLog userActionLog = new UserActionLog();
                    userActionLog.setId(null);
                    userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                    if (request != null) {
                        userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
                        if (userActionLog.getIpAddress() == null || "".equals(userActionLog.getIpAddress())) {
                            userActionLog.setIpAddress(request.getRemoteAddr());
                        }
                    }
                    userActionLog.setIpAddress(request.getRemoteAddr());
                    userActionLog.setUserId(userInfo.getUsername());
                    userActionLog.setActionId(Constant.CHANGE_PASSWORD);
                    saveUserActionLog(userActionLog);
                    return true;
                } else {
                    return false;
                }

            }
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                logger.info("Couldn’t roll back transaction" + rbe.getMessage());
            }
            logger.info("Update All Offices Details Info Display" + e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public void saveCaptcha(CaptchaValidationEO validationEO) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(validationEO);
            tx.commit();
        }catch (Exception e){
            logger.error("error info {} ", e.getMessage());
            if(null!=tx && tx.isActive()){
                tx.rollback();
            }
        }

    }

    @Override
    public Boolean getCaptcha(String captcha) {
        try(Session session= sessionFactory.openSession()){
            LocalDateTime dateTime = DateUtils.obtainCurrentTimeStamp().plusHours(1);
            return (Boolean) session.createNativeQuery("select case when count(*) > 0 then false else true end as result  from captcha_valid  where captcha=:captcha "
                            + " and date_time<='" + dateTime + "' AND status is true").setParameter("captcha", captcha)
                    
                    .uniqueResult();
        }catch (Exception e){
            logger.error("error info {} ", e.getMessage());
        }
        return false;
    }

	@Override
	public boolean fetchTokenNotExpired(String token) {
		 Session session = sessionFactory.openSession();
	     try {
	         LocalDateTime dateTime = DateUtils.obtainCurrentTimeStamp().plusHours(2);
	         Long count = (Long) session.createQuery("select count(*) from UserAesUsedPassword where token=:token "
	                         + " and dateTime<='" + dateTime + "' AND isTokenExpired is true").setParameter("token", token)
	                 .uniqueResult();
	         return count > 0 ? true : false;
	     } catch (Exception e) {
	         logger.error("Generic Query can not be completed for get output of {} due to this exception", e.getMessage());

	     } finally {
	         session.close();
	     }
	     return false;
	 }

}