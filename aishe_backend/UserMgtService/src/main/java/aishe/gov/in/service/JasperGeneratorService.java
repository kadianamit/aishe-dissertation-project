package aishe.gov.in.service;

import java.io.File;
import java.sql.Connection;

public interface JasperGeneratorService {	

	public Connection findConnection();

	File getFileLocationByAisheCode(String aisheCode);
}
