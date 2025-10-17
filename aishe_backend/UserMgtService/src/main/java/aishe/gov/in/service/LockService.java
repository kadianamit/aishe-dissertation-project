package aishe.gov.in.service;

import javax.servlet.http.HttpServletRequest;

import aishe.gov.in.mastersvo.UnlockVO;

public interface LockService {

	boolean unlockWebdcf(UnlockVO unlockVO, HttpServletRequest request);
}
