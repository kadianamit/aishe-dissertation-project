package org.nicexam.authorizationservice.userdao;

import org.nicexam.authorizationservice.utility.Verhoeff;

public class TableIdGenerationUtil {
	
	public static Long generateTableId(Long nextGeneratedId) {
		System.out.println("nextGeneratedId"+nextGeneratedId);
		String nextGeneratedIdAsString  = nextGeneratedId.toString();
		String generateVerhoeffDigit = Verhoeff.GenerateVerhoeffDigit(nextGeneratedIdAsString);
		Long generateVerhoeffCareId = Long.parseLong(nextGeneratedIdAsString+generateVerhoeffDigit);
		System.out.println("generateVerhoeffCareId"+generateVerhoeffCareId);
		return generateVerhoeffCareId;
	}
}