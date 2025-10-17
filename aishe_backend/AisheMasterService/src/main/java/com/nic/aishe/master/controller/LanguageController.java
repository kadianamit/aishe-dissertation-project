package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.RefLanguage;
import com.nic.aishe.master.repo.LanguageRapo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class LanguageController {
    @Autowired
    private LanguageRapo languageRapo;

    @GetMapping("/langauge/{languageId}")
    public RefLanguage findById(@PathVariable Integer languageId, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return languageRapo.findById(languageId).get();

    }
   

    @GetMapping("/langauge")
    public List<RefLanguage> findAll(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<RefLanguage> refLanguages = languageRapo.findAll();
        refLanguages.sort(Comparator.comparing(RefLanguage::getId));
        return refLanguages;
    }

    @GetMapping("/langauge-public")
    public List<RefLanguage> findAll() {
        List<RefLanguage> refLanguages = languageRapo.findAll();
        refLanguages.sort(Comparator.comparing(RefLanguage::getId));
        return refLanguages;
    }

}
