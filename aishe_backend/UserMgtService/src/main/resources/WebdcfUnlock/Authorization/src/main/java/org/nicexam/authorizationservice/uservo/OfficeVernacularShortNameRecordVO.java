package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.util.List;

public class OfficeVernacularShortNameRecordVO implements Serializable {

	private static final long serialVersionUID = -4647793936867240626L;
	private List<OfficeVernacularShortNameListVO> listData;

	public List<OfficeVernacularShortNameListVO> getListData() {
		return listData;
	}
	public void setListData(List<OfficeVernacularShortNameListVO> listData) {
		this.listData = listData;
	}
}
