package aishe.gov.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.CollegeStateDistrictDao;
import aishe.gov.in.masterseo.DistrictRef;
import aishe.gov.in.masterseo.StateRef;

@Service
public class CollegeStateDistrictServiceImpl implements CollegeStateDistrictService {
    @Autowired
    private CollegeStateDistrictDao stateDistrictDao;

    @Override
    public DistrictRef getDistrictByCode(String distCode) {
        return stateDistrictDao.getDistrictByCode(distCode);
    }

    @Override
    public StateRef getStateByCode(String stCode) {
        return stateDistrictDao.getStateByCode(stCode);
    }
}
