package aishe.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.AisheUnitCellDao;
import aishe.gov.in.masterseo.AisheUnitCellEo;

@Service
public class AisheUnitCellServiceImpl implements AisheUnitCellService {

	@Autowired
	AisheUnitCellDao aisheUnitCellDao;
	
	@Override
	public List<AisheUnitCellEo> getAisheUnitCell(String stateCode) {
		return aisheUnitCellDao.getAisheUnitCell(stateCode);
	}

	@Override
	public boolean saveOrUpdateAisheUnitCell(List<AisheUnitCellEo> aisheUnitCellList) {
		return aisheUnitCellDao.saveOrUpdateAisheUnitCell(aisheUnitCellList);
	}

	@Override
	public boolean deleteAisheUnitCell(Integer id, boolean whetherStateHavingAisheUnitCell, String stateCode) {
		return aisheUnitCellDao.deleteAisheUnitCell(id, whetherStateHavingAisheUnitCell, stateCode);
	}

  
}