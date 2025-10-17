package aishe.gov.in.service;

import aishe.gov.in.masterseo.DistrictRef;
import aishe.gov.in.masterseo.StateRef;

public interface CollegeStateDistrictService {

    DistrictRef getDistrictByCode(String distCode);

    StateRef getStateByCode(String stCode);
}
