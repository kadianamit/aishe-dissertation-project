package aishe.gov.in.dao;

import java.util.List;

import aishe.gov.in.masterseo.AisheUnitCellEo;

public interface AisheUnitCellDao {

	List<AisheUnitCellEo> getAisheUnitCell(String stateCode);

	boolean saveOrUpdateAisheUnitCell(List<AisheUnitCellEo> aisheUnitCellList);

	boolean deleteAisheUnitCell(Integer id, boolean whetherStateHavingAisheUnitCell, String stateCode);

  

}
