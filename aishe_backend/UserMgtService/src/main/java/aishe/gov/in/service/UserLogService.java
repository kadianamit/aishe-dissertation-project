package aishe.gov.in.service;

import aishe.gov.in.masterseo.UserActionLogEO;

public interface UserLogService {

    UserActionLogEO save (UserActionLogEO logEO);
    Integer getMaxId();
}
