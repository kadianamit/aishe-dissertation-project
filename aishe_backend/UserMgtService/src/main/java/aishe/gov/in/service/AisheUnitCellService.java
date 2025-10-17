package aishe.gov.in.service;

import java.util.List;

import aishe.gov.in.masterseo.AisheUnitCellEo;

public interface AisheUnitCellService {

	List<AisheUnitCellEo> getAisheUnitCell(String stateCode);

	boolean saveOrUpdateAisheUnitCell(List<AisheUnitCellEo> aisheUnitCellList);

	boolean deleteAisheUnitCell(Integer id, boolean whetherStateHavingAisheUnitCell, String stateCode);

}
