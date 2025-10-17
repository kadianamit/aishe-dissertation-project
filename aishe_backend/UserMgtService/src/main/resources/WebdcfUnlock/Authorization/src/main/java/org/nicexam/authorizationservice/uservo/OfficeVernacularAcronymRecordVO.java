package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.util.List;

public class OfficeVernacularAcronymRecordVO implements Serializable {

	private static final long serialVersionUID = -4647793936867240626L;
	private List<OfficeVernacularAcronymListVO> listData;

	public List<OfficeVernacularAcronymListVO> getListData() {
		return listData;
	}
	public void setListData(List<OfficeVernacularAcronymListVO> listData) {
		this.listData = listData;
	}
}
