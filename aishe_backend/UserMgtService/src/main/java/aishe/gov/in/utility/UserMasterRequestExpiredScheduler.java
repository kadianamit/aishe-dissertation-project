package aishe.gov.in.utility;

import aishe.gov.in.dao.RegisterationDao;
import aishe.gov.in.dao.RegisterationDaoImpl;
import aishe.gov.in.masterseo.UserMasterLogDetailEO;
import aishe.gov.in.masterseo.UserMasterRequestDetailEO;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2

public class UserMasterRequestExpiredScheduler {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    RegisterationDao dao;

    private static final Logger logger = LoggerFactory.getLogger(UserMasterRequestExpiredScheduler.class);

    @Scheduled(cron = "0 0 0 * * *") // This cron expression triggers at midnight
     public void expiredUserRequest() {
        logger.info("Expired User Request Scheduled task started.");
        Transaction tx = null;
        List<UserMasterRequestDetailEO> list = dao.getExpiredUserMasterRequest(null);
        if (CommanObjectOperation.listValidate(list)) {
            try (Session session = sessionFactory.openSession()) {
                tx = session.beginTransaction();
                for (UserMasterRequestDetailEO eo : list) {
                    UserMasterLogDetailEO logDetailEO = new UserMasterLogDetailEO();
                    BeanUtils.copyProperties(eo, logDetailEO);
                    logDetailEO.setStatusId(5);
                    logDetailEO.setIsApproved(0);
                    logDetailEO.setId(null);
                    session.save(logDetailEO);
                    session.delete(eo);
                }
                tx.commit();
            } catch (Exception e) {
                logger.error("Expired User Request Scheduled task interrupted due to {} this exception ", e.getMessage());
                if (null != tx && tx.isActive()) {
                    tx.rollback();
                }
            }
        }
        logger.info("Expired User Request Scheduled task Completed.");
    }

}
