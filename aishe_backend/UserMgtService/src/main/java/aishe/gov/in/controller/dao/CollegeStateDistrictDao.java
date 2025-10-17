package aishe.gov.in.dao;

import aishe.gov.in.masterseo.DistrictRef;
import aishe.gov.in.masterseo.StateRef;

public interface CollegeStateDistrictDao {

    DistrictRef getDistrictByCode(String distCode);

    StateRef getStateByCode(String distCode);
}
