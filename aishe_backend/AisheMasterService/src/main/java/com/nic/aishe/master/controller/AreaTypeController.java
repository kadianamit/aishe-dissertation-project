package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.AreaType;
import com.nic.aishe.master.impl.AreaTypesService;
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
public class AreaTypeController {


    @Autowired
    private AreaTypesService typesService;

   /* @PostMapping("/areatype")
    public ResponseEntity<?> addType(@RequestBody AreaType Type) {
        AreaType Types = typesService.addType(Type);
        return ResponseEntity.ok(Types);
    }*/

    @GetMapping("/areatype")
    public ResponseEntity<?> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<AreaType> Types = typesService.getAllType();
        if (Types != null && Types.size() > 0) {
            return ResponseEntity.ok(Types);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/areatype/{id}")
    public ResponseEntity<?> getOneType(@PathVariable Integer id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        AreaType Type = typesService.getOneType(id);
        if (Type != null) {
            return ResponseEntity.ok(Type);
        }
        return ResponseEntity.notFound().build();

    }

/*    @DeleteMapping("/areatype/{id}")
    public ResponseEntity<?> deleteOneType(@PathVariable Integer id) {
        Boolean isdel = typesService.deleteOneType(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id=" + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/areatype")
    public ResponseEntity<?> deleteAllType() {
        typesService.deleteAllType();

        return ResponseEntity.ok("All deleted Success !");
    }*/


}
