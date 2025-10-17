package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.HostelType;
import com.nic.aishe.master.impl.HostelTypeService;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class HostelTypeController {


    @Autowired(required = false)
    private HostelTypeService hostelTypeService;

    /*@PostMapping("/hostel-type")
    public ResponseEntity<?> addType(@RequestBody HostelType Type) {
        HostelType Types = hostelTypeService.addType(Type);
        return ResponseEntity.ok(Types);
    }*/

    @GetMapping("/hostel-type")
    public ResponseEntity<?> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<HostelType> Types = hostelTypeService.getAllType();
        if (Types != null && Types.size() > 0) {
            return ResponseEntity.ok(Types);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/hostel-type/{id}")
    public ResponseEntity<?> getOneType(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        HostelType Type = hostelTypeService.getOneType(id);
        if (Type != null) {
            return ResponseEntity.ok(Type);
        }
        return ResponseEntity.notFound().build();

    }

/*    @DeleteMapping("/hostel-type/{id}")
    public ResponseEntity<?> deleteOneType(@PathVariable Integer id) {
        Boolean isdel = hostelTypeService.deleteOneType(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/hostel-type")
    public ResponseEntity<?> deleteAllType() {
        hostelTypeService.deleteAllType();

        return ResponseEntity.ok("All deleted Success !");
    }*/


}
