package aishe.gov.in.dao;

import aishe.gov.in.masterseo.UserActionLogEONew;

public interface UserActionLogDao {

    boolean saveUserActionLog(UserActionLogEONew userActionLog);
    Integer getMaxId();
}
