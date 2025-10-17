package aishe.gov.in.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.LockDao;
import aishe.gov.in.mastersvo.UnlockVO;
@Service
public class LockServiceImpl implements LockService {
  @Autowired
    LockDao lockDao;
	@Override
	public boolean unlockWebdcf(UnlockVO unlockVO,HttpServletRequest request) {
		return lockDao.unlockWebdcf(unlockVO,request);
	}
}