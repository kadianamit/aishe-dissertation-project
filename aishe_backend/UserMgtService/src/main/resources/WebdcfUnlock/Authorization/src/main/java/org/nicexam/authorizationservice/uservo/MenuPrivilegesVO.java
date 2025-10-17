package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.util.List;

public class MenuPrivilegesVO implements Serializable {

	private static final long serialVersionUID = -5439711541789562816L;
	
	private List<MenuPrivilegesListVO> listData;

	public List<MenuPrivilegesListVO> getListData() {
		return listData;
	}

	public void setListData(List<MenuPrivilegesListVO> listData) {
		this.listData = listData;
	}


	
}
