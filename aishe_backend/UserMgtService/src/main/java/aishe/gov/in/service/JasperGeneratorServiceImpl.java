package aishe.gov.in.service;

import java.io.File;
import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.ReportGenerationDao;

@Service
public class JasperGeneratorServiceImpl implements JasperGeneratorService{

	@Autowired
	private ReportGenerationDao reportGenerationDao;
	
	@Override
	public Connection findConnection() {
		// TODO Auto-generated method stub
		return reportGenerationDao.findConnection();
	}

	@Override
	public File getFileLocationByAisheCode(String aisheCode) {
		String type = aisheCode.split("-")[0];
		String instituteType = "-DCF.pdf";
		if (type.equalsIgnoreCase("C")) {
			return new File("/home/cpdf/" + aisheCode + instituteType);
			//return new File("D:\\FILES\\" + aisheCode + instituteType);
		}
		if (type.equalsIgnoreCase("U")) {
			// return new File("D:\\FILES\\" + aisheCode + instituteType);
			return new File("/home/updf/" + aisheCode + instituteType);
		}
		if (type.equalsIgnoreCase("S")) {
			return new File("/home/spdf/" + aisheCode + instituteType);
		}
		return null;
	}
}
