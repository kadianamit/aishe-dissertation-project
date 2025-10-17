package com.nic.aishe.master.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.Country;
import com.nic.aishe.master.repo.CountryRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class CountryController {

    @Autowired(required = false)
    private CountryRepo countryRepo;

   /* @PostMapping("/country")
    public Country addCountry(@RequestBody Country Country) {
        return countryRepo.save(Country);
    }
*/
    @GetMapping("/country")
	public List<Country> getAllType() {
    	List<Country> country = countryRepo.findAll();
    	country.sort(Comparator.comparing(Country::getName));
        return country;
    }
    
    @GetMapping("/country-private")
	public List<Country> getAllType( @WithUser UserInfo userInfo ) {
        CommonObjectOperation.usernameValidate(userInfo);
    	List<Country> country = countryRepo.findAll();
    	country.sort(Comparator.comparing(Country::getName));
        return country;
    }
}
