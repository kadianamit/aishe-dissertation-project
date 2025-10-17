package aishe.gov.in.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.masterseo.UniversityEO;


@Repository
public class UniversityAisheDaoImpl implements UniversityAisheDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(UniversityAisheDaoImpl.class);
	@Override
	public boolean saveOrUpdateUniversityRegulatoryInformation(UniversityEO university) {
		logger.info("MasterDaoImpl : saveOrUpdateUniversityRegulatoryInformation method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(university);
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
			logger.info("saveOrUpdateUniversityRegulatoryInformation Error" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public UniversityEO fetchUniversityInformation(String universityId, Integer surveyYear) {
		logger.info("MasterDaoImpl : saveOrUpdateUniversityRegulatoryInformation method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityEO> query = builder.createQuery(UniversityEO.class);
			Root<UniversityEO> allData = query.from(UniversityEO.class);
				query.where(builder.and(builder.equal(allData.get("id"), universityId),
						builder.equal(allData.get("surveyYear"), surveyYear)));
				UniversityEO university = session.createQuery(query).getSingleResult();
			        return university;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("saveOrUpdateApplicationTypeDetails Error" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	
	@Override
	public boolean saveOrUpdateUniversityOffShoreCentreInformation(UniversityOffShoreCenterVO offShoreCentre) {
		logger.info("ModuleDaoImpl : saveModuleActivityMapping method invoked :");
		Session session1 = sessionFactory.openSession();
		Session session2 = null;
		Transaction tx = null;
		OffShoreCentreEO offShoreCentreEo = new OffShoreCentreEO();
		try {
			offShoreCentreEo = (OffShoreCentreEO) session1.get(OffShoreCentreEO.class, offShoreCentre.getId());
			session2 = sessionFactory.openSession();
			tx = session2.beginTransaction();
			UniversityOffShoreEO offshore = new UniversityOffShoreEO();
			offshore = fetchUniversityInformationOffShoreCentre(offShoreCentre.getUniversityId(),offShoreCentre.getSurveyYear());
			if (offShoreCentreEo == null) {
				BeanUtils.copyProperties(offShoreCentre, offShoreCentreEo);
				session2.save(offShoreCentreEo);
				Integer offShoreId = offShoreCentreEo.getId();
                 if(offshore==null) {
                	 offshore.setUniversityId(offShoreCentre.getUniversityId());
                	 offshore.setSurveyYear(offShoreCentre.getSurveyYear());
                	 offshore.setOffShoreCenterId(offShoreId);
                	 session2.save(offshore);
                 }
			} else {
				BeanUtils.copyProperties(offShoreCentre, offShoreCentreEo);
				     session2.update(offShoreCentreEo);
				     if(offshore!=null) {
						 offshore.setUniversityId(offShoreCentre.getUniversityId());
	                	 offshore.setSurveyYear(offShoreCentre.getSurveyYear());
	                	 offshore.setOffShoreCenterId(offShoreCentreEo.getId());
	                  	 session2.update(offshore);
	                }
			}
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
			logger.info("saveModuleActivityMapping Error" + e.getMessage());
		} finally {
			session1.close();
			session2.close();
		}
		return false;
	}

	private UniversityOffShoreEO fetchUniversityInformationOffShoreCentre(String universityId, Integer surveyYear) {
			logger.info("MasterDaoImpl : saveOrUpdateUniversityRegulatoryInformation method invoked :");
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<UniversityOffShoreEO> query = builder.createQuery(UniversityOffShoreEO.class);
				Root<UniversityOffShoreEO> allData = query.from(UniversityOffShoreEO.class);
					query.where(builder.and(builder.equal(allData.get("universityId"), universityId),
							builder.equal(allData.get("surveyYear"), surveyYear)));
					UniversityOffShoreEO university = session.createQuery(query).getSingleResult();
				        return university;
			} catch (Exception e) {
				try {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
				} catch (Exception trEx) {
					logger.error("Couldn’t roll back transaction" + trEx.getMessage());
				}
				logger.info("saveOrUpdateApplicationTypeDetails Error" + e.getMessage());
			} finally {
				session.close();
			}
			return null;
		}

	@Override
	public boolean saveOrUpdateUniversityAccrediationInformation(AccreditationVO accreditationVO) {
		logger.info("ModuleDaoImpl : saveModuleActivityMapping method invoked :");
		Session session1 = sessionFactory.openSession();
		Session session2 = null;
		Transaction tx = null;
		Accreditation accreditation = new Accreditation();
		try {
			for(int i =0;i<=accreditationVO.getAccreditationTab().size();i++) {
			accreditation = (Accreditation) session1.get(Accreditation.class, accreditationVO.getAccreditationTab().get(i).getId());
			session2 = sessionFactory.openSession();
			tx = session2.beginTransaction();
			UniversityAccreditation university = new UniversityAccreditation();
			university = fetchUniversityInformationFromAccrediation(accreditationVO.getUniversityId(),accreditationVO.getSurveyYear());
			
			if (accreditation == null) {
				accreditation.setAccreditationBody(accreditationVO.getAccreditationTab().get(i).getAccrediationBodyId());
				accreditation.setScore(accreditationVO.getAccreditationTab().get(i).getScore());
				accreditation.setMaxScore(accreditationVO.getAccreditationTab().get(i).getMaxScore());
				accreditation.setHasScore(accreditationVO.getAccreditationTab().get(i).getIsScoreProvided());
				session2.save(accreditation);
				Integer accId = accreditation.getId();
				if(university==null) {
		            university.setUniversityId(accreditationVO.getUniversityId());
		           	university.setSurveyYear(accreditationVO.getSurveyYear());
		           	Accreditation accreditationeo = new Accreditation();
		           	accreditationeo.setId(accId);
		           	university.setAccreditation(accreditationeo);
		           	session2.save(university);
		            }
			} else {
				accreditation.setAccreditationBody(accreditationVO.getAccreditationTab().get(i).getAccrediationBodyId());
				accreditation.setScore(accreditationVO.getAccreditationTab().get(i).getScore());
				accreditation.setMaxScore(accreditationVO.getAccreditationTab().get(i).getMaxScore());
				accreditation.setHasScore(accreditationVO.getAccreditationTab().get(i).getIsScoreProvided());
				session2.update(accreditation);
				Integer accId = accreditation.getId();
				if(university!=null) {
		            university.setUniversityId(accreditationVO.getUniversityId());
		           	university.setSurveyYear(accreditationVO.getSurveyYear());
		           	Accreditation accreditationeo = new Accreditation();
		           	accreditationeo.setId(accId);
		           	university.setAccreditation(accreditationeo);
		           	session2.update(university);
		            }
			}
			}
			
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
			logger.info("saveModuleActivityMapping Error" + e.getMessage());
		} finally {
			session1.close();
			session2.close();
		}
		return false;
	}

	private UniversityAccreditation fetchUniversityInformationFromAccrediation(String universityId,
			Integer surveyYear) {
		logger.info("MasterDaoImpl : saveOrUpdateUniversityRegulatoryInformation method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityAccreditation> query = builder.createQuery(UniversityAccreditation.class);
			Root<UniversityAccreditation> allData = query.from(UniversityAccreditation.class);
				query.where(builder.and(builder.equal(allData.get("universityId"), universityId),
						builder.equal(allData.get("surveyYear"), surveyYear)));
				UniversityAccreditation university = session.createQuery(query).getSingleResult();
			        return university;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("saveOrUpdateApplicationTypeDetails Error" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean saveOrUpdateUniversityScholarshipInformation(UniversityScholarshipVO universityScholarshipVO) {
		logger.info("MasterDaoImpl : saveOrUpdateUniversityScholarshipInformation method invoked :");
		Session session1 = sessionFactory.openSession();
		Session session2 = null;
		Transaction tx = null;
		UniversityFormEO university = new UniversityFormEO();
		try {
			university = fetchUniversityInformationScholarShip(universityScholarshipVO.getUniversityId(),
					universityScholarshipVO.getSurveyYear(),universityScholarshipVO.getId());
			PersonsCountByCategory personCountByCatrgory = new PersonsCountByCategory();
			Scholarship scholarship = new Scholarship();
			UniversityFormEO universitysave = new UniversityFormEO();
			session2 = sessionFactory.openSession();
			tx = session2.beginTransaction();
			if (university!=null) {
				BeanUtils.copyProperties(universityScholarshipVO, personCountByCatrgory);
				Integer personCountByCatrgoryId = ((Integer) session1.createNativeQuery("select count_by_category_id from public.scholarship where id = '"+universityScholarshipVO.getId()+"'").uniqueResult());		
				personCountByCatrgory.setId(personCountByCatrgoryId);
				session2.saveOrUpdate(personCountByCatrgory);
				scholarship.setCountByCategory(personCountByCatrgory);
				//Integer scholarId = ((Integer) session.createQuery("select max(id) from Scholarship").uniqueResult());
				scholarship.setId(universityScholarshipVO.getId());
				session2.saveOrUpdate(scholarship);
				Integer scholarshipId = scholarship.getCountByCategory().getId();
				university.setScholarshipId(scholarshipId);
				BeanUtils.copyProperties(university, universitysave);
				session2.update(universitysave);
		      	}
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
			logger.info("saveOrUpdateEnrolledRegularStudent Error" + e.getMessage());
		} finally {
			session1.close();session2.close();
		}
		return true;
	}

	private UniversityFormEO fetchUniversityInformationScholarShip(String universityId, Integer surveyYear,
			Integer id) {
		logger.info("MasterDaoImpl : fetchUniversityInformationScholarShip method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityFormEO> query = builder.createQuery(UniversityFormEO.class);
			Root<UniversityFormEO> allData = query.from(UniversityFormEO.class);
				query.where(builder.and(builder.equal(allData.get("id"), universityId),
						builder.equal(allData.get("surveyYear"), surveyYear),
						builder.equal(allData.get("scholarshipId"), id)));
				UniversityFormEO university = session.createQuery(query).getSingleResult();
			        return university;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("fetchUniversityInformationScholarShip Error" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean saveOrUpdateUniversityFellowshipInformation(UniversityFellowshipVO universityScholarshipVO) {
		logger.info("MasterDaoImpl : saveOrUpdateUniversityScholarshipInformation method invoked :");
		Session session1 = sessionFactory.openSession();
		Session session2 = null;
		Transaction tx = null;
		UniversityFormEO university = new UniversityFormEO();
		try {
			university = fetchUniversityInformationFellowship(universityScholarshipVO.getUniversityId(),
					universityScholarshipVO.getSurveyYear(),universityScholarshipVO.getId());
			PersonsCountByCategory personCountByCatrgory = new PersonsCountByCategory();
			Fellowship scholarship = new Fellowship();
			UniversityFormEO universitysave = new UniversityFormEO();
			session2 = sessionFactory.openSession();
			tx = session2.beginTransaction();
			if (university!=null) {
				BeanUtils.copyProperties(universityScholarshipVO, personCountByCatrgory);
				Integer personCountByCatrgoryId = ((Integer) session1.createNativeQuery("select count_by_category_id from public.fellowship where id = '"+universityScholarshipVO.getId()+"'").uniqueResult());		
				personCountByCatrgory.setId(personCountByCatrgoryId);
				session2.saveOrUpdate(personCountByCatrgory);
				scholarship.setCountByCategory(personCountByCatrgory);
				//Integer scholarId = ((Integer) session.createQuery("select max(id) from Scholarship").uniqueResult());
				scholarship.setId(universityScholarshipVO.getId());
				session2.saveOrUpdate(scholarship);
				Integer scholarshipId = scholarship.getCountByCategory().getId();
				university.setScholarshipId(scholarshipId);
				BeanUtils.copyProperties(university, universitysave);
				session2.update(universitysave);
		      	}
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
			logger.info("saveOrUpdateEnrolledRegularStudent Error" + e.getMessage());
		} finally {
			session1.close();session2.close();
		}
		return true;
	}

	private UniversityFormEO fetchUniversityInformationFellowship(String universityId, Integer surveyYear, Integer id) {
		logger.info("MasterDaoImpl : fetchUniversityInformationScholarShip method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityFormEO> query = builder.createQuery(UniversityFormEO.class);
			Root<UniversityFormEO> allData = query.from(UniversityFormEO.class);
				query.where(builder.and(builder.equal(allData.get("id"), universityId),
						builder.equal(allData.get("surveyYear"), surveyYear),
						builder.equal(allData.get("felloshipsId"), id)));
				UniversityFormEO university = session.createQuery(query).getSingleResult();
			        return university;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("fetchUniversityInformationScholarShip Error" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean saveOrUpdateUniversityEducationLoanInformation(UniversityLoanVO universityScholarshipVO) {
		logger.info("MasterDaoImpl : saveOrUpdateUniversityScholarshipInformation method invoked :");
		Session session1 = sessionFactory.openSession();
		Session session2 = null;
		Transaction tx = null;
		UniversityFormEO university = new UniversityFormEO();
		try {
			university = fetchUniversityInformationLoan(universityScholarshipVO.getUniversityId(),
					universityScholarshipVO.getSurveyYear(),universityScholarshipVO.getId());
			PersonsCountByCategory personCountByCatrgory = new PersonsCountByCategory();
			Loan scholarship = new Loan();
			UniversityFormEO universitysave = new UniversityFormEO();
			session2 = sessionFactory.openSession();
			tx = session2.beginTransaction();
			if (university!=null) {
				BeanUtils.copyProperties(universityScholarshipVO, personCountByCatrgory);
				Integer personCountByCatrgoryId = ((Integer) session1.createNativeQuery("select count_by_category_id from public.loan where id = '"+universityScholarshipVO.getId()+"'").uniqueResult());		
				personCountByCatrgory.setId(personCountByCatrgoryId);
				session2.saveOrUpdate(personCountByCatrgory);
				scholarship.setCountByCategory(personCountByCatrgory);
				//Integer scholarId = ((Integer) session.createQuery("select max(id) from Scholarship").uniqueResult());
				scholarship.setId(universityScholarshipVO.getId());
				session2.saveOrUpdate(scholarship);
				Integer scholarshipId = scholarship.getCountByCategory().getId();
				university.setScholarshipId(scholarshipId);
				BeanUtils.copyProperties(university, universitysave);
				session2.update(universitysave);
		      	}
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
			logger.info("saveOrUpdateEnrolledRegularStudent Error" + e.getMessage());
		} finally {
			session1.close();session2.close();
		}
		return true;
	}

	private UniversityFormEO fetchUniversityInformationLoan(String universityId, Integer surveyYear, Integer id) {
		logger.info("MasterDaoImpl : fetchUniversityInformationScholarShip method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityFormEO> query = builder.createQuery(UniversityFormEO.class);
			Root<UniversityFormEO> allData = query.from(UniversityFormEO.class);
				query.where(builder.and(builder.equal(allData.get("id"), universityId),
						builder.equal(allData.get("surveyYear"), surveyYear),
						builder.equal(allData.get("loanId"), id)));
				UniversityFormEO university = session.createQuery(query).getSingleResult();
			        return university;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("fetchUniversityInformationScholarShip Error" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
}