package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.StaffTypes;
import com.nic.aishe.master.impl.StaffTypesService;
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


@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class StaffTypesController {


    @Autowired(required = false)
    private StaffTypesService typesService;

/*    @PostMapping("/stafftype")
    public ResponseEntity<?> addType(@RequestBody StaffTypes Type) {
        StaffTypes Types = typesService.addType(Type);
        return ResponseEntity.ok(Types);
    }*/

    @GetMapping("/stafftype")
    public ResponseEntity<?> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<StaffTypes> Types = typesService.getAllType();
        if (Types != null && Types.size() > 0) {
            return ResponseEntity.ok(Types);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/stafftype/{id}")
    public ResponseEntity<?> getOneType(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        StaffTypes Type = typesService.getOneType(id);
        if (Type != null) {
            return ResponseEntity.ok(Type);
        }
        return ResponseEntity.notFound().build();

    }


   /* @DeleteMapping("/stafftype/{id}")
    public ResponseEntity<?> deleteOneType(@PathVariable Integer id) {
        Boolean isdel = typesService.deleteOneType(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/stafftype")
    public ResponseEntity<?> deleteAllType() {
        typesService.deleteAllType();

        return ResponseEntity.ok("All deleted Success !");
    }*/


}
