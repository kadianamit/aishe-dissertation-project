package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.util.List;

public class OfficeVernacularAlternateNameRecordVO implements Serializable {
	
	private static final long serialVersionUID = 7698805407004635565L;
	
	private List<OfficeVernacularAlternateNameListVO> listData;

	public List<OfficeVernacularAlternateNameListVO> getListData() {
		return listData;
	}
	public void setListData(List<OfficeVernacularAlternateNameListVO> listData) {
		this.listData = listData;
	}
}
