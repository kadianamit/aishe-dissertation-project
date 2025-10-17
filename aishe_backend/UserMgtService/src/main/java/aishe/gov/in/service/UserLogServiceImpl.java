package aishe.gov.in.service;

import aishe.gov.in.dao.ActionLogDao;
import aishe.gov.in.masterseo.UserActionLogEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private ActionLogDao actionLogDao;

    @Override
    public UserActionLogEO save(UserActionLogEO logEO) {
        return actionLogDao.saveUserActionLog(logEO);
    }

    @Override
    public Integer getMaxId() {
        return actionLogDao.maxId();
    }
}
