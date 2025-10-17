package aishe.gov.in.dao;

import javax.servlet.http.HttpServletRequest;

import aishe.gov.in.mastersvo.UnlockVO;

public interface LockDao {

	boolean unlockWebdcf(UnlockVO unlockVO,HttpServletRequest request);
 
}
