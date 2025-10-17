package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.Country;
import com.nic.aishe.master.entity.ForeignInstitute;
import com.nic.aishe.master.entity.ForeignInstituteNew;
import com.nic.aishe.master.repo.CountryRepo;
import com.nic.aishe.master.repo.ForeignInstituteRapo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ForeignInstituteController {
    @Autowired
    private ForeignInstituteRapo foreignInstituteRapo;

    @Autowired(required = false)
    private CountryRepo countryRepo;

    @GetMapping("/foreign-institute")
    public ResponseEntity<?> getAllForeignInstitute(/* @WithUser UserInfo userInfo */) {
        //CommanObjectOperation.usernameValidate(userInfo);
        List<ForeignInstitute> Specialitys = foreignInstituteRapo.findAll();
        if (Specialitys != null && Specialitys.size() > 0) {
            return ResponseEntity.ok(Specialitys);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/foreign-institute/{id}")
    public ResponseEntity<?> getOneForeignInstitute(@PathVariable Integer id/* , @WithUser UserInfo userInfo */) {
        //CommanObjectOperation.usernameValidate(userInfo);
        ForeignInstitute Speciality = foreignInstituteRapo.getById(id);
        if (Speciality != null) {
            return ResponseEntity.ok(Speciality);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/foreign-institute-by-country/{countryId}")
    public ResponseEntity<?> getForeignInstitutes(@PathVariable(required = false) Integer countryId, @RequestParam(required = false) Integer status, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<ForeignInstitute> Specialitys = new ArrayList<>();
        if (countryId == null || countryId == 0 && null == status) {
            Specialitys = foreignInstituteRapo.findAllByCountry();
        } else if (countryId == null || countryId == 0 && null != status) {
            Specialitys = foreignInstituteRapo.findAllByCountryByStatus(status);
        } else if (countryId == null || countryId != 0 && null != status)
            Specialitys = foreignInstituteRapo.findAllByCountryIdAndStatus(countryId.toString(), status);
        else if (countryId == null || countryId != 0 && null == status) {
            Specialitys = foreignInstituteRapo.findAllByCountryId(countryId.toString());
        }
        return ResponseEntity.ok(Specialitys);
    }


    @GetMapping("/foreign-institute-by-country")
    public ResponseEntity<?> getForeignInstitutesWithCountry(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<Country> countries = countryRepo.findAllCountriesExceptOne();
        if (CommonObjectOperation.listValidate(countries)) {
            for (Country country : countries) {
                List<ForeignInstitute> foreignInstitutes = foreignInstituteRapo.findAllByCountryId(country.getId().toString());
                List<ForeignInstituteNew> foreignInstitutes1 = new ArrayList<>();
                if (CommonObjectOperation.listValidate(foreignInstitutes))
                    for (ForeignInstitute foreignInstitute : foreignInstitutes) {
                        ForeignInstituteNew instituteNew = new ForeignInstituteNew();
                        BeanUtils.copyProperties(foreignInstitute, instituteNew);
                        foreignInstitutes1.add(instituteNew);
                    }
                country.setForeignInstitutes(foreignInstitutes1);
            }
            return ResponseEntity.ok(countries);
        }
        return ResponseEntity.notFound().build();
    }

    /*@PostMapping("/foreign-institute")
    public ResponseEntity<?> saveForeignInstitutes(@RequestBody ForeignInstitute foreignInstitute, @WithUser UserInfo userInfo) {
        ForeignInstitute Specialitys = foreignInstituteRapo.save(foreignInstitute);
        return ResponseEntity.ok(Specialitys);
    }*/
}
