package aishe.gov.in.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.enums.SurveyAction;
import aishe.gov.in.enums.SurveyActionLog;
import aishe.gov.in.masterseo.RefSurveyAction;
import aishe.gov.in.masterseo.SurveyMasterEO;
import aishe.gov.in.masterseo.SurveyMasterLogEO;
import aishe.gov.in.masterseo.SurveyMasterNewEO;
import aishe.gov.in.masterseo.SurveyMastersLogEO;
import aishe.gov.in.masterseo.SurveyStatusLogsEO;
import aishe.gov.in.mastersvo.SurveyMasterDTO;
import aishe.gov.in.mastersvo.SurveyMasterNewVO;
import aishe.gov.in.utility.DateUtils;

@Repository
public class SurveyYearDaoImpl implements SurveyYearDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RefMasterDao refMasterDao;

    private static final Logger logger = LoggerFactory.getLogger(RegisterationDaoImpl.class);

    @Override
    public SurveyMasterEO getSurveyMaster(Integer surveyYear) {
        logger.info("SurveyYearDaoImpl : getSurveyMaster method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SurveyMasterEO> criteriaQuery = builder.createQuery(SurveyMasterEO.class);
            Root<SurveyMasterEO> root = criteriaQuery.from(SurveyMasterEO.class);
            String surveyYearConstant = "surveyYear";
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get(surveyYearConstant), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.desc(root.get(surveyYearConstant)));

            Query<SurveyMasterEO> q = session.createQuery(criteriaQuery);
            List<SurveyMasterEO> list = q.getResultList();
            if (list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<SurveyMasterEO> getSurveyMasterYearList(Integer surveyYear) {
        logger.info("SurveyYearDaoImpl : getSurveyMasterYearList method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SurveyMasterEO> criteriaQuery = builder.createQuery(SurveyMasterEO.class);
            Root<SurveyMasterEO> root = criteriaQuery.from(SurveyMasterEO.class);
            String surveyYearConstant = "surveyYear";
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get(surveyYearConstant), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.desc(root.get(surveyYearConstant)));

            Query<SurveyMasterEO> q = session.createQuery(criteriaQuery);
            List<SurveyMasterEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<SurveyMasterEO> getSurveyMasters(Integer surveyYear) {
        logger.info("SurveyYearDaoImpl : getSurveyMasters method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SurveyMasterEO> criteriaQuery = builder.createQuery(SurveyMasterEO.class);
            Root<SurveyMasterEO> root = criteriaQuery.from(SurveyMasterEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.desc(root.get("surveyYear")));

            Query<SurveyMasterEO> q = session.createQuery(criteriaQuery);
            List<SurveyMasterEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<SurveyMastersLogEO> getSurveyMasterLog(Integer surveyYear, String type) {
        logger.info("SurveyYearDaoImpl : getSurveyMasters method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SurveyMastersLogEO> criteriaQuery = builder.createQuery(SurveyMastersLogEO.class);
            Root<SurveyMastersLogEO> root = criteriaQuery.from(SurveyMastersLogEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            if (type.equals(SurveyActionLog.CREATE_SURVEY.getActionType())) {
            	predicates.add(root.get("actionId").get("id").in(1,2,3,4));
            }
            if (type.equals(SurveyActionLog.REGISTRATION.getActionType())) {
            	predicates.add(root.get("actionId").get("id").in(5,6,7));
            }
            if (type.equals(SurveyActionLog.SPECIAL_PERMISSION.getActionType())) {
            	predicates.add(root.get("actionId").get("id").in(11,12,13));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.desc(root.get("actionDate")));

            Query<SurveyMastersLogEO> q = session.createQuery(criteriaQuery);
            List<SurveyMastersLogEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<SurveyStatusLogsEO> getSurveyStatusLog(Integer surveyYear) {
        logger.info("SurveyYearDaoImpl : getSurveyMasters method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SurveyStatusLogsEO> criteriaQuery = builder.createQuery(SurveyStatusLogsEO.class);
            Root<SurveyStatusLogsEO> root = criteriaQuery.from(SurveyStatusLogsEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.desc(root.get("actionDate")));

            Query<SurveyStatusLogsEO> q = session.createQuery(criteriaQuery);
            List<SurveyStatusLogsEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public Integer MaxSurveyYear() {
        logger.info("SurveyYearDaoImpl : saveSurveyMaster method invoked :");
        Integer maxSurveyYear = null;
        Session session = sessionFactory.openSession();
        try {
            maxSurveyYear = ((Integer) session.createQuery("select max(surveyYear) from RefUniversity ").uniqueResult());
            return maxSurveyYear;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return maxSurveyYear;
    }

    @Override
    public SurveyMasterEO saveSurveyMaster(SurveyMasterEO surveyMasterEO) {
        logger.info("SurveyYearDaoImpl : saveSurveyMaster method invoked :");
        Session session = sessionFactory.openSession();
        Session session1 = sessionFactory.openSession();
        Integer id = null;
        Transaction tx = null;
        //  Integer maxSurveyYear;
        try {
            tx = session.beginTransaction();
            session.save(surveyMasterEO);
            //  maxSurveyYear = ((Integer) session1.createQuery("select max(surveyYear) from RefUniversity ").uniqueResult());
            id = ((Integer) session1.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
                    .builder()
                    .userId(surveyMasterEO.getUserId())
                    .id(id + 1)
                    .surveyYear(surveyMasterEO.getSurveyYear())
                    .actionId(SurveyAction.CREATE.getActionType())
                    .actionDate(new Timestamp(new Date().getTime()))
                    .startDate(surveyMasterEO.getStartDate())
                    .endDate(surveyMasterEO.getEndDate())
                    .build();
            session.save(surveyMasterLogEO);

            tx.commit();
            return surveyMasterEO;
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
            session1.close();
        }
        return null;
    }

    @Override
    public SurveyMasterEO updateSurveyMaster(SurveyMasterEO masterEO, LocalDateTime prevStartLocalDateTime, LocalDateTime prevEndLocalDateTime) {
        logger.info("SurveyYearDaoImpl : updateSurveyMaster method invoked :");
        Session session = sessionFactory.openSession();
        Session session1 = sessionFactory.openSession();
        Integer id = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(masterEO);
            id = ((Integer) session1.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
                    .builder()
                    .userId(masterEO.getUserId())
                    .id(id + 1)
                    .surveyYear(masterEO.getSurveyYear())
                    .actionId(SurveyAction.EDIT.getActionType())
                    .actionDate(new Timestamp(new Date().getTime()))
                    .startDate(masterEO.getStartDate() != null ? masterEO.getStartDate() : null)
                    .endDate(masterEO.getEndDate() != null ? masterEO.getEndDate() : null)
                    .prevStartDate(prevStartLocalDateTime)
                    .prevEndDate(prevEndLocalDateTime)
                    .build();
            session.save(surveyMasterLogEO);
            tx.commit();
            return masterEO;
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
            session1.close();
        }
        return null;
    }
    @Override
    public Timestamp getMaxSurveyYearFromUpload(Integer surveyYear) {
        logger.info("SurveyYearDaoImpl : getMaxSurveyYearFromUpload method invoked :");
        Timestamp maxSurveyYear = null;
        Session session = sessionFactory.openSession();
        try {
            maxSurveyYear = (Timestamp) session.createNativeQuery("select Max(fu.upload_date) from public.form_upload fu where fu.survey_year= "+surveyYear).uniqueResult();
            return maxSurveyYear;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return maxSurveyYear;
    }
    @Override
    public Timestamp getMinSurveyYearFromUpload(Integer surveyYear) {
        logger.info("SurveyYearDaoImpl : getMinSurveyYearFromUpload method invoked :");
        Timestamp minSurveyYear = null;
        Session session = sessionFactory.openSession();
        try {
            minSurveyYear = (Timestamp) session.createNativeQuery("select Min(fu.upload_date) from public.form_upload fu where fu.survey_year= "+surveyYear).uniqueResult();
            return minSurveyYear;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return minSurveyYear;
    }

	@Override
	public List<RefSurveyAction> getSurveyAction() {
		 logger.info("SurveyYearDaoImpl : getSurveyMasterYearList method invoked :");
	        Session session = sessionFactory.openSession();
	        try {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<RefSurveyAction> criteriaQuery = builder.createQuery(RefSurveyAction.class);
	            Root<RefSurveyAction> root = criteriaQuery.from(RefSurveyAction.class);
	            criteriaQuery.select(root).orderBy(builder.asc(root.get("id")));
	            Query<RefSurveyAction> q = session.createQuery(criteriaQuery);
	            List<RefSurveyAction> list = q.getResultList();
	            return list;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public List<SurveyMasterNewVO> getSurveyDataMasterList(Integer surveyYear,Boolean isSurveyFreezed) {
		  Session session = sessionFactory.openSession();
	        try {
	            StringBuilder queryBuilder = new StringBuilder();
	           
	                queryBuilder.append("select survey_year,start_date,end_date,registration_start_date,registration_end_date,survey_start_date,survey_end_date,special_permission_start_date"
	                		+ ", special_permission_end_date,is_survey_freezed from survey_master_new \r\n");
	                
	                if(!isSurveyFreezed) {
		            	   queryBuilder.append(" where (is_survey_freezed !=true or is_survey_freezed is null) "); 
		            	   if(surveyYear!=null) {
			            	   queryBuilder.append(" and survey_year ="+surveyYear+"");  
			               }
		               }else {
		            	   if(surveyYear!=null) {
			            	   queryBuilder.append(" where survey_year ="+surveyYear+"");  
			               }
		               }
	               queryBuilder.append(" order by survey_year asc"); 
	            Query query = session.createNativeQuery(queryBuilder.toString());
	            List<SurveyMasterNewVO> postMenuList = new ArrayList<>();

	            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
	            for (Object[] object : objectlist) {
	            	SurveyMasterNewVO postMenuData = new SurveyMasterNewVO();
	            	 if (object[0] != null) {
	  	               postMenuData.setSurveyYear(Integer.parseInt(object[0].toString()));
	  	             }
	            	 if (object[1] != null) {
	                       postMenuData.setStartDate(object[1] + "");
	                       Timestamp ts = Timestamp.valueOf(postMenuData.getStartDate());
	                       LocalDateTime dob = ts.toLocalDateTime();
	                       postMenuData.setStartDate(DateUtils.convertDBDateTimeToStringNew(dob));
	                }
	            	 if (object[2] != null) {
	                       postMenuData.setEndDate(object[2] + "");
	                       Timestamp ts = Timestamp.valueOf(postMenuData.getEndDate());
	                       LocalDateTime dob = ts.toLocalDateTime();//.toLocalDate();
	                       postMenuData.setEndDate(DateUtils.convertDBDateTimeToStringNew(dob));
	                }
	            	if (object[3] != null) {
	                       postMenuData.setRegistrationStartDate(object[3] + "");
	                       Timestamp ts = Timestamp.valueOf(postMenuData.getRegistrationStartDate());
	                       LocalDateTime dob = ts.toLocalDateTime();//.toLocalDate();
	                       postMenuData.setRegistrationStartDate(DateUtils.convertDBDateTimeToStringNew(dob));
	                }
	            	if (object[4] != null) {
	                       postMenuData.setRegistrationEndDate(object[4] + "");
	                       Timestamp ts = Timestamp.valueOf(postMenuData.getRegistrationEndDate());
	                       LocalDateTime dob = ts.toLocalDateTime();//.toLocalDate();
	                       postMenuData.setRegistrationEndDate(DateUtils.convertDBDateTimeToStringNew(dob));
	                }
	            	if (object[5] != null) {
	                       postMenuData.setSurveyStartDate(object[5] + "");
	                       Timestamp ts = Timestamp.valueOf(postMenuData.getSurveyStartDate());
	                       LocalDateTime dob = ts.toLocalDateTime();//.toLocalDate();
	                       postMenuData.setSurveyStartDate(DateUtils.convertDBDateTimeToStringNew(dob));
	                }
	            	if (object[6] != null) {
	                       postMenuData.setSurveyEndDate(object[6] + "");
	                       Timestamp ts = Timestamp.valueOf(postMenuData.getSurveyEndDate());
	                       LocalDateTime dob = ts.toLocalDateTime();//.toLocalDate();
	                       postMenuData.setSurveyEndDate(DateUtils.convertDBDateTimeToStringNew(dob));
	                }
	            	 if (object[7] != null) {
	                       postMenuData.setSpecialPermissionStartDate(object[7] + "");
	                       Timestamp ts = Timestamp.valueOf(postMenuData.getSpecialPermissionStartDate());
	                       LocalDateTime dob = ts.toLocalDateTime();//.toLocalDate();
	                       postMenuData.setSpecialPermissionStartDate(DateUtils.convertDBDateTimeToStringNew(dob));
	                }
	            	 if (object[8] != null) {
	                       postMenuData.setSpecialPermissionEndDate(object[8] + "");
	                       Timestamp ts = Timestamp.valueOf(postMenuData.getSpecialPermissionEndDate());
	                       LocalDateTime dob = ts.toLocalDateTime();//.toLocalDate();
	                       postMenuData.setSpecialPermissionEndDate(DateUtils.convertDBDateTimeToStringNew(dob));
	                }
	            	if (object[9] != null) {
		  	               postMenuData.setIsSurveyFreezed(Boolean.parseBoolean(object[9].toString()));
		  	        }
	                postMenuList.add(postMenuData);
	            }
	            return postMenuList;
	        } catch (Exception e) {
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public SurveyMasterNewEO saveSurveyMaster(SurveyMasterNewEO surveyMasterEO,Integer creationType) {
		 logger.info("SurveyYearDaoImpl : saveSurveyMaster method invoked :");
	        Session session = sessionFactory.openSession();
	        Session session1 = sessionFactory.openSession();
	        Integer id = null;
	        Transaction tx = null;
	        Transaction tx1 = null;
	        try {
	            tx = session.beginTransaction();
	            tx1 = session1.beginTransaction();
	            //Integer surveyYear = fetchSurveyYearIsAlreadyCreated(surveyMasterEO.getSurveyYear());
	            SurveyMasterNewEO surveyYear = (SurveyMasterNewEO)session.get(SurveyMasterNewEO.class,surveyMasterEO.getSurveyYear());
	            if(surveyYear==null) {
	            session.save(surveyMasterEO);
	            id = ((Integer) session1.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
	            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
	                    .builder()
	                    .userId(surveyMasterEO.getUserId())
	                    .id(id + 1)
	                    .surveyYear(surveyMasterEO.getSurveyYear())
	                    .actionId(creationType)//SurveyAction.CREATE.getActionType())
	                    .actionDate(new Timestamp(new Date().getTime()))
	                    .startDate(surveyMasterEO.getStartDate())
	                    .endDate(surveyMasterEO.getEndDate())
	                    .build();
	            session.save(surveyMasterLogEO);
	            }else {
	            	 BeanUtils.copyProperties(surveyYear, surveyMasterEO);
	            	surveyMasterEO.setSurveyStartDate(surveyMasterEO.getSurveyStartDate());
	            	surveyMasterEO.setSurveyEndDate(surveyMasterEO.getSurveyEndDate());
	            	surveyMasterEO.setStartDate(surveyMasterEO.getStartDate());
	            	surveyMasterEO.setEndDate(surveyMasterEO.getEndDate());
	            	session1.update(surveyMasterEO);
	            	
	            	
	            	// session.createNativeQuery("update survey_master_new set end_date ='"+surveyMasterEO.getEndDate()+"',start_date='"+surveyMasterEO.getStartDate()+"'"
	            	 //		+ ",survey_end_date='"+surveyMasterEO.getSurveyEndDate()+"', survey_start_date='"+surveyMasterEO.getSurveyStartDate()+"' where survey_year = "+surveyMasterEO.getSurveyYear()+""
	            	// 		+ "").executeUpdate();
	            	
	            	 //session.update(surveyMasterEO);
	 	            id = ((Integer) session1.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
	 	            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
	 	                    .builder()
	 	                    .userId(surveyMasterEO.getUserId())
	 	                    .id(id + 1)
	 	                    .surveyYear(surveyMasterEO.getSurveyYear())
	 	                   .actionId(creationType)//(SurveyAction.EDIT.getActionType())
	 	                    .actionDate(new Timestamp(new Date().getTime()))
	 	                    .startDate(surveyMasterEO.getStartDate())
	 	                    .endDate(surveyMasterEO.getEndDate())
	 	                    .build();
	 	            session.save(surveyMasterLogEO);
	            }
	            tx.commit();
	            tx1.commit();
	            return surveyMasterEO;
	        } catch (Exception e) {
	            try {
	                if (tx != null && tx.isActive() || tx1 != null && tx1.isActive()) {
	                    tx.rollback();tx1.rollback();
	                }
	            } catch (Exception trEx) {
	                e.printStackTrace();
	            }
	        } finally {
	            session.close();
	            session1.close();
	        }
	        return surveyMasterEO;
	    }

	private Integer fetchSurveyYearIsAlreadyCreated(Integer surveyYear) {
		 Session session = sessionFactory.openSession();
	        try {
	            Integer survey = (Integer) session.createNativeQuery("select survey_year from survey_master_new where survey_year="+surveyYear+" and start_date is not null")
	                    .uniqueResult();
	            return survey;
	        } catch (Exception e) {
	            logger.info("isUserNameExist Error" + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public Integer fetchRegisterSurveyYearIsAlready(Integer surveyYear) {
	        Session session = sessionFactory.openSession();
	        try {
	            Integer survey = (Integer) session.createNativeQuery("select survey_year from survey_master_new where (registration_start_date is not null and survey_year ="+surveyYear+")")
	                    .uniqueResult();
	            return survey;
	        } catch (Exception e) {
	            logger.info("isUserNameExist Error" + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public Integer fetchSurveyYearIsAlready(Integer surveyYear) {
		  Session session = sessionFactory.openSession();
	        try {
	            Integer survey = (Integer) session.createNativeQuery("select survey_year from survey_master_new where (start_date is not null and survey_year ="+surveyYear+")")
	                    .uniqueResult();
	            return survey;
	        } catch (Exception e) {
	            logger.info("isUserNameExist Error" + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public SurveyMasterNewEO saveSurveyYearSpecialPermission(SurveyMasterNewEO surveyMasterEO,Integer surveyCreationType) {
		 logger.info("SurveyYearDaoImpl : saveSurveyMaster method invoked :");
	        Session session = sessionFactory.openSession();
	        Session session1 = sessionFactory.openSession();
	        Integer id = null;
	        Transaction tx = null;
	        try {
	            tx = session.beginTransaction();
	           LocalDateTime specialStart = surveyMasterEO.getSpecialPermissionStartDate();
	           LocalDateTime specialEnd = surveyMasterEO.getSpecialPermissionEndDate();
	           String userId = surveyMasterEO.getUserId();
	           // Integer surveyYear = fetchSurveyYearIsAlreadyCreatedForSpecialPermission(surveyMasterEO.getSurveyYear());
	            SurveyMasterNewEO surveyYear = (SurveyMasterNewEO)session1.get(SurveyMasterNewEO.class,surveyMasterEO.getSurveyYear());
	            BeanUtils.copyProperties(surveyYear, surveyMasterEO);
	            surveyMasterEO.setSpecialPermissionStartDate(specialStart);
	            surveyMasterEO.setSpecialPermissionEndDate(specialEnd);
	            surveyMasterEO.setUserId(userId);
	            if(surveyYear==null) {
	            session.save(surveyMasterEO);
	            id = ((Integer) session1.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
	            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
	                    .builder()
	                    .userId(surveyMasterEO.getUserId())
	                    .id(id + 1)
	                    .surveyYear(surveyMasterEO.getSurveyYear())
	                    .actionId(surveyCreationType)//SurveyAction.SPECIAL_PERMISSION_CREATE.getActionType())
	                    .actionDate(new Timestamp(new Date().getTime()))
	                    .startDate(surveyMasterEO.getStartDate())
	                    .endDate(surveyMasterEO.getEndDate())
	                    .build();
	            session.save(surveyMasterLogEO);
	            }else {
	            	  session.update(surveyMasterEO);
	  	            id = ((Integer) session1.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
	  	            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
	  	                    .builder()
	  	                    .userId(surveyMasterEO.getUserId())
	  	                    .id(id + 1)
	  	                    .surveyYear(surveyMasterEO.getSurveyYear())
	  	                    .actionId(surveyCreationType)//SurveyAction.SPECIAL_PERMISSION_START_EDIT.getActionType())
	  	                    .actionDate(new Timestamp(new Date().getTime()))
	  	                    .startDate(surveyMasterEO.getStartDate())
	  	                    .endDate(surveyMasterEO.getEndDate())
	  	                    .build();
	  	            session.save(surveyMasterLogEO);
	            }
	            tx.commit();
	            return surveyMasterEO;
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
	            session1.close();
	        }
	        return null;
	    }

	private Integer fetchSurveyYearIsAlreadyCreatedForSpecialPermission(Integer surveyYear) {
		Session session = sessionFactory.openSession();
        try {
            Integer survey = (Integer) session.createNativeQuery("select survey_year from survey_master_new where survey_year="+surveyYear+" and special_permission_start_date is not null")
                    .uniqueResult();
            return survey;
        } catch (Exception e) {
            logger.info("isUserNameExist Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

	@Override
	public SurveyMasterNewEO saveRegistrationSurveyYear(SurveyMasterNewEO surveyMasterEO,Integer surveyCreationType) {
		 logger.info("SurveyYearDaoImpl : saveSurveyMaster method invoked :");
	        Session session = sessionFactory.openSession();
	        Session session1 = sessionFactory.openSession();
	        Integer id = null;
	        Transaction tx = null;
	        Transaction tx1 = null;
	        try {
	            tx = session.beginTransaction();
	            tx1 = session1.beginTransaction();
	            //Integer surveyYear = fetchSurveyYearIsAlreadyCreatedForRegistration(surveyMasterEO.getSurveyYear());
	            SurveyMasterNewEO surveyYear = (SurveyMasterNewEO)session.get(SurveyMasterNewEO.class,surveyMasterEO.getSurveyYear());
	            if(surveyYear==null) {
	            session.save(surveyMasterEO);
	            id = ((Integer) session1.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
	            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
	                    .builder()
	                    .userId(surveyMasterEO.getUserId())
	                    .id(id + 1)
	                    .surveyYear(surveyMasterEO.getSurveyYear())
	                    .actionId(surveyCreationType)//SurveyAction.REGISTRATION_CREATE.getActionType())
	                    .actionDate(new Timestamp(new Date().getTime()))
	                    .startDate(surveyMasterEO.getStartDate())
	                    .endDate(surveyMasterEO.getEndDate())
	                    .build();
	            session.save(surveyMasterLogEO);
	            }else {
	            	BeanUtils.copyProperties(surveyYear, surveyMasterEO);
	            	surveyMasterEO.setRegistrationEndDate(surveyMasterEO.getRegistrationEndDate());
	            	surveyMasterEO.setRegistrationStartDate(surveyMasterEO.getRegistrationStartDate());
	            	session1.update(surveyMasterEO);
	            	// session.createNativeQuery("update survey_master_new set registration_end_date ='"+surveyMasterEO.getRegistrationEndDate()+"',registration_start_date='"+surveyMasterEO.getRegistrationStartDate()+"'"
		            //	 		+ " where survey_year = "+surveyMasterEO.getSurveyYear()+""
		            //	 		+ " ").executeUpdate();
	            	 //session.update(surveyMasterEO);
	 	            id = ((Integer) session1.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
	 	            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
	 	                    .builder()
	 	                    .userId(surveyMasterEO.getUserId())
	 	                    .id(id + 1)
	 	                    .surveyYear(surveyMasterEO.getSurveyYear())
	 	                    .actionId(surveyCreationType)//SurveyAction.REGISTRATION_START_EDIT.getActionType())
	 	                    .actionDate(new Timestamp(new Date().getTime()))
	 	                    .startDate(surveyMasterEO.getStartDate())
	 	                    .endDate(surveyMasterEO.getEndDate())
	 	                    .build();
	 	            session.save(surveyMasterLogEO);	
	            }
	            tx.commit();tx1.commit();
	            return surveyMasterEO;
	        } catch (Exception e) {
	            try {
	                if (tx != null && tx.isActive() || tx1 != null && tx1.isActive() ) {
	                    tx.rollback();
	                    tx1.rollback();
	                }
	            } catch (Exception trEx) {
	                e.printStackTrace();
	            }
	        } finally {
	            session.close();
	            session1.close();
	        }
	        return surveyMasterEO;
	    }

	private Integer fetchSurveyYearIsAlreadyCreatedForRegistration(Integer surveyYear) {
		 Session session = sessionFactory.openSession();
	        try {
	            Integer survey = (Integer) session.createNativeQuery("select survey_year from survey_master_new where survey_year="+surveyYear+" and registration_start_date is not null")
	                    .uniqueResult();
	            return survey;
	        } catch (Exception e) {
	            logger.info("isUserNameExist Error" + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public Long fetchSpecialPermissionForSurveyYearCanCreated(@NotNull Integer surveyYear) {
		 Session session = sessionFactory.openSession();
	        try {
	        	Long surveyCount=null;
	        	BigInteger survey = (BigInteger) session.createNativeQuery("select count(*) from survey_master_new where survey_year ="+surveyYear+"")
	                    .uniqueResult();
	           if(survey!=null) {
	        	   surveyCount = Long.valueOf(survey.toString());
	           }
	        	return surveyCount;
	        } catch (Exception e) {
	            logger.info("fetchSpecialPermissionForSurveyYearCanCreated Error" + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public SurveyMasterDTO updateFreezeSurveyYear(@Valid SurveyMasterDTO surveyMasterEO) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;	
		try {
			tx = session.beginTransaction();
					 session.createNativeQuery("update survey_master_new set is_survey_freezed =true where survey_year = "+surveyMasterEO.getSurveyYear()+"").executeUpdate();
					 Integer id = ((Integer) session.createQuery("select max(id) from SurveyMasterLogEO ").uniqueResult());
		 	            SurveyMasterLogEO surveyMasterLogEO = SurveyMasterLogEO
		 	                    .builder()
		 	                    .userId(surveyMasterEO.getUserId())
		 	                    .id(id + 1)
		 	                    .surveyYear(surveyMasterEO.getSurveyYear())
		 	                    .actionId(14)//SurveyAction.REGISTRATION_START_EDIT.getActionType())
		 	                    .actionDate(new Timestamp(new Date().getTime()))
		 	                    .prevStartDate(DateUtils.obtainCurrentTimeStamp())
		 	                    .prevEndDate(DateUtils.obtainCurrentTimeStamp())
		 	                    .build();
		 	            session.save(surveyMasterLogEO);
		 	           surveyMasterEO.setMessage("Success");
			tx.commit();
			return surveyMasterEO;
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
		return null;
	}

	@Override
	public Integer fetchCreatedSurveyYearIsAlready(@NotNull Integer surveyYear) {
		 Session session = sessionFactory.openSession();
	        try {
	            Integer survey = (Integer) session.createNativeQuery("select survey_year from survey_master_new where survey_year ="+surveyYear+" and start_date is not null")
	                    .uniqueResult();
	            return survey;
	        } catch (Exception e) {
	            logger.info("isUserNameExist Error" + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return null;
	    }
}